package me.cyclingman.battlemap;

import me.cyclingman.battlemap.commands.*;
import me.cyclingman.battlemap.ultils.Teams;
import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.plugin.java.JavaPlugin;

public final class BattleMap extends JavaPlugin {

    public static BattleMap plugin;

    private Catalog catalog;

    @Override
    public void onEnable() {
        plugin = this;

        Teams.createTeams();

        //Setup/Load Config
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        Bukkit.createWorld(new WorldCreator("BattleMap2.0"));


        if (!Catalog.load(getConfig().getString("defaultSaveName"))) {
            catalog = new Catalog();
        }

        //register the commands
        getCommand("controlpoint").setExecutor(new ControlPointCommand(this));
        getCommand("feature").setExecutor(new FeatureCommand(this));
        getCommand("map").setExecutor(new MapCommand(this));
        getCommand("catalog").setExecutor(new CatalogCommand(this));

    }

    @Override
    public void onDisable() {
        catalog.save(getConfig().getString("defaultSaveName"));
        catalog.deactivate();
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }
}
