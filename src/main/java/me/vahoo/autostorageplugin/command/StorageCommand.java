package me.vahoo.autostorageplugin.command;

import me.vahoo.autostorageplugin.manager.StorageManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class StorageCommand implements CommandExecutor {

    private final StorageManager storageManager;

    public StorageCommand(StorageManager storageManager) {
        this.storageManager = storageManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage("Только игрок может открыть хранилище!");
            return true;
        }

        if (!player.hasPermission("autostorage.use")) {
            player.sendMessage("Нет прав на использование хранилища!");
            return true;
        }

        if (args.length == 0) {
            player.openInventory(storageManager.getStorage(player).getInventory());
            return true;
        }

        if (args.length == 2 && args[0].equalsIgnoreCase("open")) {
            if (!player.hasPermission("autostorage.admin")) {
                player.sendMessage("Нет прав на открытие чужого хранилища!");
                return true;
            }

            Player target = Bukkit.getPlayerExact(args[1]);
            if (target == null) {
                player.sendMessage("Игрок не найден!");
                return true;
            }

            player.openInventory(storageManager.getStorage(target).getInventory());
            player.sendMessage("Открыто хранилище игрока " + target.getName());
            return true;
        }

        player.sendMessage("Использование: /store [open <player>]");
        return true;
    }
}