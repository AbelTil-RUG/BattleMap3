package me.cyclingman.battlemap.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodListener implements Listener {

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent e) {
        if (e.getFoodLevel() < 20) {
            e.getEntity().setSaturation(20);
            e.getEntity().setFoodLevel(20);
            e.getEntity().sendMessage("EATEN");
            Bukkit.getLogger().info("eaten");
        }
    }
}
