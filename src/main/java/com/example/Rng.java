package com.example;

import java.util.Random;

public class Rng {

    private static final String[] commonItems = {
        "Sword (Common)", "Shield (Common)", "Bow (Common)"
    };

    private static final String[] rareItems = {
        "Potion (Rare)", "Helmet (Rare)"
    };

    private static final String[] epicItems = {
        "Armor (Epic)", "Boots (Epic)"
    };

    private static final String[] legendaryItems = {
        "Ring (Legendary)"
    };

    private static final String[] mythicItems = {
        "Amulet (Mythic)"
    };

    private static final Random random = new Random();

    public static String getRandomItem() {
        int roll = random.nextInt(100) + 1; // 1-100

        if (roll <= 40) {
            return commonItems[random.nextInt(commonItems.length)];
        } else if (roll <= 65) {
            return rareItems[random.nextInt(rareItems.length)];
        } else if (roll <= 85) {
            return epicItems[random.nextInt(epicItems.length)];
        } else if (roll <= 95) {
            return legendaryItems[random.nextInt(legendaryItems.length)];
        } else {
            return mythicItems[random.nextInt(mythicItems.length)];
        }
    }

    // Force a specific rarity (used in Admin panel)
    public static String forceRarity(String rarity) {
        switch (rarity) {
            case "Common (Gray)":
                return commonItems[random.nextInt(commonItems.length)];
            case "Rare (Blue)":
                return rareItems[random.nextInt(rareItems.length)];
            case "Epic (Purple)":
                return epicItems[random.nextInt(epicItems.length)];
            case "Legendary (Orange)":
                return legendaryItems[random.nextInt(legendaryItems.length)];
            case "Mythic (Red)":
                return mythicItems[random.nextInt(mythicItems.length)];
            default:
                return "Unknown Item";
        }
    }
}
