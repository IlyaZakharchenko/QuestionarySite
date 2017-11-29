package controller.servlets.pages;

import freemarker.template.TemplateException;
import model.database.UserDAO;
import model.entities.User;
import support.ParameterDecoder;
import support.Render;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "AccountSettingsServlet", urlPatterns = "/account_settings")
public class AccountSettingsServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> context = new HashMap<>();

        User currentUser = (User) request.getSession().getAttribute("current_user");

        context.put("cur_page", "settings");

        String email = request.getParameter("email");
        String about = request.getParameter("about_yourself");
        String ageStr = request.getParameter("age");


        if (about == null) about = "";
        if (email == null) email = "";

        int age = 0;
        if (ageStr != null) {
            age = Integer.parseInt(ageStr);
        }
        about = ParameterDecoder.decodePostParameter(about);

        UserDAO dao = UserDAO.getDao();

        if (!currentUser.getEmail().equals(email)) currentUser.setEmail(email);
        if (!about.equals(currentUser.getAboutYourself())) currentUser.setAboutYourself(about);
        if (currentUser.getAge() != age) currentUser.setAge(age);

        context.put("user", currentUser);

        dao.update(currentUser);

        response.sendRedirect("/profile?id=" + currentUser.getId());

        try {
            Render.render(response, context, "/settings.ftl");
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> context = new HashMap<>();

        User currentUser = (User) request.getSession().getAttribute("current_user");

        context.put("cur_page", "settings");

        if (currentUser != null) {
            context.put("user", currentUser);
        }


        try {
            Render.render(response, context, "/settings.ftl");
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
