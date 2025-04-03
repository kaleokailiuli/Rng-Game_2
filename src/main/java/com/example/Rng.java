package com.example;

import java.util.Random;

public class Rng {
    // Define items and their rarities (higher rarity means more rare)
    public static final String[] obtainableItems = {
            "Sword", "Shield", "Bow", "Potion", "Helmet",
            "Armor", "Boots", "Ring", "Amulet", "Dagger"
    };

    // Define rarity probabilities (sum must be 100)
    private static final int[] rarities = {
            50, // 50% chance for Sword
            20, // 20% chance for Shield
            15, // 15% chance for Bow
            5,  // 5% chance for Potion
            5,  // 5% chance for Helmet
            3,  // 3% chance for Armor
            1,  // 1% chance for Boots
            1,  // 1% chance for Ring
            0,  // 0% chance for Amulet (not used in this example)
            0   // 0% chance for Dagger (not used in this example)
    };

    // Roll an item based on rarities
    public static String rollItem() {
        Random rand = new Random();
        int totalChance = 0;

        // Calculate total chance (sum of rarities)
        for (int rarity : rarities) {
            totalChance += rarity;
        }

        int roll = rand.nextInt(totalChance); // Get random number between 0 and totalChance
        int cumulativeChance = 0;

        for (int i = 0; i < obtainableItems.length; i++) {
            cumulativeChance += rarities[i];
            if (roll < cumulativeChance) {
                return obtainableItems[i];
            }
        }
        return null; // Default, shouldn't happen
    }
}
