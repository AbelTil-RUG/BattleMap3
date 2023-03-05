package me.cyclingman.battlemap.kits;

import me.cyclingman.battlemap.InventoryHelper;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;


public class Archer implements Kit, Listener {
    public final String KIT_NAME = "archer";



    @Override
    public void setKit(Player p) {
        p.sendMessage(KIT_NAME + " kit loaded!");
        p.getInventory().addItem(new ItemStack(Material.ICE,1));
        p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
        p.setHealth(20);

        Bukkit.dispatchCommand(p, "give @s arrow 3");
    }

    @Override
    public String getKitName() {
        return KIT_NAME;
    }

    @EventHandler
    public void onArrowShot(EntityShootBowEvent e) {
        Entity entity = e.getEntity();
        if (entity instanceof Player p) {
            p.sendMessage("SHOTS FIRED!");
            int amount = new InventoryHelper().getAmountOfItem(p, Material.ARROW);
            p.sendMessage(String.valueOf(amount));


        }
    }

}
