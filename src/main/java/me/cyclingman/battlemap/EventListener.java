package me.cyclingman.battlemap;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class EventListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.getPlayer().chat("test this out!");
        KitHandler.setScout(event.getPlayer());
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Location location = event.getPlayer().getLocation();
        location.createExplosion(5);
        event.getPlayer().setMaxHealth(10);
        KitHandler.removeKit(event.getPlayer());
    }

    @EventHandler
    public void onLeftClickWithCarrot(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        player.getInventory().addItem(new ItemStack(Material.CARROT,10));
        if (event.getAction() == Action.LEFT_CLICK_AIR && event.getMaterial() == Material.CARROT) {
            player.giveExpLevels(-1);
        }
        if (event.getAction() == Action.RIGHT_CLICK_AIR && event.getMaterial() == Material.NETHER_STAR) {
            player.giveExpLevels(1);
        }
    }

}
