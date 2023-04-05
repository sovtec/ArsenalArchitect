package me.paradis.main;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class kitCommandManager implements CommandExecutor {

    private JavaPlugin plugin;

    public kitCommandManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.sendMessage("kit");

            KitGui customGui = new KitGui(plugin);
            customGui.showGui(player, plugin);
        } else {
            sender.sendMessage("This command can only be executed by a player.");
        }

        return true;
    }
}