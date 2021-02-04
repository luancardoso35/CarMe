package servlets;

import model.Usuario;
import model.UsuarioDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ProfileServlet", urlPatterns = "/userProfile")
public class ProfileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cpf = (String) request.getSession().getAttribute("cpf");

        Usuario user = UsuarioDAO.queryUsuario(cpf);

        user.setTelefone(UsuarioDAO.editTelefone(user.getTelefone()));
        request.setAttribute("user", user);
        request.setAttribute("nome", user.getNome().split(" ")[0]);
        request.getRequestDispatcher("/userCars").forward(request,response);
    }
}
