package model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class AbstractDAO<T> {

    Connection connection;

    public AbstractDAO() {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/interview_site",
                    "postgres",
                    "post");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public abstract void create(T object);

    public abstract T read(int id);

    public abstract void update(T object);

    public abstract void delete(int id);
}
