package me.paradis.main;

import me.paradis.main.GUIs.GuiManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CommandManager implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (!(sender instanceof Player)) {
            // #HR set message
            sender.sendMessage("You must be a player to use this command"); // #HR set message
            return false;
        }

        Player p = (Player) sender;
        //Kitpvp98.getSqlManager().saveNewPlayer(p.getName(), p.getUniqueId().toString());
        //ResultSet resultSet = Kitpvp98.getSqlManager().getAllPlayers();


        return true;
    }
}
