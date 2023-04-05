package me.paradis.main;

import me.paradis.main.sql.SQLManager;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class Kitpvp98 extends JavaPlugin implements CommandExecutor {

    private static Kitpvp98 instance;
    private static SQLManager sqlManager;
    String url = "jdbc:sqlite:kitpvp98.db";

    FileConfiguration c = getConfig();


    @Override
    public void onEnable() {
        instance = this;

        // Plugin startup logic
        getLogger().info("=========");
        getLogger().info("starting ArsenalArchitect");
        getLogger().info("=========");

        addDefaults();



        // register command /aa
        getCommand("aa").setExecutor(new CommandManager());

        try {
            sqlManager = new SQLManager(url);
            sqlManager.setup();
            System.out.println("Connected to database.");
        } catch (SQLException e) {
            throw new RuntimeException(e); // TODO log error
        }
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("stopping kitpvp98");
        saveConfig();
    }

    public void addDefaults(){
        this.saveDefaultConfig();

        // this will add the default messages to the config
        c.addDefault(Messages.TEST.name(), "test");
        // to send a message to the player use player.sendMessage(messages.TEST.name());
        // .name() is used to get the name of the enum

        c.options().copyDefaults(true);

        saveConfig();
    }

    public static Kitpvp98 getInstance() {
        return instance;
    }

    public static SQLManager getSqlManager() {
        return sqlManager;
    }

}
