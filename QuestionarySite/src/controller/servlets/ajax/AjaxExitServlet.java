package controller.servlets.ajax;

import model.database.UserDAO;
import model.entities.User;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AjaxExitServlet", urlPatterns = "/ajax_exit")
public class AjaxExitServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("current_user");

        UserDAO dao = UserDAO.getDao();

        if (user.getCookie() != null && !user.getCookie().equals("")) {
            dao.updateCookieByUsername(user.getUsername(), null);

            Cookie cookie = new Cookie("user_id", null);
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }

        request.getSession().setAttribute("current_user", null);
        JSONObject jo = new JSONObject();

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/json");
        response.getWriter().println(jo.toString());
        response.getWriter().close();
    }
}
