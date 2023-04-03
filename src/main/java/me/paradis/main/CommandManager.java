package me.paradis.main;

import me.paradis.main.GUIs.MainGui;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
        if (args.length == 0) { // #HR set message,sound and perm if needed
            new MainGui(p);
        } else {
            try {
                Kitpvp98.getSqlManager().saveNewKit(args[0], p.getUniqueId().toString());
                p.sendMessage("You created a new kit called " + args[0]);
            } catch (SQLException e) {
                p.sendMessage(" ERROR: " + e.getMessage());
                throw new RuntimeException(e);
            }


        }


        return true;
    }
}
