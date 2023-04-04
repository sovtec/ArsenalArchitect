package me.paradis.main;

import me.paradis.main.GUIs.MainGui;
import me.paradis.main.sql.SQLManager;
import me.paradis.main.tools.Items;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

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

            ItemStack item = Items.createItem(Material.DIAMOND_AXE, "Axe", 1, "test", "test2");
            ItemStack item1 = Items.createItem(Material.DIAMOND_SWORD, "Sword", 1, "test", "test2");
            ItemStack item2 = Items.createItem(Material.DIAMOND_PICKAXE, "Pickaxe", 1, "test", "test2");
            try {
                SQLManager sql = Kitpvp98.getSqlManager();

                sql.saveNewKitItem(1,3, Items.serialize(item));
                sql.saveNewKitItem(1,5, Items.serialize(item1));
                sql.saveNewKitItem(1,7, Items.serialize(item2));

                p.sendMessage("saved items");
            } catch (SQLException | IOException e) {
                throw new RuntimeException(e);
            }


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
