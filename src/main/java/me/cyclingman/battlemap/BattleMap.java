package me.cyclingman.battlemap;

import me.cyclingman.battlemap.commands.CreateControlPointCommand;
import me.cyclingman.battlemap.commands.KitCommand;
import me.cyclingman.battlemap.commands.MenuCommand;
import me.cyclingman.battlemap.ultils.Teams;
import me.cyclingman.battlemap.gamemodes.Conquest;
import me.cyclingman.battlemap.kits.Archer;
import me.cyclingman.battlemap.listeners.FoodListener;
import me.cyclingman.battlemap.listeners.MenuListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class BattleMap extends JavaPlugin {

    public static BattleMap plugin;

    public static Conquest cq;

    @Override
    public void onEnable() {
        plugin = this;

        Teams.createTeams();

        //Setup/Load Config
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        //register the commands
        getCommand("kit").setExecutor(new KitCommand());
        getCommand("menu").setExecutor(new MenuCommand());
        getCommand("controlpoint").setExecutor(new CreateControlPointCommand());

        //register the events
        getServer().getPluginManager().registerEvents(new MenuListener(), this);
        getServer().getPluginManager().registerEvents(new Archer(), this);
        getServer().getPluginManager().registerEvents(new FoodListener(), this);
        getServer().getPluginManager().registerEvents(new Conquest(), this);

        cq = new Conquest();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
