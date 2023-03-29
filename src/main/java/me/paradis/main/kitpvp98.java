package me.paradis.main;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.*;

public final class kitpvp98 extends JavaPlugin implements CommandExecutor {

    private static kitpvp98 instance;

    FileConfiguration c = getConfig();
    String url = "jdbc:sqlite:kitpvp98.db";

    private Connection connection;


    @Override
    public void onEnable() {
        instance = this;

        //String url = "jdbc:mysql://127.0.0.1:3306/kitpvp98";
        String user = "root";
        String password = "";

        // connect to database
        try {
            //Connection connection = DriverManager.getConnection(url);
            getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS players (uuid VARCHAR(36), username VARCHAR(16))").executeUpdate();
            System.out.println("Connected to database and created table.");

        } catch (SQLException e) {
            e.printStackTrace();

            System.out.println("failed to connect to database");
        }


        // Plugin startup logic
        System.out.println("=========");
        System.out.println("starting kitpvp98");
        System.out.println("=========");

        addDefaults();

        //getCommand("test1").setExecutor(new test1CommandManager());
        // register command /test in this file that inserts a player into the database
        getCommand("test").setExecutor(this);


    }

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

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("stopping kitpvp98");
        saveConfig();
    }

    public void addDefaults(){
        this.saveDefaultConfig();


        c.options().copyDefaults(true);

        saveConfig();
    }

    public static kitpvp98 getInstance() {
        return instance;
    }

}


/*
                        // remove nbti tag PublicBukkitValues from item
                        NBTItem nbtItem = new NBTItem(item);
                        nbtItem.removeKey("PublicBukkitValues");
                        item = nbtItem.getItem();
 */