package me.paradis.main;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class testCommandManager implements CommandExecutor {

    private FileConfiguration c = kitpvp98.getInstance().getConfig();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // TODO Auto-generated method stub
        Player p = (Player) sender;
        sender.sendMessage("test");

        // create a gui that allows player to place items and when clicked a red wool calls "saveInv()"

        ChestGui gui = new ChestGui(4, "test");
        gui.setOnGlobalClick(event -> {
            System.out.println(event.getCurrentItem());
            event.setCancelled(true);
        });

        StaticPane pane = new StaticPane(0, 0, 9, 1);
        OutlinePane background = new OutlinePane(0, 1, 9, 3, Pane.Priority.LOWEST);

        pane.addItem(new GuiItem(new ItemStack(Material.RED_WOOL), event -> {
            System.out.println("clicked");
            // get a list of all items in background pane

            for (GuiItem item : background.getItems()){
                System.out.println(item.getItem());
            }
        }), 0, 0);
        gui.addPane(pane);

        for (int i = 0; i < 27; i++) {
            background.addItem(new GuiItem(new ItemStack(Material.BLACK_STAINED_GLASS_PANE), event -> {
                //background.getItems().set(event.getSlot() - 9,new GuiItem(new ItemStack(Material.APPLE)));
                    ChestGui gui1 = new ChestGui(4, "sel item");
                    gui1.setOnGlobalClick(event1 -> {
                        System.out.println(event1.getCurrentItem());
                        event1.setCancelled(true);
                    });
                    OutlinePane pane1 = new OutlinePane(0, 0, 9, 1);

                    List<Material> materials = new ArrayList<>();
                    materials.add(Material.DIAMOND_SWORD);
                    materials.add(Material.DIAMOND_AXE);
                    materials.add(Material.DIAMOND_PICKAXE);
                    materials.add(Material.DIAMOND_SHOVEL);
                    materials.add(Material.DIAMOND_HOE);
                    materials.add(Material.DIAMOND_HELMET);
                    materials.add(Material.DIAMOND_CHESTPLATE);
                    materials.add(Material.DIAMOND_LEGGINGS);
                    materials.add(Material.DIAMOND_BOOTS);
                    materials.add(Material.BOW);
                    materials.add(Material.CROSSBOW);
                    materials.add(Material.TRIDENT);
                    materials.add(Material.FISHING_ROD);
                    materials.add(Material.SHIELD);


                    for (Material material : materials) {
                        pane1.addItem(new GuiItem(new ItemStack(material), event1 -> {
                            System.out.println("clicked2");
                            // get a list of all items in background pane
                            background.getItems().set(event.getSlot() - 9, new GuiItem(new ItemStack(material), selItemEvent -> {
                                System.out.println(selItemEvent.getSlot());
                                ChestGui selItemGui = new ChestGui(4, "enchant");
                                selItemGui.setOnGlobalClick(selItemEvent1 -> {
                                    System.out.println(selItemEvent1.getCurrentItem());
                                    selItemEvent1.setCancelled(true);
                                });
                                OutlinePane selItemPane = new OutlinePane(0, 0, 9, 1);
                                ItemStack clickedItem = selItemEvent.getCurrentItem();
                                System.out.println(clickedItem);
                                Map<Enchantment, Integer> enchants = new HashMap<>();
                                enchants.put(Enchantment.DAMAGE_ALL, 4);
                                enchants.put(Enchantment.ARROW_DAMAGE, 2);
                                enchants.put(Enchantment.KNOCKBACK, 1);
                                enchants.put(Enchantment.DURABILITY, 2);
                                enchants.put(Enchantment.LOOT_BONUS_MOBS, 2);

                                System.out.println(enchants);
                                for(Map.Entry<Enchantment, Integer> entry : enchants.entrySet()) {
                                    System.out.println(entry.getKey() + " " + entry.getValue());
                                    Enchantment enchant = entry.getKey();
                                    int level = entry.getValue();
                                    //System.out.println(enchant.getKey().getKey() + " " + level);
                                    ItemStack enchantedBook = new ItemStack(Material.ENCHANTED_BOOK);
                                    ItemMeta meta = enchantedBook.getItemMeta();
                                    meta.addEnchant(enchant, level, true);
                                    enchantedBook.setItemMeta(meta);

                                    selItemPane.addItem(new GuiItem(enchantedBook, selItemEvent2 -> {
                                        System.out.println("clicked1");
                                        // get a list of all items in background pane
                                        ItemMeta meta1 = clickedItem.getItemMeta();
                                        meta1.addEnchant(enchant, level, true);
                                        clickedItem.setItemMeta(meta1);
                                        background.getItems().set(selItemEvent.getSlot() - 9, new GuiItem(clickedItem));

                                        gui.update();
                                        p.closeInventory();
                                        gui.show(p);
                                    }));
                                }
                                selItemGui.addPane(selItemPane);
                                selItemGui.show(p);
                            }));

                            gui.update();
                            p.closeInventory();
                            gui.show(p);
                        }));
                    }

                    gui1.addPane(pane1);
                    gui1.show(p);
            }));
        }


        gui.addPane(background);

        gui.show(p);
        return false;
    }

    public void saveInv(Inventory inv, Player p){
        System.out.println("saved");
        p.getInventory().setContents(inv.getContents());

    }

    public void setItem(Player p, OutlinePane pane, int index){
        System.out.println("set");
        ChestGui gui = new ChestGui(4, "test");
        gui.setOnGlobalClick(event -> {
            System.out.println(event.getCurrentItem());
            event.setCancelled(true);
        });
        // add an apple in x 0 y 0
        StaticPane pane1 = new StaticPane(0, 0, 9, 1);
        pane1.addItem(new GuiItem(new ItemStack(Material.APPLE), event -> {
            System.out.println("clicked");
            // get a list of all items in background pane
            pane.insertItem(new GuiItem(new ItemStack(Material.APPLE)), 12);



        }), 0, 0);

        gui.addPane(pane1);
        gui.show(p);
    }

}
