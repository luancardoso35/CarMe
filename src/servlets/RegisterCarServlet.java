package servlets;

import com.oreilly.servlet.MultipartRequest;
import model.CarroDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

@WebServlet(name = "RegisterCarServlet", urlPatterns = "/registerCar")
public class RegisterCarServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathRequests = "C:\\dev\\CarMe\\requests";
        String pathImages = "C:\\dev\\CarMe\\carros";
        MultipartRequest multipartRequest = null;

        try {
             multipartRequest = new MultipartRequest(request, pathRequests);
        } catch (IOException e) {
            request.setAttribute("error", "Imagem muito grande. Experimente reduzi-la ou usar outra imagem.");
            request.getRequestDispatcher("registerCar.jsp").forward(request, response);
        }

        String marca = multipartRequest.getParameter("marca");
        String modelo = multipartRequest.getParameter("modelo");
        String cor = multipartRequest.getParameter("cor");
        String cambio = multipartRequest.getParameter("cambio");
        int assentos = Integer.parseInt(multipartRequest.getParameter("assentos"));
        String ano = multipartRequest.getParameter("ano");
        String quilometragem = multipartRequest.getParameter("kms");
        String renavam = multipartRequest.getParameter("renavam");
        String combustivel = multipartRequest.getParameter("combustivel");
        String preco = multipartRequest.getParameter("preco");
        File imagem = multipartRequest.getFile("imagem");
        String cidade = multipartRequest.getParameter("cidade");

        String cpfUser = (String) request.getSession().getAttribute("cpf");

        String path = pathImages + "\\" + imagem.getName();
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path));
        byte[] imagemBytes = Files.readAllBytes(imagem.toPath());
        bos.write(imagemBytes);
        bos.flush();
        bos.close();

        if (CarroDAO.insertCar(marca, modelo, cambio, assentos, ano, renavam, cor, quilometragem, combustivel,
                 preco, cpfUser, imagem.getName(), cidade)) {
            response.sendRedirect("/catalogue");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
