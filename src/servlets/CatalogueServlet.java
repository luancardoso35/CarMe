package servlets;

import model.Carro;
import model.CarroDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "CatalogueServlet", urlPatterns = "/catalogue")
public class CatalogueServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String imageName = request.getParameter("img");

        if (imageName == null) {

            String marca = request.getParameter("marca");
            String modelo = request.getParameter("modelo");
            String cambio = request.getParameter("cambio");
            String ano = request.getParameter("ano");
            String assentos = (request.getParameter("assentos"));
            int integerAssentos = 0;

            if(marca == null) {
                marca = "";
            }

            if(modelo == null) {
                modelo = "";
            }

            if(cambio == null) {
                cambio = "";
            }

            if(ano == null) {
                ano = "";
            }

            if (assentos != null) {
                if (!assentos.equals("")) {
                    integerAssentos = Integer.parseInt(assentos);
                }
            }

            String cpfUser = (String) request.getSession().getAttribute("cpf");

            HashMap<String, Carro> cars = CarroDAO.queryCars(marca, modelo, cambio, integerAssentos,
                    ano, cpfUser);

            if (cars != null) {
                request.setAttribute("carMap", cars);
            } else {
                if (request.getParameter("marca") == null) {
                    request.setAttribute("error", "Não há nada para mostrar por enquanto.");
                } else {
                    request.setAttribute("error", "Nenhum resultado encontrado.");
                }
            }

            request.getRequestDispatcher("catalogue.jsp").forward(request, response);
        } else {
            String path = "C:\\dev\\CarMe\\carros\\";
            response.setContentType("image/gif");
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(path + imageName));

            response.getOutputStream().write(bis.readAllBytes());
            bis.close();
        }
    }
}
