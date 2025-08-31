package me.vahoo.autostorageplugin;

import me.vahoo.autostorageplugin.command.StorageCommand;
import me.vahoo.autostorageplugin.manager.StorageManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private StorageManager storageManager;

    @Override
    public void onEnable() {
        storageManager = new StorageManager(this);
        getCommand("store").setExecutor(new StorageCommand(storageManager));
        Bukkit.getPluginManager().registerEvents(new PlayerQuitListener(storageManager), this);
        getLogger().info("AutoStorage включен!");
    }

    @Override
    public void onDisable() {
        storageManager.saveAll();
        getLogger().info("AutoStorage выключен!");
    }

    public StorageManager getStorageManager() {
        return storageManager;
    }
}
