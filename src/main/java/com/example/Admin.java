package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.List;

public class Admin extends Application {

    // List of obtainable items for forced roll
    private static final List<String> obtainableItems = List.of(
            "Sword", "Shield", "Bow", "Potion", "Helmet",
            "Armor", "Boots", "Ring", "Amulet", "Dagger"
    );

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Admin Panel");

        // Root Pane
        Pane root = new Pane();
        root.setPrefSize(400, 300);
        root.setStyle("-fx-background-color: #1e1e1e;");

        // Title
        Label titleLabel = new Label("Admin Panel");
        titleLabel.setLayoutX(140);
        titleLabel.setLayoutY(10);
        titleLabel.setFont(new Font(20));
        titleLabel.setTextFill(Color.WHITE);
        root.getChildren().add(titleLabel);

        // Coin input and label
        Label coinLabel = new Label("Set Coins:");
        coinLabel.setLayoutX(20);
        coinLabel.setLayoutY(50);
        coinLabel.setFont(new Font(16));
        coinLabel.setTextFill(Color.WHITE);
        root.getChildren().add(coinLabel);

        TextField coinInput = new TextField();
        coinInput.setLayoutX(100);
        coinInput.setLayoutY(50);
        coinInput.setPrefWidth(100);
        root.getChildren().add(coinInput);

        // Add coins button
        Button addCoinsButton = new Button("Add Coins");
        addCoinsButton.setLayoutX(220);
        addCoinsButton.setLayoutY(50);
        addCoinsButton.setOnAction(e -> {
            try {
                int coinsToAdd = Integer.parseInt(coinInput.getText());
                if (coinsToAdd >= 0) {
                    // Add coins to the current coin balance in Gui
                    Gui.coins += coinsToAdd; 
                    Gui.coinsLabel.setText("Coins: " + Gui.coins); // Update coins in the main GUI
                }
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        });
        root.getChildren().add(addCoinsButton);

        // Forced Roll section
        Label rollLabel = new Label("Force Roll Item:");
        rollLabel.setLayoutX(20);
        rollLabel.setLayoutY(100);
        rollLabel.setFont(new Font(16));
        rollLabel.setTextFill(Color.WHITE);
        root.getChildren().add(rollLabel);

        // ComboBox for item selection
        ComboBox<String> itemComboBox = new ComboBox<>();
        itemComboBox.getItems().addAll(obtainableItems); // Add items to the ComboBox
        itemComboBox.setLayoutX(120);
        itemComboBox.setLayoutY(100);
        itemComboBox.setPrefWidth(150);
        root.getChildren().add(itemComboBox);

        // Force Roll Button
        Button forceRollButton = new Button("Force Roll");
        forceRollButton.setLayoutX(120);
        forceRollButton.setLayoutY(150);
        forceRollButton.setOnAction(e -> {
            String selectedItem = itemComboBox.getValue();
            if (selectedItem != null && obtainableItems.contains(selectedItem)) {
                // Add the forced rolled item to inventory
                Inventory.addRolledItem(selectedItem);

                // Display the forced rolled item in the roll display
                Gui.updateRollDisplay("You forced rolled: " + selectedItem); // This updates the Roll Display

                // Optionally, add coins for forced roll
                Gui.coins += 10;
                Gui.coinsLabel.setText("Coins: " + Gui.coins); // Update coin count
            } else {
                System.out.println("Invalid item. Please select an item.");
            }
        });
        root.getChildren().add(forceRollButton);

        // Scene and Stage Setup
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
