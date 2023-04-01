package me.paradis.main;

import me.paradis.main.GUIs.GuiManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandManager implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (!(sender instanceof Player)) {
            // #HR set message
            sender.sendMessage("You must be a player to use this command");
            return false;
        }

        Player p = (Player) sender;
        if (args.length == 0) {
            GuiManager.openMainGUI(p);



            return false;
        }

        return false;
    }
}
