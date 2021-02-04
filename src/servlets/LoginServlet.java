package servlets;

import model.Usuario;
import model.UsuarioDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        String cpf = editCPF(request.getParameter("cpf"));
        String password = request.getParameter("password");


        Usuario user = UsuarioDAO.queryUserLogin(cpf, password);

        if(user != null) {

            request.getSession().setAttribute("nome", user.getNome().split(" ")[0]);
            request.getSession().setAttribute("cpf", user.getCPF());
            request.getSession().setAttribute("imageName", user.getImageName());
        } else {
            request.setAttribute("error", "Usuário e/ou senha incorreto(s)");
            request.getRequestDispatcher("/login.jsp?method=" + method).forward(request,response);
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public String editCPF(String cpf) {
        cpf = cpf.replaceAll("[.]", "");
        cpf = cpf.replaceAll("[-]", "");

        return cpf;
    }


}
