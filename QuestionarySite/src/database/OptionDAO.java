package database;

import model.entity.Option;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OptionDAO extends AbstractDAO<Option> {

    private static OptionDAO dao;

    private Connection connection;

    public static OptionDAO getDao() {
        if (dao == null) {
            dao = new OptionDAO();

        }
        return dao;
    }

    @Override
    public void create(Option option) throws SQLException {
        PreparedStatement st = connection.prepareStatement(
                "INSERT INTO options (answer, img_path, description, interview_id, count_vote) " +
                        "VALUES (?, ?, ?, ?, ?);"
        );

        st.setString(1, option.getAnswer());
        st.setString(2, option.getImgPath());
        st.setString(3, option.getDescription());
        st.setInt(4, option.getInterviewId());
        st.setInt(5, option.getVoteCount());

        st.executeUpdate();
    }

    @Override
    public Option read(int id) throws SQLException {
        PreparedStatement st = connection.prepareStatement(
                "SELECT * FROM options WHERE id=?;"
        );

        st.setInt(1, id);

        ResultSet rs = st.executeQuery();

        rs.next();

        Option option = new Option(
                rs.getInt("id"),
                rs.getString("answer"),
                rs.getString("img_path"),
                rs.getString("description"),
                rs.getInt("interview_id"),
                rs.getInt("count_vote")
        );

        return option;
    }

    @Override
    public void update(Option option) throws SQLException {
        PreparedStatement st = connection.prepareStatement(
                "UPDATE options SET answer=?, img_path =?, description=?, interview_id=?, count_vote=? WHERE id=?;"
        );
        st.setString(1, option.getAnswer());
        st.setString(2, option.getImgPath());
        st.setString(3, option.getDescription());
        st.setInt(4, option.getInterviewId());
        st.setInt(5, option.getVoteCount());
        st.setInt(6, option.getId());

        st.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        PreparedStatement st = connection.prepareStatement(
                "DELETE FROM options WHERE id=?;"
        );
        st.setInt(1, id);

        st.executeUpdate();
    }
}
