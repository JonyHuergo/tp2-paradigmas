package org.example.Handlers;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.Pantallas.JuegoScreen;
import javafx.scene.layout.StackPane;

public class PlayButtonHandler extends ButtonHandler {

    public PlayButtonHandler(Stage stage) {
        super(stage);
    }

    @Override
    public void handle(ActionEvent event) {
        // Lógica para mostrar la pantalla de juego
        JuegoScreen pantallaJuego = new JuegoScreen(new StackPane());
        Scene gameScene = new Scene(pantallaJuego, 800, 600);
        stage.setScene(gameScene);
    }
}
