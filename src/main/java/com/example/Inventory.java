package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class Inventory extends Application {

    // All obtainable items
    private final String[] obtainableItems = {
            "Sword", "Shield", "Bow", "Potion", "Helmet",
            "Armor", "Boots", "Ring", "Amulet", "Dagger"
    };

    // Map to store rolled items and their counts
    private static final Map<String, Integer> rolledItems = new HashMap<>();

    public static void main(String[] args) {
        // Example rolled items with quantities
        rolledItems.put("Sword", 10);
        rolledItems.put("Potion", 5);
        rolledItems.put("Armor", 42);
        launch(args);
    }

    // Method to add an item to the rolledItems map and increment its count
    public static void addRolledItem(String item) {
        rolledItems.put(item, rolledItems.getOrDefault(item, 0) + 1);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Inventory");

        // Root Pane with Dark Background
        Pane root = new Pane();
        root.setPrefSize(400, 400);
        root.setStyle("-fx-background-color: #1e1e1e;");

        // Inventory Title
        Label inventoryLabel = new Label("Inventory");
        inventoryLabel.setLayoutX(150);
        inventoryLabel.setLayoutY(10);
        inventoryLabel.setFont(new Font(20));
        inventoryLabel.setTextFill(Color.WHITE);
        root.getChildren().add(inventoryLabel);

        // Display All Obtainable Items and their rolled counts
        int yOffset = 50;
        for (String item : obtainableItems) {
            Label itemLabel = new Label(item);

            // Highlight rolled items and display quantity
            if (rolledItems.containsKey(item)) {
                itemLabel.setText(item + " x" + rolledItems.get(item)); // Show quantity
                itemLabel.setTextFill(Color.LIME); // Highlight if owned
            } else {
                itemLabel.setText(item + " x0"); // Display 0 if not owned
                itemLabel.setTextFill(Color.RED); // Gray out if not owned
            }

            itemLabel.setLayoutX(50);
            itemLabel.setLayoutY(yOffset);
            itemLabel.setFont(new Font(16));
            root.getChildren().add(itemLabel);
            yOffset += 30; // Space between items
        }

        // Close Button
        Button closeButton = new Button("Close");
        closeButton.setLayoutX(150);
        closeButton.setLayoutY(350);
        closeButton.setPrefSize(100, 30);
        closeButton.setOnAction(e -> primaryStage.close());
        root.getChildren().add(closeButton);

        // Scene and Stage Setup
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
