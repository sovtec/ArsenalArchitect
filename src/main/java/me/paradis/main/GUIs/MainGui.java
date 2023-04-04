package me.paradis.main.GUIs;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import me.paradis.main.Kitpvp98;
import me.paradis.main.sql.SQLManager;
import me.paradis.main.tools.Items;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.sql.SQLException;
import java.util.List;

public class MainGui {

    SQLManager sql = Kitpvp98.getSqlManager();

    public MainGui(Player p){
        // ideally player never leaves the gui until done with all actions

        // Kitpvp98.getSqlManager().executeQuery("SELECT * FROM players WHERE uuid = '" + p.getUniqueId().toString() + "'");
        ChestGui gui = new ChestGui(6, "Kit Selector");
        gui.setOnGlobalClick(event -> event.setCancelled(true));
        // background ?

        StaticPane pane = new StaticPane(0, 0, 9, 3);


        // get all kits from player, and add them to the gui, if not found add a default kit
        List<String> kitNames = getPlayerKitNames(p);
        int cont = 2;
        for (String kitName : kitNames) {
            // #HR remake lore and name
            ItemStack kit = Items.createItem(Material.BOOK, "kit " + (cont / 2), 1, kitName, " " , "Right click to edit", "Left click to select");

            Integer finalCont = cont / 2;
            GuiItem guiItem = new GuiItem(kit, event -> {

                if (event.isLeftClick()) selectKit(p, finalCont);
                // TODO rename lore to selected kit

                if (event.isRightClick()) {
                    try {
                        new EditKitGui(sql.getPlayerItemsFromKit(finalCont), p, finalCont);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }

            });
            pane.addItem(guiItem, cont, 1);

            cont += 2;
        }



        gui.addPane(pane);

        gui.show(p);
    }

    private void selectKit(Player p, Integer finalCont) {
        // TODO select kit
        // #HR set message
        p.sendMessage("You selected kit " + finalCont); // finalCont is the id of kit
        p.closeInventory();
    }

    private List<String> getPlayerKitNames(Player player) {
        List<String> kitNames;
        try {
            kitNames = sql.getKits(player.getUniqueId().toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // add "undefined" to the list until it has a length of 3
        while (kitNames.size() < 3) {
            player.sendMessage("adding undefined kit");
            kitNames.add("[undefined]"); // #HR rename default kit name

        }
        // return first 3 kits
        return kitNames.subList(0, 3);
    }

}
