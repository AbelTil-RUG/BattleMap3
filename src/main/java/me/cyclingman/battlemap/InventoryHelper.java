package me.cyclingman.battlemap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;

public class InventoryHelper {

    public int getAmountOfItem(Player player, Material material) {
        int amount = 0;
        Bukkit.getLogger().info("fetching items");
        Collection<ItemStack> items = (Collection<ItemStack>) player.getInventory().all(material).values();
        Bukkit.getLogger().info("counting items");
        for (ItemStack item : items) {
            amount += item.getAmount();
        }
        Bukkit.getLogger().info("returning count");
        return amount;
    }


}
