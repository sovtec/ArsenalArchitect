package me.paradis.main.sql;

import me.paradis.main.tools.Items;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SQLManager {

    /*

        Querys are executed in sync with the main thread, TODO, replace with async example:

public CompletableFuture<ResultSet> runQueryAsync(String query) {
    return CompletableFuture.supplyAsync(() -> {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            statement.close();
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    });
}

public void getPlayer(String player) {
    runQueryAsync("SELECT * FROM players WHERE playerName='" + player + "';")
        .thenAcceptAsync(resultSet -> {
            try {
                if (resultSet.next()) {
                    String playerName = resultSet.getString("playerName");
                    // Use the playerName variable
                }
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
}

        this would improve performance, but it's not necessary for now
        remember ACID, Atomicity, Consistency, Isolation, Durability

     */

    private Connection connection;
    String url;

    public SQLManager(String url) throws SQLException {
        this.url = url;
        setup();

    }

    public Connection getConnection() throws SQLException {
        if (connection != null) return connection;

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
        String createKitItems = "CREATE TABLE IF NOT EXISTS kit_item (id INTEGER PRIMARY KEY AUTOINCREMENT, kit_id INTEGER, slot_number INTEGER, value BLOB, FOREIGN KEY (kit_id) REFERENCES kits(id));";
        statement.execute(createKitItems);


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

    public void saveNewKitItem(int kit_id, int slot_number, byte[] value) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement("INSERT INTO kit_item (kit_id, slot_number, value) VALUES (?, ?, ?);");
        statement.setInt(1, kit_id);
        statement.setInt(2, slot_number);
        statement.setBytes(3, value);

        statement.executeUpdate();

        statement.close();
    }

    public Map<Integer, ItemStack> getPlayerItemsFromKit(int kitID) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM kit_item WHERE kit_id = ?");
        statement.setInt(1, kitID);

        ResultSet resultSet = statement.executeQuery();
        Map<Integer, ItemStack> items = new HashMap<>();

        while (resultSet.next()) {
            int slot_number = resultSet.getInt("slot_number");
            byte[] value = resultSet.getBytes("value");
            ItemStack item;
            try {
                item = Items.deserialize(value);
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            items.put(slot_number, item);
        }

        return items;
    }
}
