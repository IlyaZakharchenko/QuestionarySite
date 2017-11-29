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

@WebServlet(name = "MyProfileServlet", urlPatterns = "/profile")
public class MyProfileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> context = new HashMap<>();

        User currentUser = (User) request.getSession().getAttribute("current_user");


        if (Integer.parseInt(request.getParameter("id")) == currentUser.getId()) {
            context.put("cur_page", "my_profile");
            context.put("owner", currentUser);
        }
        else {
            context.put("cur_page", "profile");
            User user = UserDAO.getDao().getUserById(Integer.parseInt(request.getParameter("id")));
            context.put("owner", user);
        }

            context.put("user", currentUser);


        try {
            Render.render(response, context, "/profile.ftl");
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
