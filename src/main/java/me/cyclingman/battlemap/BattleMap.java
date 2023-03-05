package me.cyclingman.battlemap;

import me.cyclingman.battlemap.commands.ControlPointCommand;
import me.cyclingman.battlemap.commands.SaveLoadCommand;
import me.cyclingman.battlemap.ultils.Teams;
import org.bukkit.plugin.java.JavaPlugin;

public final class BattleMap extends JavaPlugin {

    public static BattleMap plugin;

    public MapCatalog catalog;

    @Override
    public void onEnable() {
        plugin = this;

        Teams.createTeams();

        //Setup/Load Config
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        //register the commands
        getCommand("controlpoint").setExecutor(new ControlPointCommand());
        getCommand("saves").setExecutor(new SaveLoadCommand());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public MapCatalog load() {
        catalog = MapCatalog.load();
        return catalog;
    }

    public void save() {
        catalog.save();
    }

    public void create() {
        catalog = new MapCatalog();
    }
}
