package me.cyclingman.battlemap.commands;

import me.cyclingman.battlemap.BattleMap;
import me.cyclingman.battlemap.Map;
import me.cyclingman.battlemap.gamemodes.Gamemode;
import me.cyclingman.battlemap.gamemodes.GamemodeFactory;
import me.cyclingman.battlemap.gamemodes.KOTH;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class MapCommand implements CommandExecutor {

    private final BattleMap plugin;
    public MapCommand(BattleMap plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length < 1) return false;

        switch (args[0].toLowerCase()) {
            case "create":
                createCommand(sender, Arrays.stream(args).skip(1L).toArray(String[]::new));
                return true;
            case "delete":
                deleteCommand(sender, Arrays.stream(args).skip(1L).toArray(String[]::new));
                return true;
            case "update":
                updateCommand(sender, Arrays.stream(args).skip(1L).toArray(String[]::new));
                return true;
            case "activate":
                activateCommand(sender, Arrays.stream(args).skip(1L).toArray(String[]::new));
                return true;
            case "deactivate":
                deactivateCommand(sender, Arrays.stream(args).skip(1L).toArray(String[]::new));
                return true;
            case "list":
                listCommand(sender, Arrays.stream(args).skip(1L).toArray(String[]::new));
                return true;
            case "gamemodes":
                gamemodesCommand(sender, Arrays.stream(args).skip(1L).toArray(String[]::new));
                return true;
            case "goto":
                gotoCommand(sender, Arrays.stream(args).skip(1L).toArray(String[]::new));
                return true;
            case "join":
                joinCommand(sender, Arrays.stream(args).skip(1L).toArray(String[]::new));
                return true;
            case "leave":
                leaveCommand(sender, Arrays.stream(args).skip(1L).toArray(String[]::new));
                return true;
        }

        return false;
    }

    private void createCommand(CommandSender sender, String[] args) {
        if (sender instanceof Player p) {
            if (args.length == 2) {
                String mapName = args[0];
                Gamemode gamemode = GamemodeFactory.createGamemode(args[1]);

                if (gamemode == null) {
                    sender.sendMessage(ChatColor.RED + "Gamemode does not exist");
                    return;
                }

                boolean success = plugin.getCatalog().addMap(new Map(mapName, p.getLocation(), new KOTH()));
                if (success) {
                    p.sendMessage(ChatColor.GREEN + "Map created.");
                } else {
                    p.sendMessage(ChatColor.RED + "Map with provided name already exists.");
                }

            } else {
                sender.sendMessage(ChatColor.RED + "Usage: /map create <mapName> <gamemode>");
                sender.sendMessage(ChatColor.RED + "For a list of gamemodes do /map gamemodes");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "This command can only be executed as a player.");
        }
    }

    private void deleteCommand(CommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "Usage: /map delete <mapName>");
            sender.sendMessage(ChatColor.RED + "For a list of maps do /map list");
        } else if (args.length == 1) {
            sender.sendMessage(ChatColor.YELLOW + "Are you sure you want to delete the map.");
            sender.sendMessage(ChatColor.YELLOW + "Do /map delete <mapName> confirm to delete the map.");
        } else if (args.length == 2 && args[1].equalsIgnoreCase("confirm")) {
            boolean success = plugin.getCatalog().removeMap(args[0]);
            if (success) {
                sender.sendMessage(ChatColor.GREEN + "Map successfully removed");
            } else {
                sender.sendMessage(ChatColor.RED + "Could not remove map. Is the name correct?");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "Usage: /map delete <mapName>");
        }
    }

    private void updateCommand(CommandSender sender, String[] args) {
        if (args.length == 1) {
            sender.sendMessage(ChatColor.YELLOW + "Not yet implemented. Try removing and recreating the map.");
        } else {
            sender.sendMessage(ChatColor.RED + "Usage: /map update <mapName>");
            sender.sendMessage(ChatColor.RED + "For a list of maps do /map list");
        }
    }

    private void activateCommand(CommandSender sender, String[] args) {
        if (args.length == 1) {
            Map map = plugin.getCatalog().getMap(args[0]);
            if (map == null) {
                sender.sendMessage(ChatColor.RED + "Map does not exist. Is the name correct?");
                return;
            }
            map.getGamemode().activate();
            sender.sendMessage(ChatColor.GREEN + "Map is (re)activated");
        } else {
            sender.sendMessage(ChatColor.RED + "Usage: /map activate <mapName>");
            sender.sendMessage(ChatColor.RED + "For a list of maps do /map list");
        }
    }

    private void deactivateCommand(CommandSender sender, String[] args) {
        if (args.length == 1) {
            Map map = plugin.getCatalog().getMap(args[0]);
            if (map == null) {
                sender.sendMessage(ChatColor.RED + "Map does not exist. Is the name correct?");
                return;
            }
            map.getGamemode().deactivate();
            sender.sendMessage(ChatColor.GREEN + "Map is deactivated.");
        } else {
            sender.sendMessage(ChatColor.RED + "Usage: /map deactivate <mapName>");
            sender.sendMessage(ChatColor.RED + "For a list of maps do /map list");
        }
    }

    private void listCommand(CommandSender sender, String[] args) {
        plugin.getCatalog().printMaps(sender);
    }

    private void gamemodesCommand(CommandSender sender, String[] args) {
        GamemodeFactory.printGamemodes(sender);
    }

    private void gotoCommand(CommandSender sender, String[] args) {
        if (sender instanceof Player p) {
            if (args.length == 1) {
                Map map = plugin.getCatalog().getMap(args[0]);
                if (map == null) {
                    sender.sendMessage(ChatColor.RED + "Provided map does not exist.");
                    return;
                }
                p.teleport(map.getLocation());
                sender.sendMessage(ChatColor.GREEN + "TP successful.");
            } else {
                sender.sendMessage(ChatColor.RED + "Usage: /map goto <mapName>");
                sender.sendMessage(ChatColor.RED + "For a list of maps do /map list");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "This command can only be executed as a player.");
        }
    }

    private void joinCommand(CommandSender sender, String[] args) {
        if (sender instanceof Player p) {
            if (args.length == 1) {
                Map map = plugin.getCatalog().getMap(args[0]);
                if (map == null) {
                    sender.sendMessage(ChatColor.RED + "Provided map does not exist.");
                    return;
                }
                if (map.getGamemode().addPlayer(p)) {
                    sender.sendMessage(ChatColor.GREEN + "Joined successfully.");
                } else {
                    sender.sendMessage(ChatColor.RED + "Could not join, is the map enabled?");
                }

            } else {
                sender.sendMessage(ChatColor.RED + "Usage: /map join <mapName>");
                sender.sendMessage(ChatColor.RED + "For a list of maps do /map list");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "This command can only be executed as a player.");
        }
    }

    private void leaveCommand(CommandSender sender, String[] args) {
        if (sender instanceof Player p) {
            if (args.length == 1) {
                Map map = plugin.getCatalog().getMap(args[0]);
                if (map == null) {
                    sender.sendMessage(ChatColor.RED + "Provided map does not exist.");
                    return;
                }
                if (map.getGamemode().removePlayer(p)) {
                    sender.sendMessage(ChatColor.GREEN + "Left successfully.");
                } else {
                    sender.sendMessage(ChatColor.RED + "Could not leave, is the map enabled?");
                }

            } else {
                sender.sendMessage(ChatColor.RED + "Usage: /map join <mapName>");
                sender.sendMessage(ChatColor.RED + "For a list of maps do /map list");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "This command can only be executed as a player.");
        }
    }
}
