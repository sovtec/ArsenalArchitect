package me.paradis.main.sql;

import javax.annotation.Nullable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLManager {

    private Connection connection;
    String url;

    public SQLManager(String url) throws SQLException {
        this.url = url;
        setup();

    }

    public Connection getConnection() throws SQLException {
        if(connection != null) return connection;

        //Try to connect to my MySQL database running locally
        Connection connection = DriverManager.getConnection(url);

        this.connection = connection;

        System.out.println("Connected to database.");

        return connection;
    }

    public void setup() throws SQLException {
        // setup database
        // tested in https://sqliteonline.com

        // Create a query with table players and columns id int autoincrement primary key, name varchar and uuid as varchar
        Statement statement = getConnection().createStatement();
        String createPlayers = "CREATE TABLE IF NOT EXISTS players (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, uuid VARCHAR);";
        statement.execute(createPlayers);
        String createKits = "CREATE TABLE IF NOT EXISTS kits (id INTEGER PRIMARY KEY AUTOINCREMENT, player_id INTEGER, name VARCHAR, FOREIGN KEY (player_id) REFERENCES players(id));";
        statement.execute(createKits);

        statement.close();


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

    public List<String> getKits(String uuid) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement("SELECT name FROM kits WHERE player_id = ?");
        statement.setString(1, uuid);

        ResultSet resultSet = statement.executeQuery();
        List<String> kitNames = new ArrayList<>();

        while (resultSet.next()) {
            kitNames.add(resultSet.getString("name"));
        }

        statement.close();

        return kitNames;
    }

    public void saveNewKit(String name, String uuid) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement("INSERT INTO kits (player_id, name) VALUES (?, ?);");
        statement.setString(1, uuid);
        statement.setString(2, name);

        statement.executeUpdate();

        statement.close();
    }


}
