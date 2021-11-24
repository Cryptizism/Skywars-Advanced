package net.cryptizism.skywars;

import net.cryptizism.skywars.manager.ConfigManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Skywars extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        ConfigManager.setup();
        ConfigManager.getCustomChestConfig().options().copyDefaults(true);
        ConfigManager.saveChestConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
