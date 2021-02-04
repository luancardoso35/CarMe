package servlets;

import model.Carro;
import model.CarroDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "AllUserCarsServlet", urlPatterns = "/userCars")
public class AllUserCarsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cpf = (String) request.getSession().getAttribute("cpf");

        HashMap<String, Carro> carMap = CarroDAO.queryCarsByUser(cpf);
        if (carMap != null) {
            request.setAttribute("carMap", carMap);

        } else {
            request.setAttribute("error", "Você não fez nenhum anúncio ainda..");
        }
        request.getRequestDispatcher("/userProfile.jsp").forward(request,response);
    }
}
