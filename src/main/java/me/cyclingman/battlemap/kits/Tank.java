package me.cyclingman.battlemap.kits;

import org.bukkit.entity.Player;

public class Tank implements Kit {
    public final String KIT_NAME = "tank";

    @Override
    public void setKit(Player p) {
        p.sendMessage(KIT_NAME + " kit loaded.");
    }

    @Override
    public String getKitName() {
        return KIT_NAME;
    }
}
