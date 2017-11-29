package model.database;

import model.entities.Theme;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ThemeDAO extends AbstractDAO<Theme> {

    private static ThemeDAO dao;


    public static ThemeDAO getDao() {
        if (dao == null) {
            dao = new ThemeDAO();
        }
        return dao;
    }

    private ThemeDAO() {
        super();
    }


    @Override
    public void create(Theme theme) {
        try {
            PreparedStatement st = connection.prepareStatement(
                    "INSERT INTO themes (name) VALUES (?) RETURNING id;"
            );

            st.setString(1, theme.getName());

            st.execute();
            ResultSet rs = st.getResultSet();
            if (rs.next()) theme.setId(rs.getInt("id"));;

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Theme read(int id) {
        Theme theme = null;

        try {

            PreparedStatement st = connection.prepareStatement(
                    "SELECT * FROM themes WHERE id=?;"
            );

            st.setInt(1, id);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                theme = new Theme(
                        rs.getInt("id"),
                        rs.getString("name")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return theme;
    }

    @Override
    public void update(Theme theme) {
        try {
            PreparedStatement st = connection.prepareStatement(
                    "UPDATE themes SET name=? WHERE id=?;"
            );

            st.setString(1, theme.getName());
            st.setInt(2, theme.getId());

            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement st = connection.prepareStatement(
                    "DELETE FROM themes WHERE id=?;"
            );

            st.setInt(1, id);

            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Theme getThemeByName(String name) {
        Theme theme = null;

        try {

            PreparedStatement st = connection.prepareStatement(
                    "SELECT * FROM themes WHERE name=?;"
            );

            st.setString(1, name);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                theme = new Theme(
                        rs.getInt("id"),
                        rs.getString("name")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return theme;
    }
}
