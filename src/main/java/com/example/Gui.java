package com.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
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

        // Root StackPane with Dark Background
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: #1e1e1e;");
        root.setPrefSize(600, 500);

        // VBox for main content
        VBox mainLayout = new VBox(20);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setAlignment(Pos.TOP_CENTER);

        // Coins Display
        coinsLabel = new Label("Coins: " + coins);
        coinsLabel.setFont(new Font(16));
        coinsLabel.setTextFill(Color.WHITE);
        mainLayout.getChildren().add(coinsLabel);

        // Roll Display
        rollDisplay = new Label("\"Roll Display\"");
        rollDisplay.setPrefSize(400, 100);
        rollDisplay.setAlignment(Pos.CENTER);
        rollDisplay.setFont(new Font(18));
        rollDisplay.setStyle("-fx-background-color: #3a3a3a; -fx-text-fill: white; -fx-border-color: white;");
        mainLayout.getChildren().add(rollDisplay);

        // Buttons: Roll, Inventory, Index
        HBox buttonRow = new HBox(20);
        buttonRow.setAlignment(Pos.CENTER);

        Button rollButton = new Button("ROLL");
        rollButton.setPrefSize(200, 50);
        rollButton.setOnAction(e -> {
            String rolledItem = Rng.getRandomItem();
            Inventory.addRolledItem(rolledItem);
            rollDisplay.setText("You rolled: " + rolledItem);

            String rarity = rolledItem.substring(rolledItem.indexOf('(') + 1, rolledItem.indexOf(')'));
            int reward;

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

        Button inventoryButton = new Button("Inventory");
        inventoryButton.setPrefSize(120, 30);
        inventoryButton.setOnAction(e -> {
            try {
                new Inventory().start(new Stage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Button indexButton = new Button("Index");
        indexButton.setPrefSize(120, 30);
        indexButton.setOnAction(e -> {
            try {
                new ItemIndex().start(new Stage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        buttonRow.getChildren().addAll(inventoryButton, rollButton, indexButton);
        mainLayout.getChildren().add(buttonRow);

        // Admin button aligned to top right
        HBox topBar = new HBox();
        topBar.setAlignment(Pos.TOP_RIGHT);
        Button adminButton = new Button("Admin");
        adminButton.setPrefSize(80, 30);
        adminButton.setOnAction(e -> {
            try {
                new Admin().start(new Stage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        topBar.getChildren().add(adminButton);
        topBar.setPadding(new Insets(0, 10, 0, 0));

        // Final layout stack
        VBox fullLayout = new VBox();
        fullLayout.getChildren().addAll(topBar, mainLayout);
        root.getChildren().add(fullLayout);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to update the roll display (for Admin.java)
    public static void updateRollDisplay(String text) {
        rollDisplay.setText(text);
    }
}
   