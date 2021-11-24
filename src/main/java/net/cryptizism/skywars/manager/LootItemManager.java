package net.cryptizism.skywars.manager;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class LootItemManager {
    private final Material material;
    private final String customName;
    private final Map<Enchantment, Integer> enchantmentToMap = new HashMap<>();
    private final double chance;

    private final int minAmount;
    private final int maxAmount;

    public LootItemManager(ConfigurationSection section){
        Material material;

        try {
            material = Material.valueOf(section.getString("material"));
        } catch (Exception err) {
            material = Material.AIR;
        }

        this.material = material;
        this.customName = section.getString("name");
        this.chance = section.getDouble("chance");
        this.minAmount = section.getInt("minAmount");
        this.maxAmount = section.getInt("maxAmount");

        ConfigurationSection enchantmentsSection = section.getConfigurationSection("enchantments");

        if(enchantmentsSection != null){
            for (String enchantmentKey : enchantmentsSection.getKeys(false)){
                Enchantment enchantment = Enchantment.getByName(enchantmentKey.toLowerCase());
                if(enchantment != null){
                    int level = enchantmentsSection.getInt(enchantmentKey);
                    enchantmentToMap.put(enchantment, level);
                }
            }
        }
    }

    public boolean shouldFill(Random random){
        return random.nextDouble() < chance;
    }

    public ItemStack make(ThreadLocalRandom random){
        int amount = random.nextInt(minAmount, maxAmount + 1);

        ItemStack itemStack = new ItemStack(material, amount);

        ItemMeta itemMeta = itemStack.getItemMeta();

        if (customName != null){
            itemMeta.setDisplayName(
                    ChatColor.translateAlternateColorCodes('&', customName)
            );
        }

        if(enchantmentToMap.isEmpty()){
            for (Map.Entry<Enchantment, Integer> enchantEntry : enchantmentToMap.entrySet()){
                itemMeta.addEnchant(
                        enchantEntry.getKey(),
                        enchantEntry.getValue(),
                        true
                );
            }
        }

        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }
}
