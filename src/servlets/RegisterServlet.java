package servlets;
import com.oreilly.servlet.MultipartRequest;
import model.UsuarioDAO;

import javax.servlet.annotation.WebServlet;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

@WebServlet (name = "RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        MultipartRequest multipartRequest = null;
        String pathRequests = "C:\\dev\\CarMe\\requests";
        String pathImages = "C:\\dev\\CarMe\\users";

        try {
            multipartRequest = new MultipartRequest(request, pathRequests);
        } catch (IOException e) {
            request.setAttribute("error", "Imagem muito grande. Experimente reduzi-la ou usar outra imagem.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }

        String method = multipartRequest.getParameter("method");
        String nome = multipartRequest.getParameter("nome");
        String cpf = editCPF(multipartRequest.getParameter("cpf"));
        String email = multipartRequest.getParameter("email");
        String telefone = editTelefone(multipartRequest.getParameter("telefone"));
        String senha = multipartRequest.getParameter("senha");
        File imagem = multipartRequest.getFile("imagem");

        String path = pathImages + "\\" + imagem.getName();
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path));
        byte[] imagemBytes = Files.readAllBytes(imagem.toPath());
        bos.write(imagemBytes);
        bos.flush();
        bos.close();

        if (UsuarioDAO.insert(nome, cpf, email, telefone, senha, imagem.getName())) {
            request.getSession().setAttribute("nome", nome.split(" ")[0]);
            request.getSession().setAttribute("cpf", cpf);
            request.getSession().setAttribute("imageName", imagem.getName());
        } else {
            request.setAttribute("error", "O usuário já existe");
            request.getRequestDispatcher("/register.jsp?method=" + method).forward(request,response);
        }

        switch (method) {
            case "compra" -> response.sendRedirect("/catalogue");
            case "venda" -> response.sendRedirect("/registerCar.jsp");
            case "none" -> response.sendRedirect("/index.jsp");
            case "about" -> response.sendRedirect("/aboutUs.jsp");
            default -> {
                request.getSession().setAttribute("carro", request.getParameter("carro"));
                response.sendRedirect("/carDetail");
            }
        }
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    public String editCPF(String cpf) {
        cpf = cpf.replaceAll("[.]", "");
        cpf = cpf.replaceAll("[-]", "");

        return cpf;
    }

    public static String editTelefone(String telefone) {
        telefone = telefone.replaceAll(" ", "");
        telefone = telefone.replaceAll("[()]", "");

        return telefone;
    }
}
