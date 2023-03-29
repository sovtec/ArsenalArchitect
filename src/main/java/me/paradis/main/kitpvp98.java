package me.paradis.main;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class kitpvp98 extends JavaPlugin {

    private static kitpvp98 instance;

    FileConfiguration c = getConfig();

    @Override
    public void onEnable() {
        instance = this;
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