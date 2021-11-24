package net.cryptizism.skywars.manager;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ChestManager {
    private final Set<Location> openedChests = new HashSet<>();
    private final List<LootItemManager> lootItems = new ArrayList<LootItemManager>();

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
