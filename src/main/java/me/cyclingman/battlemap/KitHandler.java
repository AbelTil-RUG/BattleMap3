package me.cyclingman.battlemap;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

public class KitHandler {

    public static void removeKit(Player player) {
        player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.10);
    }

    public static void setScout(Player player) {
        player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.20);
    }
}
