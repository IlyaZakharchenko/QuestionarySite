package controller.servlets.ajax;

import model.database.UserDAO;
import model.entities.User;
import org.json.JSONException;
import org.json.JSONObject;
import support.HashGen;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "AjaxLoginCheckServlet", urlPatterns = "/ajax_check_login")
public class AjaxLoginCheckServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");

        String password = request.getParameter("password");

        boolean rememberMe = Boolean.parseBoolean(request.getParameter("remember_me"));

        JSONObject jo = new JSONObject();

        UserDAO dao = UserDAO.getDao();
        User user = dao.getUserByUsername(username);

        boolean wrongPassName = !checkLogin(password, user);

        boolean userNamePattern = username.matches(".*\\W+.*");

        boolean passPattern = password.matches(".*\\W+.*");

        try {
            jo.put("error_msg", wrongPassName);
            jo.put("username_pattern_err", userNamePattern);
            jo.put("pass_pattern_err", passPattern);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        if (!(wrongPassName || userNamePattern || passPattern)) {
            request.getSession().setAttribute("current_user", user);
            if (rememberMe) {
                String cookieStr = String.valueOf(UUID.randomUUID());
                Cookie c = new Cookie("user_id", cookieStr);
                c.setMaxAge(24 * 3600);

                response.addCookie(c);

                dao.updateCookieByUsername(username, cookieStr);
            }
        }

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/json");
        response.getWriter().print(jo.toString());
        response.getWriter().close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private boolean checkLogin(String password, User user) {

        HashGen hashGen = new HashGen(password);

        return user != null && hashGen.md5Hash().equals(user.getPassword());

    }
}
