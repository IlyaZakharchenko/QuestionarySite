package database;

import model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends AbstractDAO<User> {

    private Connection connection;

    private static UserDAO dao;

    public static UserDAO getDao() {
        if (dao == null) {
            dao = new UserDAO();
        }
        return dao;
    }

    @Override
    public void create(User user) throws SQLException {
        PreparedStatement st = connection.prepareStatement(
                "INSERT INTO users (login, password, username, age, about_yourself, img_path)" +
                        "VALUES (?, ?, ?, ?, ?, ?);"
        );

        st.setString(1, user.getLogin());
        st.setString(2, user.getPassword());
        st.setString(3, user.getUsername());
        st.setInt(4, user.getAge());
        st.setString(5, user.getAboutYourself());
        st.setString(6, user.getImgPath());

        st.executeUpdate();
    }

    @Override
    public User read(int id) throws SQLException {
        PreparedStatement st = connection.prepareStatement(
                "SELECT * FROM users WHERE id=?;"
        );

        st.setInt(1, id);

        ResultSet rs = st.executeQuery();

        User user = new User (
          rs.getInt("id"),
          rs.getString("login"),
          rs.getString("password"),
          rs.getString("username"),
          rs.getInt("age"),
          rs.getString("about_yourself"),
          rs.getString("img_path")
        );

        return user;
    }

    @Override
    public void update(User user) throws SQLException {
        PreparedStatement st = connection.prepareStatement(
                "UPDATE users SET login=?, password=?, username=?, age=?, about_yourself=?, img_path=? WHERE id=?;"
        );

        st.setString(1, user.getLogin());
        st.setString(2, user.getPassword());
        st.setString(3, user.getUsername());
        st.setInt(4, user.getAge());
        st.setString(5, user.getAboutYourself());
        st.setString(6, user.getImgPath());
        st.setInt(7, user.getId());

        st.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        PreparedStatement st = connection.prepareStatement(
                "DELETE FROM users WHERE id=?;"
        );

        st.setInt(1, id);

        st.executeUpdate();
    }
}
