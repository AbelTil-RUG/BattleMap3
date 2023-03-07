package me.cyclingman.battlemap;

import me.cyclingman.battlemap.commands.ControlPointCommand;
import me.cyclingman.battlemap.commands.MapCommand;
import me.cyclingman.battlemap.commands.FeatureCommand;
import me.cyclingman.battlemap.commands.CatalogCommand;
import me.cyclingman.battlemap.ultils.Teams;
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


        if (!Catalog.load(getConfig().getString("defaultSaveName"))) {
            catalog = new Catalog();
        }

        //register the commands
        getCommand("controlpoint").setExecutor(new ControlPointCommand(this));
        getCommand("mapfeature").setExecutor(new FeatureCommand(this));
        getCommand("map").setExecutor(new MapCommand(this));
        getCommand("catalog").setExecutor(new CatalogCommand(this));

    }

    @Override
    public void onDisable() {
        catalog.save(getConfig().getString("defaultSaveName"));
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }
}
