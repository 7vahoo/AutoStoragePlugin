package me.vahoo.autostorageplugin.manager;

import me.vahoo.autostorageplugin.Main;
import me.vahoo.autostorageplugin.storage.PlayerStorage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class StorageManager {

    private final Map<UUID, PlayerStorage> storages = new HashMap<>();
    private final File folder;

    public StorageManager(Main plugin) {
        folder = new File(plugin.getDataFolder(), "storages");
        if (!folder.exists()) folder.mkdirs();

    
        for (Player player : Bukkit.getOnlinePlayers()) {
            storages.put(player.getUniqueId(), new PlayerStorage(player.getUniqueId(), folder));
        }
    }

    public PlayerStorage getStorage(Player player) {
        return storages.computeIfAbsent(player.getUniqueId(), uuid -> new PlayerStorage(uuid, folder));
    }

    public void saveAll() {
        for (PlayerStorage storage : storages.values()) {
            storage.save();
        }
    }
}
