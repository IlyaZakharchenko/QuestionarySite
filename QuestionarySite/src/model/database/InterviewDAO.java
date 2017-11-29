package model.database;

import model.entities.Interview;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class InterviewDAO extends AbstractDAO<Interview> {

    private static InterviewDAO dao;

    private InterviewDAO() {
        super();
    }

    public static InterviewDAO getDao() {
        if (dao == null) {
            dao = new InterviewDAO();
        }
        return dao;
    }

    @Override
    public void create(Interview interview) {
        try {
            PreparedStatement st = connection.prepareStatement(
                    "INSERT INTO interviews (question, owner_id, date, is_archive, theme_id, check_type, is_anonim, can_comment)" +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?) RETURNING id;"
            );

            st.setString(1, interview.getQuestion());
            st.setInt(2, interview.getOwnerId());
            st.setDate(3, interview.getDate());
            st.setBoolean(4, interview.isArchived());
            st.setInt(5, interview.getThemeId());
            st.setBoolean(6, interview.isTypeCheck());
            st.setBoolean(7, interview.isAnonymous());
            st.setBoolean(8, interview.isCanComment());

            st.execute();
            ResultSet rs = st.getResultSet();
            if (rs.next()) interview.setId(rs.getInt("id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Interview read(int id) {
        Interview interview = null;

        try {
            PreparedStatement st = connection.prepareStatement(
                    "SELECT * FROM interviews WHERE id=?"
            );

            st.setInt(1, id);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                interview = fillInterview(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return interview;
    }

    @Override
    public void update(Interview interview) {
        try {
            PreparedStatement st = connection.prepareStatement(
                    "UPDATE interviews " +
                            "SET question=?, owner_id=?, date=?, is_archive=?, theme_id=?, check_type=?, is_anonim=?, can_comment=?" +
                            " WHERE id=?;"
            );

            st.setString(1, interview.getQuestion());
            st.setInt(2, interview.getOwnerId());
            st.setDate(3, interview.getDate());
            st.setBoolean(4, interview.isArchived());
            st.setInt(5, interview.getThemeId());
            st.setBoolean(6, interview.isTypeCheck());
            st.setBoolean(7, interview.isAnonymous());
            st.setBoolean(8, interview.isCanComment());
            st.setInt(9, interview.getId());

            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Interview getInterviewByQuestion(String question) {
        Interview interview = null;

        try {
            PreparedStatement st = connection.prepareStatement(
                    "SELECT * FROM interviews WHERE question=?"
            );

            st.setString(1, question);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                interview = fillInterview(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return interview;
    }

    public List<Interview> getInterviewsByOwnerId(int owner_id) {
        List<Interview> interviews = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(
                    "SELECT * FROM interviews WHERE owner_id=?"
            );

            st.setInt(1, owner_id);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                interviews.add(fillInterview(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return interviews;
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement st = connection.prepareStatement(
                    "DELETE FROM interviews WHERE id=?;"
            );

            st.setInt(1, id);

            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Interview fillInterview(ResultSet rs) {
        Interview interview = null;

        try {
            interview = new Interview(
                    rs.getInt("id"),
                    rs.getString("question"),
                    rs.getInt("owner_id"),
                    rs.getDate("date"),
                    rs.getBoolean("is_archive"),
                    rs.getInt("theme_id"),
                    rs.getBoolean("check_type"),
                    rs.getBoolean("is_anonim"),
                    rs.getBoolean("can_comment")
            );
            interview.setOptions(OptionDAO.getDao().getOptionsByInterviewId(interview.getId()));
            interview.setCommentaries(CommentaryDAO.getDao().getCommentsByInterviewId(interview.getId()));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return interview;
    }

    public List<Interview> getInterviewByThemeString(int themeId, String string) {
        List<Interview> interviews = new ArrayList<>();

        try {
            PreparedStatement st = connection.prepareStatement(
                    "SELECT * FROM interviews WHERE theme_id=? AND lower(question) LIKE ?;"
            );

            st.setInt(1, themeId);
            st.setString(2, string);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Interview interview = fillInterview(rs);
                interviews.add(interview);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return interviews;
    }

    public List<Interview> getInterviewByString(String string) {
        List<Interview> interviews = new ArrayList<>();

        try {
            PreparedStatement st = connection.prepareStatement(
                    "SELECT * FROM interviews WHERE  lower(question) LIKE ?;"
            );

            st.setString(1, string);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Interview interview = fillInterview(rs);
                interviews.add(interview);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return interviews;
    }

    public List<Interview> getAll() {
        List<Interview> interviews = new ArrayList<>();

        try {
            PreparedStatement st = connection.prepareStatement(
                    "SELECT * FROM interviews;"
            );


            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Interview interview = fillInterview(rs);
                interviews.add(interview);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return interviews;
    }
}
