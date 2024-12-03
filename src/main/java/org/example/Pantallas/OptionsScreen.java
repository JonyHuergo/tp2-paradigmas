package org.example.Pantallas;

import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class OptionsScreen extends VBox {
    public OptionsScreen(StackPane stackPane) {
        Text text = new Text("Pantalla Options");
        text.setFont(Font.font("RetroFont", FontWeight.BOLD, 60));
        text.setFill(Color.BLACK);

        StackPane.setAlignment(text, javafx.geometry.Pos.CENTER);

        this.getChildren().add(text);
    }
}
