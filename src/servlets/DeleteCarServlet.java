package servlets;

import model.Carro;
import model.CarroDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet(name = "DeleteCarServlet", urlPatterns = "/deleteCar")
public class DeleteCarServlet extends HttpServlet {
    String pathImages = "C:\\dev\\CarMe\\carros";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String renavam = request.getParameter("carro");
        Carro carro = CarroDAO.queryCarByRenavam(renavam);

        if (CarroDAO.deleteCar(renavam)) {
            if (deleteCarImage(carro.getImageName())) {
                response.sendRedirect("/userProfile");
            }
        }
    }

    private boolean deleteCarImage (String imageName) {
        File f = new File (pathImages + "\\" + imageName);
        return f.delete();
    }
}
