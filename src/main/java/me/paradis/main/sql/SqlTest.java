package me.paradis.main.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlTest {

    private Connection connection;

    String url = "jdbc:sqlite:kitpvp98.db";
    //String url = "jdbc:mysql://127.0.0.1:3306/kitpvp98";

    String user = "root";
    String password = "";

    //getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS players (uuid VARCHAR(36), username VARCHAR(16))").executeUpdate();

    public Connection getConnection() throws SQLException {
        if(connection != null){
            return connection;
        }

        //Try to connect to my MySQL database running locally
        Connection connection = DriverManager.getConnection(url);

        this.connection = connection;

        System.out.println("Connected to database.");

        return connection;
    }

    // notes keep in here for future reference
    /*
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("test")) {
            if (args[0].equalsIgnoreCase("add")) {
                Player p = (Player) sender;
                String uuid = p.getUniqueId().toString();
                String username = p.getName();
                //String query = "INSERT INTO players (uuid, username) VALUES ('" + uuid + "', '" + username + "')";
                PreparedStatement statement = null;
                try {
                    statement = getConnection().prepareStatement("INSERT INTO players (uuid, username) VALUES (?, ?)");
                    statement.setString(1, uuid);
                    statement.setString(2, username);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else if (args[0].equalsIgnoreCase("set")){
                //String query = "UPDATE players SET " + args[2] + " = " + args[3] + " WHERE uuid = '" + args[4] + "'";
                PreparedStatement statement = null;
                try {
                    //statement = getConnection().prepareStatement("UPDATE players SET " + args[2] + " = " + args[3] + " WHERE uuid = '" + args[4] + "'");
                    statement = getConnection().prepareStatement("INSERT INTO players (uuid, username) VALUES (?, ?)");
                    statement.setString(1, args[1]);
                    statement.setString(2, args[2]);

                    statement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else if (args[0].equalsIgnoreCase("get")){
                // get player from database
                //String query = "SELECT * FROM players WHERE uuid = '" + args[2] + "'";
                PreparedStatement statement = null;
                try {
                    statement = getConnection().prepareStatement("SELECT * FROM players");
                    ResultSet result = statement.executeQuery();
                    while (result.next()){
                        System.out.println(result.getString("uuid"));
                        System.out.println(result.getString("username"));
                    }
                    statement.close();
                    return false;


                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }
        }
        return false;
    }
     */
}
