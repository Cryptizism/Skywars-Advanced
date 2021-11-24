package net.cryptizism.skywars.manager;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ChestManager {
    private final Set<Location> openedChests = new HashSet<>();
    private final List<LootItemManager> lootItems = new ArrayList<LootItemManager>();

    public ChestManager(FileConfiguration lootConfig){
        ConfigurationSection itemsSection = lootConfig.getConfigurationSection("lootItems");

        if(itemsSection == null){
            Bukkit.getLogger().warning("ERROR 404: Please setup your `lootItems` in the config.yml (pls)");
        } else {
            for (String key : itemsSection.getKeys(false)) {
                ConfigurationSection section = itemsSection.getConfigurationSection(key);
                lootItems.add(new LootItemManager(section));
            }
        }
    }

    public void markAsOpened(Location location){
        openedChests.add(location);
        //add timer hologram above
    }

    public boolean hasBeenOpened(Location location){
        return openedChests.contains(location);
    }

    public void resetChests(){
        openedChests.clear();

    }

    public void populateChest(Inventory inventory, FileConfiguration chestConfig){

    }
}
