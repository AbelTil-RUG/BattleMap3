package me.cyclingman.battlemap.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TPCommand implements CommandExecutor {

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player p) {
            if (args.length > 0) {
                World world = Bukkit.getWorld(args[0]);
                if (world == null) {
                    p.sendMessage("Map name is not found.");
                    return true;
                }
                Location location = new Location(world,0,0,0);
                p.teleport(location);
                return true;
            }
        }
        return false;
    }
}
