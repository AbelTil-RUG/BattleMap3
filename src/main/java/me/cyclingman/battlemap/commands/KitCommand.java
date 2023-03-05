package me.cyclingman.battlemap.commands;

import me.cyclingman.battlemap.kits.*;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class KitCommand implements CommandExecutor {

    private final List<Kit> kits;

    public KitCommand () {
        kits = new ArrayList<>();
        kits.add(new Archer());
        kits.add(new Bomber());
        kits.add(new Scout());
        kits.add(new Sniper());
        kits.add(new Tank());
        kits.add(new Wizard());
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player p) {
                if (args.length == 0) {
                    p.sendMessage(ChatColor.RED + "No kit is provided.");
                    return false;
                }
            for (Kit kit : kits) {
                if (args[0].equalsIgnoreCase(kit.getKitName())) {
                    kit.setKit(p);
                    return true;
                }
            }
            p.sendMessage(ChatColor.RED + "No valid kit name is provided.");
        } else {
            sender.sendMessage("This can only be executed as a player.");
        }
        return true;
    }
}
