package me.cyclingman.battlemap;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class MapCatalog implements Serializable {

    @Serial
    private static final long serialVersionUID = -1681012206529286330L;
    private List<Map> maps;

    int nextId;

    public MapCatalog() {
        this.maps = new ArrayList<>();
        nextId = 1;
    }

    public int addMap(Map map) {
        maps.add(map);
        return nextId++;
    }

    public void removeMap(int id) {
        if (id >= maps.size()) {
            return;
        }
        maps.remove(id);
    }

    public String getMapNames() {
        String message = String.valueOf(maps.size());
        for (Map map : maps) {
            message = message + map.getName() + " (" + map.getId() + ")  -  ";
        }
        return message;
    }

    public boolean save() {
        String filePath = BattleMap.plugin.getConfig().getString(ConfigPaths.SAVE_PATH);
        Bukkit.broadcast(Component.text(filePath));
        try {
            BukkitObjectOutputStream out = new BukkitObjectOutputStream(new GZIPOutputStream(new FileOutputStream(filePath)));
            out.writeObject(this);
            out.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Save Failed");
            return false;
        }
    }
    public static MapCatalog load() {
        String filePath = BattleMap.plugin.getConfig().getString(ConfigPaths.SAVE_PATH);
        Bukkit.broadcast(Component.text(filePath));
        try {
            BukkitObjectInputStream in = new BukkitObjectInputStream(new GZIPInputStream(new FileInputStream(filePath)));
            MapCatalog catalog = (MapCatalog) in.readObject();
            in.close();
            return catalog;
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            System.out.println("Load Failed");
            return new MapCatalog();
        }
    }
}
