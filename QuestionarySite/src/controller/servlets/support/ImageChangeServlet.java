package controller.servlets.support;

import model.database.UserDAO;
import model.entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Paths;
import java.util.Random;

@WebServlet(name = "ImageChangeServlet", urlPatterns = "/img_change")
@MultipartConfig
public class ImageChangeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("profile_img"); // Retrieves <input type="file" name="file">
        String ext = Paths.get(filePart.getSubmittedFileName()).getFileName().toString().split("\\.")[1]; // MSIE fix.
        String fileName = String.valueOf((new Random()).nextInt(1000000)) + ext;
        InputStream fileContent = filePart.getInputStream();

        OutputStream fileStream = new FileOutputStream(new File("D:/SiteImages/"+fileName));
        int a;
        while ((a = fileContent.read()) != -1) {
            fileStream.write(a);
        }
        fileContent.close();
        fileStream.close();

        UserDAO dao = UserDAO.getDao();

        User user = (User) request.getSession().getAttribute("current_user");

        user.setImgPath(fileName);

        request.setAttribute("current_user", user);

        dao.updateImgPathByUsername(fileName, user.getUsername());

        response.sendRedirect("/profile?id=" + user.getId());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
