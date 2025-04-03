package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Admin extends Application {

    private Gui gui; // Reference to the Gui class

    public static void main(String[] args) {
        launch(args); // Launch the Admin GUI
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Admin Panel");

        // Create a new instance of Gui to access its fields
        gui = new Gui();

        // Root Pane with Dark Background
        Pane root = new Pane();
        root.setPrefSize(400, 300);
        root.setStyle("-fx-background-color: #1e1e1e;");

        // Add Coins Text Field
        TextField addCoinsField = new TextField();
        addCoinsField.setPromptText("Enter amount of coins");
        addCoinsField.setLayoutX(50);
        addCoinsField.setLayoutY(50);
        root.getChildren().add(addCoinsField);

        // Add Coins Button (Position it to the right of the text field)
        Button addCoinsButton = new Button("Add Coins");
        addCoinsButton.setLayoutX(230);  // Adjusted for right placement
        addCoinsButton.setLayoutY(50);
        addCoinsButton.setOnAction(e -> {
            try {
                int coinsToAdd = Integer.parseInt(addCoinsField.getText());
                gui.coins += coinsToAdd; // Update the coin count
                gui.coinsLabel.setText("Coins: " + gui.coins); // Update the coins label
                addCoinsField.clear(); // Clear the text field after use
            } catch (NumberFormatException ex) {
                ex.printStackTrace(); // Handle invalid input
            }
        });
        root.getChildren().add(addCoinsButton);

        // Force Roll Dropdown
        ComboBox<String> forceRollComboBox = new ComboBox<>();
        forceRollComboBox.getItems().addAll("Sword", "Shield", "Potion", "Helmet", "Armor", "Boots");
        forceRollComboBox.setLayoutX(50);
        forceRollComboBox.setLayoutY(100);
        root.getChildren().add(forceRollComboBox);

        // Force Roll Button
        Button forceRollButton = new Button("Force Roll");
        forceRollButton.setLayoutX(230);  // Positioned next to the dropdown
        forceRollButton.setLayoutY(100);
        forceRollButton.setOnAction(e -> {
            String selectedItem = forceRollComboBox.getValue();
            if (selectedItem != null) {
                // Display the forced item in the roll display
                String rolledItem = selectedItem + " (forced)";
                Gui.updateRollDisplay("You forced: " + rolledItem); // Update the roll display
                Inventory.addRolledItem(rolledItem); // Add to inventory
            }
        });
        root.getChildren().add(forceRollButton);

        // Scene and Stage Setup
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
