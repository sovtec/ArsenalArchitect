package me.paradis.main.extras;

import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import me.paradis.main.Kitpvp98;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.InputStream;

public class test1CommandManager implements CommandExecutor {

    private final Kitpvp98 instance = Kitpvp98.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // TODO Auto-generated method stub




        Player p = (Player) sender;
        sender.sendMessage("test123123");

        if (args.length == 0) {
            return false;
        }


        InputStream stream = Kitpvp98.getInstance().getResource("gui.xml");
        System.out.println(stream);
        ChestGui gui = ChestGui.load(this, stream);

        gui.setOnGlobalClick(event -> {
            System.out.println(event.getCurrentItem());

            // if event is 0 1 or 2
            if (event.getSlot() == 0 || event.getSlot() == 1 || event.getSlot() == 2) {
                event.getWhoClicked().getInventory().addItem(event.getCurrentItem());
            } else if (event.getSlot() == 54) {
                System.out.println("next page");
                event.setCancelled(true);
            } else {
                event.setCancelled(true);
            }

        });

        gui.show(p);

        return false;
    }

}
