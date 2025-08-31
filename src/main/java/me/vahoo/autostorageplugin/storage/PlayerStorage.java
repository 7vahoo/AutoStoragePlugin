package me.vahoo.autostorageplugin.storage;

import me.vahoo.autostorageplugin.util.ItemStackSerializer;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

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
        config.set("items", ItemStackSerializer.toBase64(inventory.getContents()));
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load() {
        inventory = Bukkit.createInventory(null, 27, "Storage");
        if (!file.exists()) return;

        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        String base64 = config.getString("items");
        if (base64 != null) {
            ItemStack[] items = ItemStackSerializer.fromBase64(base64);
            inventory.setContents(items);
        }
    }
}
