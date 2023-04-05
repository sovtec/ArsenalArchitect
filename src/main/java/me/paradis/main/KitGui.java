package me.paradis.main;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.inventory.InventoryClickEvent;
import org.bukkit.configuration.file.FileConfiguration;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;

import java.io.InputStream;

public class KitGui {

    private ChestGui gui;

    public KitGui(JavaPlugin plugin) {
        InputStream inputStream = plugin.getResource("kit_gui.xml");
        gui = ChestGui.load(plugin, inputStream);

        gui.setOnGlobalClick(event -> {
            event.setCancelled(true);
            handleItemClick(event);
        });
    }

    public void showGui(Player player, JavaPlugin plugin) {
        gui.show(player);
    }

    private void handleItemClick(InventoryClickEvent event) {
    }
}