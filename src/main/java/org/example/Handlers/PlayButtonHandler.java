package org.example.Handlers;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import org.example.Pantallas.JuegoScreen;
import javafx.scene.layout.StackPane;

public class PlayButtonHandler extends ButtonHandler {

    public PlayButtonHandler(Stage stage, MediaPlayer mediaPlayer) {
        super(stage,mediaPlayer);

    }

    @Override
    public void handle(ActionEvent event) {
        // Lógica para mostrar la pantalla de juego
        ActionHandler.actionSound();
        JuegoScreen pantallaJuego = new JuegoScreen(new StackPane());
        Scene gameScene = new Scene(pantallaJuego, 800, 600);
        stage.setScene(gameScene);
    }
}
