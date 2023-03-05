package me.cyclingman.battlemap.commands;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public class CreateControlPointCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player p) {
            World world = p.getWorld();
            ArmorStand armorStand = (ArmorStand) world.spawnEntity(p.getLocation(), EntityType.ARMOR_STAND);
            armorStand.addScoreboardTag("control_point_tag");
        }
        return true;
    }
}
