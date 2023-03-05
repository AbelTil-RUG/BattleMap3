package me.cyclingman.battlemap.listeners;

import me.cyclingman.battlemap.BattleMap;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public class MenuListener implements Listener {

    @EventHandler
    public void preventAdventurerEditInventory(InventoryClickEvent e) {
        Player p = (Player)e.getWhoClicked();
        if (p.getGameMode() == GameMode.ADVENTURE) {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onMenuClick(InventoryClickEvent e) {

        if(e.getView().getTitle().equalsIgnoreCase(ChatColor.RED + "Potato Time")){

            //Make sure the player clicked on an item and not on the inventory
            if(e.getCurrentItem() == null){
                return;
            }

            //See what item they clicked on by getting the material of the item
            if (e.getCurrentItem().getType() == Material.POPPY){
                BattleMap.cq.activate();
            } else if (e.getCurrentItem().getType() == Material.BEEF){
                BattleMap.cq.deactive();
            }

            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onOffHandSwap(PlayerSwapHandItemsEvent e) {
        if (e.getPlayer().getGameMode() == GameMode.ADVENTURE) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDropItem(PlayerDropItemEvent e) {
        if (e.getPlayer().getGameMode() == GameMode.ADVENTURE) {
            e.setCancelled(true);
        }
    }

}