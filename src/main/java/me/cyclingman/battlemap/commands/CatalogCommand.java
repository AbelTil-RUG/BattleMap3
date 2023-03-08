package me.cyclingman.battlemap.commands;

import me.cyclingman.battlemap.BattleMap;
import me.cyclingman.battlemap.Catalog;
import org.bukkit.ChatColor;
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

        switch (args[0].toLowerCase()) {
            case "create":
                createCommand(sender, Arrays.stream(args).skip(1L).toArray(String[]::new));
                return true;
            case "save":
                saveCommand(sender, Arrays.stream(args).skip(1L).toArray(String[]::new));
                return true;
            case "load":
                loadCommand(sender, Arrays.stream(args).skip(1L).toArray(String[]::new));
                return true;
            case "delete":
                deleteCommand(sender, Arrays.stream(args).skip(1L).toArray(String[]::new));
                return true;
            case "list":
                listCommand(sender);
                return true;
        }

        return false;
    }

    private void createCommand(CommandSender sender, String[] args) {
        if (args.length > 0 && args[0].equalsIgnoreCase("confirm")) {
            plugin.setCatalog(new Catalog());
            sender.sendMessage(ChatColor.GREEN + "Created new catalog.");
        } else {
            sender.sendMessage(ChatColor.YELLOW + "Are you sure you want to overwrite the current catalog?");
            sender.sendMessage(ChatColor.YELLOW + " To confirm, please type /catalog create confirm");
        }
    }
    private void saveCommand(CommandSender sender, String[] args) {
        String fileName;
        if (args.length < 1) {
            fileName = plugin.getConfig().getString("defaultSaveName");
            sender.sendMessage(ChatColor.YELLOW + "Warning: Save might be overwritten when server closes.");
        } else {
            fileName = args[0];
        }

        if (plugin.getCatalog().save(fileName)) {
            sender.sendMessage(ChatColor.GREEN + "Successfully saved catalog.");
        } else {
            sender.sendMessage(ChatColor.RED + "Failed to save catalog. Look at server log to find potential problem.");
        }
    }

    private void loadCommand(CommandSender sender, String[] args) {
        String fileName;
        if (args.length < 1) {
            fileName = plugin.getConfig().getString("defaultSaveName");
        } else {
            fileName = args[0];
        }

        if (Catalog.load(fileName)) {
            sender.sendMessage(ChatColor.GREEN + "Successfully loaded catalog.");
        } else {
            sender.sendMessage(ChatColor.RED + "Failed to load catalog. Look at server log to find potential problem.");
        }
    }

    private void deleteCommand(CommandSender sender, String[] args) {
        if (args.length > 1 && args[1].equalsIgnoreCase("confirm")) {
            String fileName = args[0];

            if (!fileName.endsWith(Catalog.SUFFIX)) {
                fileName += Catalog.SUFFIX;
            }

            File myObj = new File(plugin.getConfig().getString("savePath") + File.separator + fileName);
            if (myObj.delete()) {
                sender.sendMessage(ChatColor.GREEN + "Sucessfully deleted file.");
            } else {
                sender.sendMessage(ChatColor.RED + "Failed to delete file.");
            }
        } else {
            sender.sendMessage(ChatColor.YELLOW + "Are you sure you want to overwrite the current catalog?");
            sender.sendMessage(ChatColor.YELLOW + " To confirm, please type /catalog delete <fileName> confirm");
        }
    }

    private void listCommand(CommandSender sender) {
        File folder = new File(Objects.requireNonNull(plugin.getConfig().getString("savePath")));
        File[] listOfFiles = folder.listFiles();
        sender.sendMessage(ChatColor.BOLD + "All available files:");
        for (File file: listOfFiles) {
            if (file.isFile() && file.getName().endsWith(Catalog.SUFFIX)) {
                sender.sendMessage(" - " + file.getName());
            }
        }
    }
}
