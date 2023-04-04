package me.paradis.main.tools;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class Items {

    public static ItemStack createItem(Material material, String name, int amount, String... lore ) {
        ItemStack item = new ItemStack(material, amount);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(lore));
        item.setItemMeta(meta);

        return item;
    }

    public static byte[] serialize(ItemStack itemStack) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);
        dataOutput.writeObject(itemStack);
        dataOutput.close();
        return outputStream.toByteArray();
    }

    public static ItemStack deserialize(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
        BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
        ItemStack itemStack = (ItemStack) dataInput.readObject();
        dataInput.close();
        return itemStack;
    }

    /*
    ItemStack a = Items.createItem(Material.DIAMOND_SWORD, "Sword", 1,"test", "test2");
            System.out.println("item: " + a);
            byte[] b;
            try {
                b = serialize(a);
                System.out.println("byte[] " + Arrays.toString(b));

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                ItemStack c = deserialize(b);
                System.out.println("item c: " + c);
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
     */
}
