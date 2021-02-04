package servlets;

import model.UsuarioDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateProfileServlet", urlPatterns = "/updateProfile")
public class UpdateProfileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        nome = nome.replaceAll("%20", " ");

        String cpf = (String) request.getSession().getAttribute("cpf");
        String email = request.getParameter("email");
        String telefone = RegisterServlet.editTelefone(request.getParameter("tel"));
        telefone = telefone.replaceAll("-", "");

        if (UsuarioDAO.updateUser(cpf, nome, email, telefone)) {
            request.getSession().removeAttribute("nome");
            request.getSession().setAttribute("nome", nome.split(" ")[0]);
            response.sendRedirect("userProfile");
        }
    }
}
