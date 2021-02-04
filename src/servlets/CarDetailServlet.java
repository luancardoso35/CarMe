package servlets;

import model.Carro;
import model.CarroDAO;
import model.Usuario;
import model.UsuarioDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet(name = "CarDetailServlet", urlPatterns = "/carDetail")
public class CarDetailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String imageName = request.getParameter("img");

        if (imageName == null) {

            String cpf = (String) request.getSession().getAttribute("cpf");
            if (cpf == null) {
                request.setAttribute("carro", request.getParameter("carro"));
                request.getRequestDispatcher("/register.jsp?method=carDetail").forward(request, response);
            }

            String renavam = (String) request.getSession().getAttribute("carro");
            if (renavam == null) {
                renavam = request.getParameter("carro");
            }

            Carro carro = CarroDAO.queryCarByRenavam(renavam);
            Usuario user = UsuarioDAO.queryUserCar(renavam);
            if (user != null) {
                if (user.getCPF().equals(cpf)) {
                    request.setAttribute("errorCPF", "O carro já está cadastrado nesse usuário. Para" +
                            " visualizá-lo, entre na aba 'Meus Anúncios'");
                    request.getRequestDispatcher("/catalogue").forward(request, response);
                }
            }

            request.setAttribute("carro", carro);
            request.setAttribute("user", user);
            request.getRequestDispatcher("carDetails.jsp").forward(request, response);
        } else {
            String path = "C:\\dev\\CarMe\\users\\";
            response.setContentType("image/gif");
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(path + imageName));

            response.getOutputStream().write(bis.readAllBytes());
            bis.close();
        }


    }
}
