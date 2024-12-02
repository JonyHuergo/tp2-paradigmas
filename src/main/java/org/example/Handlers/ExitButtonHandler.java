package org.example.Handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class ExitButtonHandler extends ButtonHandler {
    public ExitButtonHandler(Stage stage, MediaPlayer mediaPlayer) {
        super(stage, mediaPlayer); // Ruta al archivo de sonido
    }
    @Override
    public void handle(ActionEvent event) {
        System.out.println("Saliendo del juego...");
        System.exit(0); // Cierra la aplicación
    }
}
