package me.paradis.main;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class arsenalArchitect extends JavaPlugin {

    public static arsenalArchitect instance;
    FileConfiguration c = getConfig();

    @Override
    public void onEnable() {
        instance = this;
        // Plugin startup logic
        System.out.println("starting Arsenal Architect");

        addDefaults();

        // register command "test"
        this.getCommand("test").setExecutor(new testCommandManager());
        this.getCommand("test1").setExecutor(new test1CommandManager());
        this.getCommand("get").setExecutor(new requestsTest());
        this.getCommand("kit").setExecutor(new kitCommandManager(this));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("stopping Arsenal Architect");
        saveConfig();
    }

    public void addDefaults() {
        this.saveDefaultConfig();

        c.options().copyDefaults(true);

        saveConfig();
    }

    public static arsenalArchitect getInstance() {
        return instance;
    }
}
