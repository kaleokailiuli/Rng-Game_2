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

public class ItemIndex extends Application {

    // List of obtainable items and their descriptions
    private static final List<String> items = List.of(
            "Sword - A sharp blade, used for combat.",
            "Shield - A strong defensive tool.",
            "Bow - A ranged weapon for shooting arrows.",
            "Potion - Restores health when consumed.",
            "Helmet - Protects your head in battle.",
            "Armor - Provides protection for your body.",
            "Boots - Footwear that provides extra mobility.",
            "Ring - A small magical artifact.",
            "Amulet - A magical necklace that grants powers.",
            "Dagger - A short, quick weapon."
    );

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Item Index");

        // Root Pane with Dark Background
        Pane root = new Pane();
        root.setPrefSize(600, 400);
        root.setStyle("-fx-background-color: #1e1e1e;");

        // Item Description Label (Middle Left) with Text Wrapping
        Label itemDescription = new Label("Item Description will appear here");
        itemDescription.setLayoutX(50);  // Position the description on the left side
        itemDescription.setLayoutY(150);  // Center it vertically
        itemDescription.setFont(new Font(16));
        itemDescription.setTextFill(Color.WHITE);
        itemDescription.setPrefWidth(200);  // Set width to contain text comfortably
        itemDescription.setWrapText(true);  // Allow text to wrap if it doesn't fit
        root.getChildren().add(itemDescription);

        // List of items moved up by 10 units
        int yOffset = 40;  // Starting y position decreased by 10 units
        for (String item : items) {
            Button itemButton = new Button(item.split(" - ")[0]); // Display item name only
            itemButton.setLayoutX(350);  // Position the list on the right side
            itemButton.setLayoutY(yOffset);
            itemButton.setPrefSize(200, 30);

            // Display description when an item is clicked
            itemButton.setOnAction(e -> itemDescription.setText(item));

            root.getChildren().add(itemButton);
            yOffset += 40; // Space between buttons
        }

        // Close Button (Top Left)
        Button closeButton = new Button("Close");
        closeButton.setLayoutX(10);
        closeButton.setLayoutY(10);
        closeButton.setPrefSize(80, 30);
        closeButton.setOnAction(e -> primaryStage.close());
        root.getChildren().add(closeButton);

        // Scene and Stage Setup
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
