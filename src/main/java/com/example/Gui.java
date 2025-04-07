package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Gui extends Application {

    public static int coins = 100; // Public coins variable
    public static Label coinsLabel; // Public label to show coins
    public static Label rollDisplay; // Public label for roll display

    public static void main(String[] args) {
        launch(args); // Launches the JavaFX application
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("RNG Game"); // Sets the window title

        // Root Pane with Dark Background
        Pane root = new Pane();
        root.setPrefSize(600, 500); // Sets window size
        root.setStyle("-fx-background-color: #1e1e1e;"); // Dark background

        // Coins Display
        coinsLabel = new Label("Coins: " + coins); // Shows initial coin count
        coinsLabel.setLayoutX(10);
        coinsLabel.setLayoutY(10);
        coinsLabel.setFont(new Font(16));
        coinsLabel.setTextFill(Color.WHITE);
        root.getChildren().add(coinsLabel);

        // Roll Display Label
        rollDisplay = new Label("\"Roll Display\"");
        rollDisplay.setLayoutX(100);
        rollDisplay.setLayoutY(150);
        rollDisplay.setPrefSize(400, 100);
        rollDisplay.setStyle("-fx-background-color: #3a3a3a; -fx-text-fill: white; -fx-border-color: white;");
        rollDisplay.setFont(new Font(18));
        rollDisplay.setAlignment(javafx.geometry.Pos.CENTER);
        root.getChildren().add(rollDisplay);

        // Inventory Button - Opens Inventory GUI
        Button inventoryButton = new Button("Inventory");
        inventoryButton.setLayoutX(10);
        inventoryButton.setLayoutY(425);
        inventoryButton.setPrefSize(120, 30);
        inventoryButton.setOnAction(e -> {
            try {
                new Inventory().start(new Stage()); // Opens Inventory GUI
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        root.getChildren().add(inventoryButton);

        // Index Button - Opens Index GUI
        Button indexButton = new Button("Index");
        indexButton.setLayoutX(10);
        indexButton.setLayoutY(465);
        indexButton.setPrefSize(120, 30);
        indexButton.setOnAction(e -> {
            try {
                new ItemIndex().start(new Stage()); // Opens Index GUI
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        root.getChildren().add(indexButton);

        // Roll Button - Centered and Bigger
        Button rollButton = new Button("ROLL");
        rollButton.setLayoutX(200);
        rollButton.setLayoutY(430);
        rollButton.setPrefSize(200, 50);
        rollButton.setOnAction(e -> {
            String rolledItem = Rng.getRandomItem();
            Inventory.addRolledItem(rolledItem);
            rollDisplay.setText("You rolled: " + rolledItem);

            String rarity = rolledItem.substring(rolledItem.indexOf('(') + 1, rolledItem.indexOf(')'));
            int reward = 0;

            // Rewriting the switch expression as a regular switch
            switch (rarity) {
                case "Common":
                    reward = 10;
                    break;
                case "Rare":
                    reward = 20;
                    break;
                case "Epic":
                    reward = 40;
                    break;
                case "Legendary":
                    reward = 75;
                    break;
                case "Mythic":
                    reward = 150;
                    break;
                default:
                    reward = 0;
                    break;
            }

            coins += reward;
            coinsLabel.setText("Coins: " + coins);
        });
        root.getChildren().add(rollButton);

        // Admin Button - Opens Admin GUI
        Button adminButton = new Button("Admin");
        adminButton.setLayoutX(500);
        adminButton.setLayoutY(10);
        adminButton.setPrefSize(80, 30);
        adminButton.setOnAction(e -> {
            try {
                new Admin().start(new Stage()); // Opens Admin GUI
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        root.getChildren().add(adminButton);

        // Scene and Stage Setup
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to update the roll display (for Admin.java)
    public static void updateRollDisplay(String text) {
        rollDisplay.setText(text);
    }
}
