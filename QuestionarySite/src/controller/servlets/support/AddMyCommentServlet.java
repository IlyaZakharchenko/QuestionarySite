package controller.servlets.support;

import model.database.CommentaryDAO;
import model.entities.Commentary;
import model.entities.User;
import support.ParameterDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AddMyCommentServlet", urlPatterns = "/add_my_comment")
public class AddMyCommentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("current_user");

        String content = ParameterDecoder.decodePostParameter(request.getParameter("content"));

        int interviewId = Integer.parseInt(request.getParameter("id"));

        Commentary commentary = new Commentary();

        commentary.setUserId(user.getId());
        commentary.setInterviewId(interviewId);
        commentary.setContent(content);
        commentary.setDate(new Date((new java.util.Date()).getTime()));

        CommentaryDAO dao = CommentaryDAO.getDao();

        dao.create(commentary);


        response.sendRedirect(request.getHeader("referer"));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
