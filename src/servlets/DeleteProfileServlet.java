package servlets;

import model.Carro;
import model.CarroDAO;
import model.UsuarioDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "DeleteProfileServlet", urlPatterns = "/deleteProfile")
public class DeleteProfileServlet extends HttpServlet {
    String pathImagesUsers = "C:\\dev\\CarMe\\users";
    String pathImagesCars = "C:\\dev\\CarMe\\carros";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cpf = (String) request.getSession().getAttribute("cpf");
        HashMap<String, Carro> cars = CarroDAO.queryCarsByUser(cpf);

        if (CarroDAO.deleteUserCars(cpf) && UsuarioDAO.deleteUser(cpf)) {
            String imageName = (String) request.getSession().getAttribute("imageName");
            if (deleteProfileIcon(imageName)) {
                boolean fotos = true;
                if (cars != null) {
                    for (Carro carro : cars.values()) {
                        if (!deleteCarImage(carro.getImageName())) {
                            fotos = false;
                        }
                    }
                }
                if (fotos) {
                    response.sendRedirect("/logout");
                }
            }
        }
    }

    private boolean deleteProfileIcon (String imageName) {
        File f = new File(pathImagesUsers + "\\" + imageName);
        return f.delete();
    }

    private boolean deleteCarImage (String imageName) {
        File f = new File (pathImagesCars + "\\" + imageName);
        return f.delete();
    }

}
