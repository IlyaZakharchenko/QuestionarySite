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

@WebServlet(name = "AjaxPassChangeServlet", urlPatterns = "/ajax_change_pass")
public class AjaxPassChangeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String newPass = request.getParameter("newPass");

        String oldPass = request.getParameter("oldPass");

        String repeatPass = request.getParameter("repeatPass");

        JSONObject jo = new JSONObject();

        boolean passLength = newPass.length() < 6;
        boolean passNotEqual = !newPass.equals(repeatPass);
        boolean passEquals = newPass.equals(oldPass);
        boolean passPattern = newPass.matches(".*\\W+.*");

        HashGen hashGen = new HashGen(oldPass);

        UserDAO dao = UserDAO.getDao();

        User user = (User) request.getSession().getAttribute("current_user");

        boolean wrongPass = !user.getPassword().equals(hashGen.md5Hash());
        try {
            jo.put("pass_pattern_err", passPattern);
            jo.put("pass_length_err", passLength);
            jo.put("pass_not_equal_err", passNotEqual);
            jo.put("old_new_equal_err", passEquals);
            jo.put("wrong_pass_err", wrongPass);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (!(passEquals || passLength || passNotEqual || passPattern || wrongPass)) {
            HashGen hashGen1 = new HashGen(newPass);
            user.setPassword(hashGen1.md5Hash());
            dao.updatePasswordById(hashGen1.md5Hash(), user.getId());
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
