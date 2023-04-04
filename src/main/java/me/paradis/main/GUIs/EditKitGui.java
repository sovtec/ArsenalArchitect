package me.paradis.main.GUIs;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import com.github.stefvanschie.inventoryframework.pane.util.Slot;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;

public class EditKitGui {

    /**
     * @param items a map of items to be added to the gui with the key being the slot number
     */
    public EditKitGui(Map<Integer, ItemStack> items, Player p, Integer kitNumber) {

        ChestGui gui = new ChestGui(6, "Edit Kit " + kitNumber);
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        StaticPane pane = new StaticPane(0, 0, 9, 6);


        for (Map.Entry<Integer, ItemStack> entry : items.entrySet()) {
            Integer slot = entry.getKey();
            ItemStack item = entry.getValue();


            pane.addItem(new GuiItem(item, event -> {
                // TODO add item to player inventory
                // TODO remove item from gui
                event.getWhoClicked().sendMessage("clicked on " + slot);
                // use slot to know if its armor offhand or inv
            }), Slot.fromIndex(slot));

        }

        gui.addPane(pane);

        gui.show(p);

    }
}
