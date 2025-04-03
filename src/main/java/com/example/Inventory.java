package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.HashMap;
import java.util.Map;

public class Inventory extends Application {

    private static Map<String, Integer> inventory = new HashMap<>(); // Item name and its count

    public static void addRolledItem(String item) {
        // Remove the rarity part to store only the item name
        String itemName = item.split(" ")[0];

        // Update the inventory
        inventory.put(itemName, inventory.getOrDefault(itemName, 0) + 1);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Inventory");

        // Root Pane
        Pane root = new Pane();
        root.setPrefSize(400, 300);
        root.setStyle("-fx-background-color: #1e1e1e;");

        // Inventory Label
        Label inventoryLabel = new Label("Inventory:");
        inventoryLabel.setLayoutX(10);
        inventoryLabel.setLayoutY(10);
        inventoryLabel.setFont(new Font(20));
        inventoryLabel.setTextFill(Color.WHITE);
        root.getChildren().add(inventoryLabel);

        // Display inventory items
        int yOffset = 50;
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            Label itemLabel = new Label(entry.getKey() + " x" + entry.getValue());
            itemLabel.setLayoutX(10);
            itemLabel.setLayoutY(yOffset);
            itemLabel.setFont(new Font(16));
            itemLabel.setTextFill(Color.WHITE);
            root.getChildren().add(itemLabel);
            yOffset += 30;
        }

        // Scene and Stage Setup
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
