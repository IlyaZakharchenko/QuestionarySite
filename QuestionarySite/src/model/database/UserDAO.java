package model.database;

import model.entities.User;

import java.lang.annotation.Inherited;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends AbstractDAO<User> {

    private static UserDAO dao;


    public UserDAO() {
        super();
    }

    public static UserDAO getDao() {
        if (dao == null) {
            dao = new UserDAO();
        }
        return dao;
    }

    @Override
    public void create(User user) {
        try {
            PreparedStatement st = connection.prepareStatement(
                    "INSERT INTO users (email, password, username, age, about_yourself, img_path)" +
                            "VALUES (?, ?, ?, ?, ?, ?);"
            );

        st.setString(1, user.getEmail());
        st.setString(2, user.getPassword());
        st.setString(3, user.getUsername());
        st.setInt(4, user.getAge());
        st.setString(5, user.getAboutYourself());
        st.setString(6, user.getImgPath());

        st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User read(int id) {

        ResultSet rs = null;

        try {
            PreparedStatement st = connection.prepareStatement(
                    "SELECT * FROM users WHERE id=?;"
            );

            st.setInt(1, id);

            rs = st.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return fillUser(rs);
    }

    @Override
    public void update(User user) {
        try {
            PreparedStatement st = connection.prepareStatement(
                    "UPDATE users SET username=?, password=?, username=?, age=?, about_yourself=?, img_path=? WHERE id=?;"
            );

            st.setString(1, user.getEmail());
            st.setString(2, user.getPassword());
            st.setString(3, user.getUsername());
            st.setInt(4, user.getAge());
            st.setString(5, user.getAboutYourself());
            st.setString(6, user.getImgPath());
            st.setInt(7, user.getId());

            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement st = connection.prepareStatement(
                    "DELETE FROM users WHERE id=?;"
            );

        st.setInt(1, id);

        st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserByCookie(String cookie) {

        ResultSet rs = null;

        try {
            PreparedStatement st = connection.prepareStatement(
                    "SELECT * FROM users WHERE cookie=?;"
            );

        st.setString(1, cookie);

        rs = st.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return fillUser(rs);
    }

    public User getUserByUsername(String username) {

        User user = null;

        try {
            PreparedStatement st = connection.prepareStatement(
                    "SELECT * FROM users WHERE username=?;"
            );

            st.setString(1, username);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                user = new User(
                        rs.getInt("id"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("username"),
                        rs.getInt("age"),
                        rs.getString("about_yourself"),
                        rs.getString("img_path"),
                        rs.getString("cookie")
                );
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }


    public User getUserByEmail(String email) {
        ResultSet rs = null;
        try {
            PreparedStatement st = connection.prepareStatement(
                    "SELECT * FROM users WHERE email=?;"
            );

            st.setString(1, email);

            rs = st.executeQuery();


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return fillUser(rs);
    }

    public void updateCookieByUsername(String username, String cookie) {
        try {
            PreparedStatement st = connection.prepareStatement(
                    "UPDATE users SET cookie=? WHERE username=?"
            );

        st.setString(1, cookie);
        st.setString(2, username);

        st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private User fillUser(ResultSet rs) {

        User user = null;

        try {
            if(rs.next()) {
                user = new User(
                        rs.getInt("id"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("username"),
                        rs.getInt("age"),
                        rs.getString("about_yourself"),
                        rs.getString("img_path"),
                        rs.getString("cookie")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
