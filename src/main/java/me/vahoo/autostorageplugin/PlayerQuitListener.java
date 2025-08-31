package me.vahoo.autostorageplugin;

import me.vahoo.autostorageplugin.manager.StorageManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    private final StorageManager storageManager;

    public PlayerQuitListener(StorageManager storageManager) {
        this.storageManager = storageManager;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        storageManager.savePlayer(event.getPlayer());
    }
}
