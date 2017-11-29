package controller.servlets.pages;

import freemarker.template.TemplateException;
import model.database.InterviewDAO;
import model.database.OptionDAO;
import model.database.ThemeDAO;
import model.entities.Interview;
import model.entities.Option;
import model.entities.Theme;
import model.entities.User;
import support.ParameterDecoder;
import support.Render;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "CreateInterviewServlet", urlPatterns = "/create")
public class CreateInterviewServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User currentUser = (User) request.getSession().getAttribute("current_user");

        int count = 1;
        Interview interview = new Interview();
        interview.setQuestion(ParameterDecoder.decodePostParameter(request.getParameter("question-name")));

        InterviewDAO dao = InterviewDAO.getDao();


        java.util.Date utilDate = new java.util.Date();
        interview.setDate(new Date(utilDate.getTime()));
        interview.setOwner(currentUser);
        interview.setOwnerId(currentUser.getId());
        interview.setAnonymous(ParameterDecoder.decodePostParameter(request.getParameter("anonim")).equals("Да"));
        interview.setTypeCheck(ParameterDecoder.decodePostParameter(request.getParameter("typeCheck")).equals("Cookie"));
        interview.setCanComment(ParameterDecoder.decodePostParameter(request.getParameter("canComment")).equals("Все"));

        ThemeDAO themeDAO = ThemeDAO.getDao();

        Theme theme = themeDAO.getThemeByName(ParameterDecoder.decodePostParameter(request.getParameter("theme")));

        interview.setTheme(theme);
        interview.setThemeId(theme.getId());

        OptionDAO optionDAO = OptionDAO.getDao();

        dao.create(interview);

        List<String> links;

        String optionStr = ParameterDecoder.decodePostParameter(request.getParameter("var" + count));
        while (!"".equals(optionStr)){
            int count1 = 1;
            links = new ArrayList<>();
            String descrStr = ParameterDecoder.decodePostParameter(request.getParameter("description" + count));
            Option option = new Option(optionStr, descrStr, interview.getId(), 0);
            optionDAO.create(option);
            String link = ParameterDecoder.decodePostParameter(request.getParameter("link" + count + "_" + count1));
            while (!"".equals(link)) {
                links.add(link);
                count1++;
                link = request.getParameter("link" + count + "_" + count1);
            }
            option.setLinks(links);
            count++;
            optionStr = ParameterDecoder.decodePostParameter(request.getParameter("var" + count));
        }


        List<Interview> interviews = currentUser.getInterviews();
        if (interviews == null) interviews = new ArrayList<>();
        interviews.add(interview);
        currentUser.setInterviews(interviews);

        request.getSession().setAttribute("current_user", currentUser);

        response.sendRedirect("/profile?id=" + currentUser.getId());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> context = new HashMap<>();

        User currentUser = (User) request.getSession().getAttribute("current_user");

        if (currentUser != null) {
            context.put("user", currentUser);
        }

        context.put("cur_page", "create");


        try {
            Render.render(response, context, "/create_interview.ftl");
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
