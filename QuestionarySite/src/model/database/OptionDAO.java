package model.database;

import model.entities.Option;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OptionDAO extends AbstractDAO<Option> {

    private static OptionDAO dao;

    private OptionDAO() {
        super();
    }

    public static OptionDAO getDao() {
        if (dao == null) {
            dao = new OptionDAO();

        }
        return dao;
    }

    @Override
    public void create(Option option) {
        try {
            PreparedStatement st = connection.prepareStatement(
                    "INSERT INTO options (answer, img_path, description, interview_id, count_vote) " +
                            "VALUES (?, ?, ?, ?, ?) RETURNING id;"
            );

            st.setString(1, option.getAnswer());
            st.setString(2, option.getImgPath());
            st.setString(3, option.getDescription());
            st.setInt(4, option.getInterviewId());
            st.setInt(5, option.getVoteCount());

            st.execute();
            ResultSet rs = st.getResultSet();
            if (rs.next()) option.setId(rs.getInt("id"));

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Option read(int id) {
        Option option = null;
        try {

            PreparedStatement st = connection.prepareStatement(
                    "SELECT * FROM options WHERE id=?;"
            );

            st.setInt(1, id);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                option = new Option(
                        rs.getInt("id"),
                        rs.getString("answer"),
                        rs.getString("img_path"),
                        rs.getString("description"),
                        rs.getInt("interview_id"),
                        rs.getInt("count_vote")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return option;
    }

    @Override
    public void update(Option option) {
        try {
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

        List<String> links = option.getLinks();

        for (String link : links) {
            createLink(link, option.getId());
        }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createLink(String link, int optionId) {
        try {
            PreparedStatement st = connection.prepareStatement(
                    "INSERT INTO links (link, option_id) VALUES (?, ?)"
            );

            st.setString(1, link);
            st.setInt(2, optionId);
            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement st = connection.prepareStatement(
                    "DELETE FROM options WHERE id=?;"
            );
        st.setInt(1, id);

        st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Option getOptionByAnswer(String answer) {
        Option option = null;
        try {

            PreparedStatement st = connection.prepareStatement(
                    "SELECT * FROM options WHERE answer=?;"
            );

            st.setString(1, answer);

            ResultSet rs = st.executeQuery();


            if (rs.next()) {
                option = new Option(
                        rs.getInt("id"),
                        rs.getString("answer"),
                        rs.getString("img_path"),
                        rs.getString("description"),
                        rs.getInt("interview_id"),
                        rs.getInt("count_vote")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return option;
    }

    public List<Option> getOptionsByInterviewId(int interviewId) {
        List<Option> options = new ArrayList<>();
        try {

            PreparedStatement st = connection.prepareStatement(
                    "SELECT * FROM options WHERE interview_id=?;"
            );

            st.setInt(1, interviewId);

            ResultSet rs = st.executeQuery();


            while (rs.next()) {
                Option option = new Option(
                        rs.getInt("id"),
                        rs.getString("answer"),
                        rs.getString("img_path"),
                        rs.getString("description"),
                        rs.getInt("interview_id"),
                        rs.getInt("count_vote")
                );
                options.add(option);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return options;
    }
}
