package me.cyclingman.battlemap.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MenuCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player p = (Player) sender;

        //Inventory sizes(multiple of 9 up to 54)
        //9 18 27 36 45 54

        Inventory inventory = Bukkit.createInventory(p, 9, ChatColor.RED + "Potato Time");

        //ItemStacks are items in the game
        ItemStack beef = new ItemStack(Material.BEEF, 16);
        ItemMeta beefMeta = beef.getItemMeta();
        beefMeta.setDisplayName("Stop CPs");
        beef.setItemMeta(beefMeta);
        inventory.addItem(beef);


        //ItemMeta is the metadata of an item, such as the name, lore, and enchantments
        ItemStack flower = new ItemStack(Material.POPPY, 1);
        ItemMeta flowerMeta = flower.getItemMeta();
        flowerMeta.setDisplayName("Start CPs");

        flower.setItemMeta(flowerMeta);

        inventory.setItem(1, flower);

        p.openInventory(inventory);

        return true;
    }
}
