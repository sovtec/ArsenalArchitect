package me.paradis.main;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class _98 extends JavaPlugin {

    public static _98 instance;
    FileConfiguration c = getConfig();

    @Override
    public void onEnable() {
        instance = this;
        // Plugin startup logic
        System.out.println("starting 98");

        addDefaults();

        // register command "test"
        this.getCommand("test").setExecutor(new testCommandManager());
        this.getCommand("test1").setExecutor(new test1CommandManager());
        this.getCommand("get").setExecutor(new requestsTest());


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("stopping 98");
        saveConfig();
    }

    public void addDefaults(){
        this.saveDefaultConfig();


        c.options().copyDefaults(true);

        saveConfig();
    }

    public static _98 getInstance() {
        return instance;
    }
}
