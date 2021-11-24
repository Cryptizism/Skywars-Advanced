package net.cryptizism.skywars.manager;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigManager {

    private static File chestConfig;
    private static FileConfiguration customChestConfig;

    private static File priceConfig;
    private static FileConfiguration customPriceConfig;


    // Finds or generates custom config file
    public static void setup(){
        chestConfig = new File(Bukkit.getServer().getPluginManager().getPlugin("Skywars").getDataFolder(), "chest-locations.yml");

        if(!chestConfig.exists()){
            try {
                Bukkit.getLogger().severe("No chest config");
                Bukkit.getLogger().warning("Creating chest config....");
                chestConfig.createNewFile();
                Bukkit.getLogger().warning("Chest config created, go update ur config and restart....");
            } catch (IOException e) {
                Bukkit.getLogger().severe("Failed to make chest config file... plugin will not work as intended, if the error persists DM Cryptizism#2999");
            }
        }
        customChestConfig = YamlConfiguration.loadConfiguration(chestConfig);

        if(!priceConfig.exists()){
            try {
                Bukkit.getLogger().severe("No price config");
                Bukkit.getLogger().warning("Creating price config....");
                priceConfig.createNewFile();
                Bukkit.getLogger().warning("Price config created, go update ur config and restart....");
            } catch (IOException e) {
                Bukkit.getLogger().severe("Failed to make price config file... plugin will not work as intended, try restarting, if the error persists DM Cryptizism#2999");
            }
        }
        customPriceConfig = YamlConfiguration.loadConfiguration(priceConfig);
    }

    public static void saveChestConfig(){
        try {
            Bukkit.getLogger().warning("Saving chest config....");
            customChestConfig.save(chestConfig);
            Bukkit.getLogger().warning("Saved chest config....");
        } catch (IOException e) {
            Bukkit.getLogger().severe("Failed to save chest config....");
        }
    }

    public static void reloadChestConfig(){
        customChestConfig = YamlConfiguration.loadConfiguration(chestConfig);
    }

    public static void savePriceConfig(){
        try {
            Bukkit.getLogger().warning("Saving price config....");
            customPriceConfig.save(priceConfig);
            Bukkit.getLogger().warning("Saved price config....");
        } catch (IOException e) {
            Bukkit.getLogger().severe("Failed to save price config....");
        }
    }

    public static void reloadPriceConfig(){
        customPriceConfig = YamlConfiguration.loadConfiguration(priceConfig);
    }

    //Getters

    public static FileConfiguration getCustomChestConfig() {
        return customChestConfig;
    }

    public static FileConfiguration getCustomPriceConfig() {
        return customPriceConfig;
    }
}
