package database;

import model.entity.Interview;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InterviewDAO extends AbstractDAO<Interview> {

    private InterviewDAO dao;

    private Connection connection;

    @Override
    public void create(Interview interview) throws SQLException {
        PreparedStatement st = connection.prepareStatement(
                "INSERT INTO interviews (question, owner_id, date, is_archive, theme_id, check_type, is_anonim, can_comment)" +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?);"
        );

        st.setString(1, interview.getQuestion());
        st.setInt(2, interview.getOwnerId());
        st.setDate(3, interview.getDate());
        st.setBoolean(4, interview.isArchived());
        st.setInt(5, interview.getThemeId());
        st.setBoolean(6, interview.isTypeCheck());
        st.setBoolean(7, interview.isAnonymous());
        st.setBoolean(8, interview.isCanComment());

        st.executeUpdate();
    }

    @Override
    public Interview read(int id) throws SQLException {

        PreparedStatement st = connection.prepareStatement(
                "SELECT * FROM interviews WHERE id=?"
        );

        st.setInt(1, id);

        ResultSet rs = st.executeQuery();

        Interview interview = new Interview(
                rs.getInt("id"),
                rs.getString("question"),
                rs.getInt("owner_id"),
                rs.getDate("date"),
                rs.getBoolean("is_archeve"),
                rs.getInt("theme_id"),
                rs.getBoolean("check_type"),
                rs.getBoolean("is_anonim"),
                rs.getBoolean("can_comment")
        );

        return interview;
    }

    @Override
    public void update(Interview interview) throws SQLException {
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
    }

    @Override
    public void delete(int id) throws SQLException {
        PreparedStatement st = connection.prepareStatement(
                "DELETE FROM interviews WHERE id=?;"
        );

        st.setInt(1, id);

        st.executeUpdate();
    }
}
