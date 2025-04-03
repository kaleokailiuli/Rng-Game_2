package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.List;
import java.util.Random;

public class Gui extends Application {

    private int coins = 100; // Variable to store the current coin count

    // List of obtainable items
    private static final List<String> obtainableItems = List.of(
            "Sword", "Shield", "Bow", "Potion", "Helmet",
            "Armor", "Boots", "Ring", "Amulet", "Dagger"
    );

    public static void main(String[] args) {
        launch(args); // Launches the JavaFX application
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("RNG Game");

        // Root Pane with Dark Background
        Pane root = new Pane();
        root.setPrefSize(600, 500); // Sets window size
        root.setStyle("-fx-background-color: #1e1e1e;"); // Dark background

        // Coins Display
        Label coinsLabel = new Label("Coins: " + coins);
        coinsLabel.setLayoutX(10);
        coinsLabel.setLayoutY(10);
        coinsLabel.setFont(new Font(16));
        coinsLabel.setTextFill(Color.WHITE);
        root.getChildren().add(coinsLabel);

        // Roll Display Label
        Label rollDisplay = new Label("\"Roll Display\"");
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

        // Index Button
        Button indexButton = new Button("Index");
        indexButton.setLayoutX(10);
        indexButton.setLayoutY(465);
        indexButton.setPrefSize(120, 30);
        indexButton.setOnAction(e -> {
            try {
                new ItemIndex().start(new Stage()); // Opens ItemIndex GUI
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        root.getChildren().add(indexButton);

        // Roll Button
        Button rollButton = new Button("ROLL");
        rollButton.setLayoutX(200);
        rollButton.setLayoutY(430);
        rollButton.setPrefSize(200, 50);
        rollButton.setOnAction(e -> {
            // Choose a random item from the list
            String rolledItem = getRandomItem();

            // Update the rolled item in the Inventory
            Inventory.addRolledItem(rolledItem);

            // Display rolled item in roll display
            rollDisplay.setText("You rolled: " + rolledItem);

            // Add coins for rolling
            coins += 10;
            coinsLabel.setText("Coins: " + coins); // Updates coin count
        });
        root.getChildren().add(rollButton);

        // Admin Button
        Button adminButton = new Button("Admin");
        adminButton.setLayoutX(500);
        adminButton.setLayoutY(10);
        adminButton.setPrefSize(80, 30);
        root.getChildren().add(adminButton);

        // Scene and Stage Setup
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Helper method to randomly select an item from obtainable items
    private String getRandomItem() {
        Random rand = new Random();
        return obtainableItems.get(rand.nextInt(obtainableItems.size()));
    }
}
