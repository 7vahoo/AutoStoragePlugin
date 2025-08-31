package me.vahoo.autostorageplugin.storage;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class PlayerStorage {

    private Inventory inventory;
    private final UUID playerUUID;
    private final File file;

    public PlayerStorage(UUID uuid, File folder) {
        this.playerUUID = uuid;
        this.file = new File(folder, uuid.toString() + ".yml");
        load();
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void save() {
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        config.set("items", inventory.getContents());
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load() {
        inventory = Bukkit.createInventory(null, 27, "Storage");
        if (file.exists()) {
            FileConfiguration config = YamlConfiguration.loadConfiguration(file);
            ItemStack[] items = ((ItemStack[]) config.get("items"));
            if (items != null) inventory.setContents(items);
        }
    }
}