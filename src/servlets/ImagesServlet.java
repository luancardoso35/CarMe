package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet(name = "ImagesServlet")
public class ImagesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "C:\\dev\\CarMe\\carros\\";
        String imageName = request.getParameter("img");
        response.setContentType("image/gif");
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(path + imageName));

        response.getOutputStream().write(bis.readAllBytes());
        bis.close();
    }
}
