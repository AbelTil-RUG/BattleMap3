package me.cyclingman.battlemap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class Catalog implements Serializable {

    @Serial
    private static final long serialVersionUID = -1681012206529286330L;

    public static final String SUFFIX = ".bms";
    private final List<Map> maps;

    public Catalog() {
        this.maps = new ArrayList<>();
    }

    public boolean addMap(Map map) {
        if (getMap(map.getName()) == null) {
            maps.add(map);
            return true;
        } else {
            return false;
        }
    }

    public boolean removeMap(String mapName) {
        for (Map map : maps) {
            if (map.getName().equalsIgnoreCase(mapName)) {
                maps.remove(map);
                return true;
            }
        }
        return false;
    }

    public void printMaps(CommandSender recipient) {
        recipient.sendMessage(ChatColor.BOLD + "Maps in catalog:");
        for (Map map : maps) {
            recipient.sendMessage(" - " + map.getName());
        }
    }

    public Map getMap(String mapName) {
        for (Map map: maps) {
            if (map.getName().equalsIgnoreCase(mapName)) {
                return map;
            }
        }
        return null;
    }

    public void deactivate() {
        for (Map map : maps) {
            map.getGamemode().deactivate();
        }
    }

    public boolean save(String fileName) {
        String filePath = BattleMap.plugin.getConfig().getString(ConfigPaths.SAVE_PATH) + File.separator + fileName + SUFFIX;
        try {
            BukkitObjectOutputStream out = new BukkitObjectOutputStream(new GZIPOutputStream(new FileOutputStream(filePath)));
            out.writeObject(this);
            out.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            Bukkit.getLogger().warning("Save Failed!");
            return false;
        }
    }
    public static boolean load(String fileName) {
        String filePath = BattleMap.plugin.getConfig().getString(ConfigPaths.SAVE_PATH) + File.separator + fileName + SUFFIX;
        try {
            BukkitObjectInputStream in = new BukkitObjectInputStream(new GZIPInputStream(new FileInputStream(filePath)));
            Catalog catalog = (Catalog) in.readObject();
            in.close();
            BattleMap.plugin.setCatalog(catalog);
            return true;
        } catch (ClassNotFoundException | IOException e) {
            Bukkit.getLogger().warning("Load Failed. Does a save exist?");
            return false;
        }
    }
}
