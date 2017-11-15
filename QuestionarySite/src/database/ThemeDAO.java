package database;

import model.entity.Theme;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ThemeDAO extends AbstractDAO<Theme> {

    private static ThemeDAO dao;

    private Connection connection;

    public static ThemeDAO getDao() {
        if (dao == null) {
            dao = new ThemeDAO();
        }
        return dao;
    }


    @Override
    public void create(Theme theme) throws SQLException {
        PreparedStatement st = connection.prepareStatement(
                "INSERT INTO themes (name) VALUES (?);"
        );

        st.setString(1, theme.getName());

        st.executeUpdate();
    }

    @Override
    public Theme read(int id) throws SQLException {
        PreparedStatement st = connection.prepareStatement(
                "SELECT * FROM themes WHERE id=?;"
        );

        st.setInt(1, id);

        ResultSet rs = st.executeQuery();

        Theme theme = new Theme(
                rs.getInt("id"),
                rs.getString("name")
        );

        return theme;
    }

    @Override
    public void update(Theme theme) throws SQLException {
        PreparedStatement st = connection.prepareStatement(
                "UPDATE themes SET name=? WHERE id=?;"
        );

        st.setString(1, theme.getName());
        st.setInt(2, theme.getId());

        st.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        PreparedStatement st = connection.prepareStatement(
                "DELETE FROM themes WHERE id=?;"
        );

        st.setInt(1, id);

        st.executeUpdate();
    }
}
