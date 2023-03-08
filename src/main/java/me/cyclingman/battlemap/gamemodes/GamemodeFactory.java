package me.cyclingman.battlemap.gamemodes;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class GamemodeFactory {

    public static final String CONQUEST_FULL = "Conquest";
    public static final String CONQUEST_SHORT = "cq";

    public static final String KING_OF_THE_HILL_FULL = "King of the hill";
    public static final String KING_OF_THE_HILL_SHORT = "koth";

    public static final String CAPTURE_THE_FLAG_FULL = "Capture the flag";
    public static final String CAPTURE_THE_FLAG_SHORT = "ctf";

    public static final String SPECIAL_DELIVERY_FULL = "Special delivery";
    public static final String SPECIAL_DELIVERY_SHORT = "sd";

    public static final String CONTROL_POINTS_FULL = "Control points";
    public static final String CONTROL_POINTS_SHORT = "cp";

    public static final String DEATH_MATCH_FULL = "Death match";
    public static final String DEATH_MATCH_SHORT = "dm";

    public static void printGamemodes(CommandSender sender) {
        sender.sendMessage(ChatColor.BOLD + "Currently implemented gamemodes:");
        sender.sendMessage(createGamemodeListItem(CONQUEST_FULL, CONQUEST_SHORT));
        sender.sendMessage(createGamemodeListItem(KING_OF_THE_HILL_FULL, KING_OF_THE_HILL_SHORT));
        sender.sendMessage(createGamemodeListItem(CAPTURE_THE_FLAG_FULL, CAPTURE_THE_FLAG_SHORT));
        sender.sendMessage(createGamemodeListItem(SPECIAL_DELIVERY_FULL, SPECIAL_DELIVERY_SHORT));
        sender.sendMessage(createGamemodeListItem(CONTROL_POINTS_FULL, CONTROL_POINTS_SHORT));
        sender.sendMessage(createGamemodeListItem(DEATH_MATCH_FULL, DEATH_MATCH_SHORT));
    }

    public static Gamemode createGamemode(String gamemodeName) {
        switch (gamemodeName.toLowerCase()) {
            case CONQUEST_SHORT:
            case CAPTURE_THE_FLAG_SHORT:
            case SPECIAL_DELIVERY_SHORT:
            case CONTROL_POINTS_SHORT:
            case DEATH_MATCH_SHORT:
                break;
            case KING_OF_THE_HILL_SHORT:
                return new KOTH();
        }
        return null;
    }

    public static String createGamemodeListItem(String full, String shorthand) {
        return " - " + full + " (" + shorthand + ")";
    }
}
