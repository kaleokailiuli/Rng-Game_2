package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Rng {
    // Item and its corresponding rarity
    private static final Map<String, String> itemRarities = new HashMap<>();
    static {
        itemRarities.put("Sword", "Common");
        itemRarities.put("Shield", "Rare");
        itemRarities.put("Bow", "Epic");
        itemRarities.put("Potion", "Common");
        itemRarities.put("Helmet", "Rare");
        itemRarities.put("Armor", "Epic");
        itemRarities.put("Boots", "Common");
        itemRarities.put("Ring", "Legendary");
        itemRarities.put("Amulet", "Mythic");
        itemRarities.put("Dagger", "Rare");
    }

    // Probability weights for each rarity
    private static final Map<String, Integer> rarityWeights = new HashMap<>();
    static {
        rarityWeights.put("Common", 50); // 50% chance for Common
        rarityWeights.put("Rare", 30);   // 30% chance for Rare
        rarityWeights.put("Epic", 15);   // 15% chance for Epic
        rarityWeights.put("Legendary", 4); // 4% chance for Legendary
        rarityWeights.put("Mythic", 1);  // 1% chance for Mythic
        rarityWeights.put("Secret", 0);  // 0% for Secret, but can be modified later if needed
    }

    public static String getRandomItem() {
        Random rand = new Random();
        List<String> obtainableItems = new ArrayList<>(itemRarities.keySet());

        // Simulate the random roll based on rarity weights
        int totalWeight = rarityWeights.values().stream().mapToInt(Integer::intValue).sum();
        int randomRoll = rand.nextInt(totalWeight);

        // Pick an item based on weight distribution
        String chosenItem = null;
        int currentWeight = 0;
        for (String item : obtainableItems) {
            String rarity = itemRarities.get(item);
            currentWeight += rarityWeights.get(rarity);

            if (randomRoll < currentWeight) {
                chosenItem = item;
                break;
            }
        }

        return chosenItem + " (" + itemRarities.get(chosenItem) + ")";
    }

    // Provide access to itemRarities via a getter method
    public static Map<String, String> getItemRarities() {
        return itemRarities;
    }
}
