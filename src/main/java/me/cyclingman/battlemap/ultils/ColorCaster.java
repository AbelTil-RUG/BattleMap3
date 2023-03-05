package me.cyclingman.battlemap.ultils;

import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.boss.BarColor;

import static net.kyori.adventure.text.format.NamedTextColor.*;

public class ColorCaster {
    public static BarColor toBarColor(TextColor color) {
        if (color.equals(RED) || color.equals(DARK_RED)) {
            return BarColor.RED;
        } else if (color.equals(BLUE) || color.equals(AQUA) || color.equals(DARK_AQUA) || color.equals(DARK_BLUE)) {
            return BarColor.BLUE;
        } else if (color.equals(DARK_PURPLE)) {
            return BarColor.PURPLE;
        } else if (color.equals(GREEN) || color.equals(DARK_GREEN)) {
            return BarColor.GREEN;
        } else if (color.equals(LIGHT_PURPLE)) {
            return BarColor.PINK;
        } else if (color.equals(GOLD) || color.equals(YELLOW)) {
            return BarColor.YELLOW;
        }
        return BarColor.WHITE;
    }

    public static Color toColor(TextColor color) {
        if (color.equals(RED)) {
            return Color.RED;
        } else if (color.equals(AQUA)) {
            return Color.AQUA;
        } else if (color.equals(WHITE)) {
            return Color.WHITE;
        } else {
            Bukkit.getLogger().warning("Color not yet castable!");
            return Color.WHITE;
        }
    }

    public static ChatColor toChatColor(TextColor color) {
        if (color.equals(RED)) {
            return ChatColor.RED;
        } else if (color.equals(AQUA)) {
            return ChatColor.AQUA;
        } else if (color.equals(WHITE)) {
            return ChatColor.WHITE;
        } else {
            Bukkit.getLogger().warning("Color not yet castable!");
            return ChatColor.WHITE;
        }
    }
}
