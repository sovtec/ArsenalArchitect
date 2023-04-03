package me.paradis.main.sql;

import javax.annotation.Nullable;
import java.sql.*;

public class SQLManager {

    private Connection connection;
    String url;

    public SQLManager(String url) throws SQLException {
        this.url = url;
        connection = getConnection();

    }

    public void setup() throws SQLException {
        // setup database

        // Create a query with table players and columns id int autoincrement primary key, name varchar and uuid as varchar
        String query = "CREATE TABLE IF NOT EXISTS players (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, uuid VARCHAR);";
        getConnection().createStatement().execute(query);

    }

    // execute query
    public void executeQuery(String query) throws SQLException {
        //connection.prepareStatement(query).executeUpdate(); remove this
    }

    public void saveNewPlayer(String name, String uuid) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement("INSERT INTO players (name, uuid) VALUES (?, ?);");
        statement.setString(1, name);
        statement.setString(2, uuid);
        statement.executeUpdate();
        statement.close();
    }

    public ResultSet getAllPlayers() throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM players;");
        ResultSet resultSet = statement.executeQuery();
        statement.close();

        return resultSet;
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
