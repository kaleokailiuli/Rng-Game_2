package com.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Admin extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Admin Panel");

        VBox root = new VBox(15);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #2b2b2b;");

        Label titleLabel = new Label("Admin Panel");
        titleLabel.setFont(new Font(22));
        titleLabel.setTextFill(Color.WHITE);

        // === Force Roll Dropdown ===
        ComboBox<String> forceRollDropdown = new ComboBox<>();
        forceRollDropdown.getItems().addAll(
                "Common (Gray)", 
                "Rare (Blue)", 
                "Epic (Purple)", 
                "Legendary (Orange)", 
                "Mythic (Red)"
        );
        forceRollDropdown.setPromptText("Select rarity to force roll");
        forceRollDropdown.setStyle("-fx-background-color: #3c3f41; -fx-text-fill: white;");
        forceRollDropdown.setPrefHeight(40);

        Button forceRollButton = new Button("Force Roll");
        forceRollButton.setStyle("-fx-background-color: #e67e22; -fx-text-fill: white;");
        forceRollButton.setPrefHeight(40);
        forceRollButton.setOnAction(e -> {
            String rarity = forceRollDropdown.getValue();
            if (rarity != null) {
                String item = Rng.forceRarity(rarity);
                Inventory.addRolledItem(item);
                Gui.updateRollDisplay("Forced roll: " + item);
            }
        });

        HBox forceBox = new HBox(10, forceRollDropdown, forceRollButton);

        // === Coin Modifier ===
        TextField coinField = new TextField();
        coinField.setPromptText("Add coins...");
        coinField.setStyle("-fx-background-color: #3c3f41; -fx-text-fill: white;");
        coinField.setPrefHeight(40);

        Button addCoinsButton = new Button("Add");
        addCoinsButton.setPrefHeight(40);
        addCoinsButton.setStyle("-fx-background-color: #27ae60; -fx-text-fill: white;");
        addCoinsButton.setOnAction(e -> {
            try {
                int coinsToAdd = Integer.parseInt(coinField.getText());
                Gui.coins += coinsToAdd;
                Gui.coinsLabel.setText("Coins: " + Gui.coins);
            } catch (NumberFormatException ex) {
                // Optional error message
            }
        });

        HBox coinBox = new HBox(10, coinField, addCoinsButton);

        root.getChildren().addAll(
                titleLabel,
                new Separator(),
                new Label("Force Rarity Roll:"),
                forceBox,
                new Separator(),
                new Label("Modify Coins:"),
                coinBox
        );

        Scene scene = new Scene(root, 420, 300);
        stage.setScene(scene);
        stage.show();
    }
}
