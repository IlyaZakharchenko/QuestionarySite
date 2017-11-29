package model.database;

import model.entities.Commentary;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentaryDAO extends AbstractDAO<Commentary> {

    private static CommentaryDAO dao;

    public static CommentaryDAO getDao() {

        if (dao == null) {
            dao = new CommentaryDAO();
        }
        return dao;
    }

    private CommentaryDAO() {
        super();
    }

    @Override
    public Commentary read(int id) {
        Commentary comment = null;

        try {
            PreparedStatement pst = connection.prepareStatement(
                    "SELECT * FROM commentaries WHERE commentaries.id=?;"
            );
        pst.setInt(1, id);

        ResultSet rs = pst.executeQuery();

        if (rs.next()) {

            comment = new Commentary(
                    rs.getInt("id"),
                    rs.getString("content"),
                    rs.getInt("user_id"),
                    rs.getInt("interview_id"),
                    rs.getDate("date")
            );
        }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return comment;
    }

    public void update(Commentary commentary) {
        try {
            PreparedStatement st = connection.prepareStatement(
                    "UPDATE commentaries SET content=?, user_id =?, interview_id=?, date=? WHERE id=?;"
            );
        st.setString(1, commentary.getContent());
        st.setInt(2, commentary.getUserId());
        st.setInt(3, commentary.getInterviewId());
        st.setDate(4, commentary.getDate());
        st.setInt(5, commentary.getId());

        st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement st = connection.prepareStatement(
                    "DELETE FROM commentaries WHERE id=?;"
            );
        st.setInt(1, id);

        st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void create(Commentary commentary) {

        try {
            PreparedStatement st = connection.prepareStatement(
                    "INSERT INTO commentaries (content, user_id, interview_id, date)" +
                            "VALUES (?, ?, ?, ?) RETURNING id;"
            );
        st.setString(1, commentary.getContent());
        st.setInt(2, commentary.getUserId());
        st.setInt(3, commentary.getInterviewId());
        st.setDate(4, commentary.getDate());

            st.execute();
            ResultSet rs = st.getResultSet();
            if (rs.next()) commentary.setId(rs.getInt("id"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Commentary> getCommentsByInterviewId(int interviewId) {
        List<Commentary> comments = new ArrayList<>();

        try {
            PreparedStatement pst = connection.prepareStatement(
                    "SELECT * FROM commentaries WHERE interview_id=?;"
            );
            pst.setInt(1, interviewId);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                Commentary comment = new Commentary(
                        rs.getInt("id"),
                        rs.getString("content"),
                        rs.getInt("user_id"),
                        rs.getInt("interview_id"),
                        rs.getDate("date")
                );
                comment.setUser(UserDAO.getDao().read(comment.getUserId()));
                comments.add(comment);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return comments;
    }
}
