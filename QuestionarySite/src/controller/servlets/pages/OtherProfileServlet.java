package controller.servlets.pages;

import freemarker.template.TemplateException;
import model.database.UserDAO;
import model.entities.User;
import support.Render;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "OtherProfileServlet", urlPatterns = "/profile1")
public class OtherProfileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> context = new HashMap<>();

        User currentUser = (User) request.getSession().getAttribute("current_user");

        context.put("cur_page", "other_profile");

       /* User user = UserDAO.getDao().getUserById(request.getParameter("id"));

        if (currentUser != null) {
            context.put("user", currentUser);
            context.put("owner", user);
        }*/


        try {
            Render.render(response, context, "/profile.ftl");
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
