package me.paradis.main;

import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.InputStream;

public class test1CommandManager implements CommandExecutor {

    private final _98 instance = _98.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // TODO Auto-generated method stub
        Player p = (Player) sender;
        sender.sendMessage("test1");

        InputStream stream = _98.getInstance().getResource("gui.xml");
        System.out.println(stream);
        ChestGui gui = ChestGui.load(this, stream);

        gui.setOnGlobalClick(event -> event.setCancelled(true));

        gui.show(p);

        return false;
    }

}
