package controller.servlets.ajax;

import model.database.UserDAO;
import model.entities.User;
import org.json.JSONException;
import org.json.JSONObject;
import support.HashGen;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AjaxSignUpServlet", urlPatterns = "/ajax_sign_up")
public class AjaxSignUpServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");

        String email = request.getParameter("email");

        String password = request.getParameter("password1");

        String confirmPass = request.getParameter("password2");

        JSONObject jo = new JSONObject();

        boolean emailErr = !email.matches(".*@(mail\\.ru|yandex\\.ru|google\\.com)");
        boolean passLength = password.length() < 6;
        boolean passNotEqual = !password.equals(confirmPass);

        UserDAO dao = UserDAO.getDao();

        boolean userExists = dao.getUserByUsername(username) != null;

        boolean emailExists = dao.getUserByEmail(email) != null;

        boolean userNamePattern = username.matches(".*\\W+.*");

        boolean passPattern = password.matches(".*\\W+.*");
        try {
            jo.put("email_pattern_err", emailErr);
            jo.put("username_pattern_err", userNamePattern);
            jo.put("pass_pattern_err", passPattern);
            jo.put("pass_length_err", passLength);
            jo.put("user_exists_err", userExists);
            jo.put("pass_not_equal_err", passNotEqual);
            jo.put("email_exists_err", emailExists);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (!(emailErr || passLength || userExists || passNotEqual || emailExists || userNamePattern || passPattern)) {
            HashGen hashGen = new HashGen(password);

            User user = new User(0, email, hashGen.md5Hash(), username, 0, null, null, null);

            dao.create(user);

            request.getSession().setAttribute("current_user", user);

        }

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/json");
        response.getWriter().print(jo.toString());
        response.getWriter().close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
