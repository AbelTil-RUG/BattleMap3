package me.cyclingman.battlemap.commands;

import me.cyclingman.battlemap.BattleMap;
import me.cyclingman.battlemap.Catalog;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;

/**
 * The command responsible for IO operations on the catalog.
 */
public class CatalogCommand implements CommandExecutor {

    private final BattleMap plugin;

    public CatalogCommand(BattleMap plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length < 1) return false;

        if (args[0].equalsIgnoreCase("create")) {
            return createCommand(sender, Arrays.stream(args).skip(1L).toArray(String[]::new));
        }

        if (args[0].equalsIgnoreCase("save")) {
            return saveCommand(sender, Arrays.stream(args).skip(1L).toArray(String[]::new));
        }

        if (args[0].equalsIgnoreCase("load")) {
            return loadCommand(sender, Arrays.stream(args).skip(1L).toArray(String[]::new));
        }

        if (args[0].equalsIgnoreCase("delete")) {
            return deleteCommand(sender, Arrays.stream(args).skip(1L).toArray(String[]::new));
        }

        if (args[0].equalsIgnoreCase("list")) {
            return listCommand(sender);
        }

        return false;
    }

    private boolean createCommand(CommandSender sender, String[] args) {
        if (args.length > 0 && args[0].equalsIgnoreCase("confirm")) {
            plugin.setCatalog(new Catalog());
            sender.sendMessage("Created new catalog.");
            return true;
        } else {
            sender.sendMessage("Are you sure you want to overwrite the current catalog?");
            sender.sendMessage(" To confirm, please type /catalog create confirm");
            return true;
        }
    }
    private boolean saveCommand(CommandSender sender, String[] args) {
        String fileName;
        if (args.length < 1) {
            fileName = plugin.getConfig().getString("defaultSaveName");
        } else {
            fileName = args[0];
        }

        if (plugin.getCatalog().save(fileName)) {
            sender.sendMessage("Successfully saved catalog.");
        } else {
            sender.sendMessage("Failed to save catalog. Look at server log to find potential problem.");
        }
        return true;
    }

    private boolean loadCommand(CommandSender sender, String[] args) {
        String fileName;
        if (args.length < 1) {
            fileName = plugin.getConfig().getString("defaultSaveName");
        } else {
            fileName = args[0];
        }

        if (Catalog.load(fileName)) {
            sender.sendMessage("Successfully loaded catalog.");
        } else {
            sender.sendMessage("Failed to load catalog. Look at server log to find potential problem.");
        }
        return true;
    }

    private boolean deleteCommand(CommandSender sender, String[] args) {
        if (args.length > 1 && args[1].equalsIgnoreCase("confirm")) {
            String fileName = args[0];

            if (!fileName.endsWith(Catalog.SUFFIX)) {
                fileName += Catalog.SUFFIX;
            }

            File myObj = new File(plugin.getConfig().getString("savePath") + File.separator + fileName);
            if (myObj.delete()) {
                sender.sendMessage("Sucessfully deleted file.");
            } else {
                sender.sendMessage("Failed to delete file.");
            }
            return true;
        } else {
            sender.sendMessage("Are you sure you want to overwrite the current catalog?");
            sender.sendMessage(" To confirm, please type /catalog delete <fileName> confirm");
            return true;
        }

    }

    private boolean listCommand(CommandSender sender) {
        File folder = new File(Objects.requireNonNull(plugin.getConfig().getString("savePath")));
        File[] listOfFiles = folder.listFiles();
        sender.sendMessage("All available files:");
        for (File file: listOfFiles) {
            if (file.isFile() && file.getName().endsWith(Catalog.SUFFIX)) {
                sender.sendMessage(file.getName());
            }
        }
        return true;
    }
}
