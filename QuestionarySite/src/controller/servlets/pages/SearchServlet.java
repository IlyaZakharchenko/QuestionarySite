package controller.servlets.pages;

import freemarker.template.TemplateException;
import model.database.InterviewDAO;
import model.database.ThemeDAO;
import model.entities.Interview;
import model.entities.User;
import support.ParameterDecoder;
import support.Render;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "SearchServlet", urlPatterns = "/search")
public class SearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> context = new HashMap<>();

        User currentUser = (User) request.getSession().getAttribute("current_user");

        boolean work = Boolean.parseBoolean(request.getParameter("work"));
        boolean scool = Boolean.parseBoolean(request.getParameter("scool"));
        boolean car = Boolean.parseBoolean(request.getParameter("car"));
        boolean education = Boolean.parseBoolean(request.getParameter("education"));
        boolean technic = Boolean.parseBoolean(request.getParameter("technic"));
        boolean ethernet = Boolean.parseBoolean(request.getParameter("ethernet"));
        boolean politic = Boolean.parseBoolean(request.getParameter("politic"));
        boolean fashion = Boolean.parseBoolean(request.getParameter("fashion"));

        String searchText = request.getParameter("search_text");

        if (searchText == null) {
            searchText = "";
        }

        searchText = ParameterDecoder.decodePostParameter(searchText).toLowerCase();

        searchText = "%"+ searchText +"%";

        if (currentUser != null) {
            context.put("user", currentUser);
        }

        context.put("cur_page", "search");

        InterviewDAO dao = InterviewDAO.getDao();

        ThemeDAO themeDAO = ThemeDAO.getDao();

        List<Interview> interviews = new ArrayList<>();

        boolean theme = false;

        if (work) {
            interviews.addAll(dao.getInterviewByThemeString(themeDAO.getThemeByName("Работа").getId(), searchText));
            theme = true;
        }
        if (scool) {
            interviews.addAll(dao.getInterviewByThemeString(themeDAO.getThemeByName("Школа").getId(), searchText));
            theme = true;
        }
        if (car) {
            interviews.addAll(dao.getInterviewByThemeString(themeDAO.getThemeByName("Машины").getId(), searchText));
            theme = true;
        }
        if (education) {
            interviews.addAll(dao.getInterviewByThemeString(themeDAO.getThemeByName("Образование").getId(), searchText));
            theme = true;
        }
        if (technic) {
            interviews.addAll(dao.getInterviewByThemeString(themeDAO.getThemeByName("Техника").getId(), searchText));
            theme = true;
        }
        if (ethernet) {
            interviews.addAll(dao.getInterviewByThemeString(themeDAO.getThemeByName("Интернет").getId(), searchText));
            theme = true;
        }
        if (politic) {
            interviews.addAll(dao.getInterviewByThemeString(themeDAO.getThemeByName("Политика").getId(), searchText));
            theme = true;
        }
        if (fashion) {
            interviews.addAll(dao.getInterviewByThemeString(themeDAO.getThemeByName("Мода").getId(), searchText));
            theme = true;
        }

        if (!theme) {
            interviews.addAll(dao.getInterviewByString(searchText));
        }

        context.put("interviews", interviews);

        try {
            Render.render(response, context, "/search.ftl");
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
