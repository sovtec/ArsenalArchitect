package me.paradis.main.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLManager {

    Connection connection;
    String url;

    public SQLManager(String url) throws SQLException {
        this.url = url;
        this.connection = getConnection();

    }

    public static void setup() throws SQLException {
        // setup database
    }

    // execute query
    public void executeQuery(String query) throws SQLException {
        //connection.prepareStatement(query).executeUpdate(); remove this
    }

    public Connection getConnection() throws SQLException {
        if(connection != null) return connection;

        //Try to connect to my MySQL database running locally
        Connection connection = DriverManager.getConnection(url);

        this.connection = connection;

        System.out.println("Connected to database.");

        return connection;
    }
}
