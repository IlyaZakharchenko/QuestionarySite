package controller.servlets.pages;

import freemarker.template.TemplateException;
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

@WebServlet(name = "MainServlet", urlPatterns = "/main")
public class MainServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> context = new HashMap<>();

        User currentUser = (User) request.getSession().getAttribute("current_user");

        if (currentUser != null) {
            context.put("user", currentUser);
        }

        context.put("cur_page", "main");

        if (request.getParameter("l") != null) {
            context.put("open_signin", true);
        }


        try {
            Render.render(response, context, "/main.ftl");
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
