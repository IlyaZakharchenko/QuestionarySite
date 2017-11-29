package controller.servlets.pages;

import freemarker.template.TemplateException;
import model.database.InterviewDAO;
import model.entities.Interview;
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

@WebServlet(name = "ConcreteInterviewServlet", urlPatterns = "/interview")
public class ConcreteInterviewServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map<String, Object> context = new HashMap<>();

        User currentUser = (User) request.getSession().getAttribute("current_user");

        Interview interview = InterviewDAO.getDao().read(Integer.parseInt(request.getParameter("id")));

        if (currentUser != null) {
            context.put("user", currentUser);
        }

        context.put("cur_page", "concrete");

        if (interview != null) {
            context.put("interview", interview);
        }


        try {
            Render.render(response, context, "/concrete_interview.ftl");
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

}
