package org.example.Pantallas;

import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class JuegoScreen extends VBox {
    private StackPane stackPane;

    public JuegoScreen(StackPane stackPane) {
        Text text = new Text("Pantalla Juego Principal");
        text.setFont(Font.font("RetroFont", FontWeight.BOLD, 60));
        text.setFill(Color.BLACK);

        StackPane.setAlignment(text, javafx.geometry.Pos.CENTER);

        this.getChildren().add(text);
    }
}
