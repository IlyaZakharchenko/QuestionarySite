package database;

import model.entity.Commentary;

import java.sql.*;

public class CommentaryDAO extends AbstractDAO<Commentary> {

    private static CommentaryDAO dao;

    private Connection connection;

    public static CommentaryDAO getDao() {

        if (dao == null) {
            dao = new CommentaryDAO();
        }
        return dao;
    }

    @Override
    public Commentary read(int id) throws SQLException {
        PreparedStatement pst = connection.prepareStatement(
                "SELECT * FROM commentaries WHERE commentaries.id=?;"
        );
        pst.setInt(1, id);

        ResultSet rs = pst.executeQuery();

        rs.next();

        Commentary comment = new Commentary(
                rs.getInt("id"),
                rs.getString("content"),
                rs.getInt("user_id"),
                rs.getInt("interview_id"),
                rs.getDate("date")
        );

        return comment;
    }

    public void update(Commentary commentary) throws SQLException {
        PreparedStatement st = connection.prepareStatement(
                "UPDATE commentaries SET content=?, user_id =?, interview_id=?, date=? WHERE id=?;"
        );
        st.setString(1, commentary.getContent());
        st.setInt(2, commentary.getUserId());
        st.setInt(3, commentary.getInterviewId());
        st.setDate(4, commentary.getDate());
        st.setInt(5, commentary.getId());

        st.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        PreparedStatement st = connection.prepareStatement(
                "DELETE FROM commentaries WHERE id=?;"
        );
        st.setInt(1, id);

        st.executeUpdate();
    }

    public void create(Commentary commentary) throws SQLException {

        PreparedStatement st = connection.prepareStatement(
                "INSERT INTO commentaries (content, user_id, interview_id, date)" +
                        "VALUES (?, ?, ?, ?);"
        );
        st.setString(1, commentary.getContent());
        st.setInt(2, commentary.getUserId());
        st.setInt(3, commentary.getInterviewId());
        st.setDate(4, commentary.getDate());

        st.executeUpdate();
    }
}
