package me.paradis.main;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class kitpvp98 extends JavaPlugin {

    private static kitpvp98 instance;

    FileConfiguration c = getConfig();

    @Override
    public void onEnable() {
        instance = this;

        String url = "jdbc:mysql://127.0.0.1:3306/kitpvp98";
        String user = "root";
        String password = "";

        // connect to database
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("connected to database");
            // works by adding password
        } catch (SQLException e) {
            e.printStackTrace();

            System.out.println("failed to connect to database");
        }


        // Plugin startup logic
        System.out.println("=========");
        System.out.println("starting kitpvp98");
        System.out.println("=========");

        addDefaults();

        getCommand("test1").setExecutor(new test1CommandManager());

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