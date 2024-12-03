package org.example.Handlers;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import org.example.Controladores.PantallaJuegoController;
import org.example.Pantallas.JuegoScreen;
import javafx.scene.layout.StackPane;

public class PlayButtonHandler extends ButtonHandler {

    private final PantallaJuegoController pantallaJuegoController;

    public PlayButtonHandler(Stage stage, MediaPlayer mediaPlayer) {
        super(stage, mediaPlayer);
        this.pantallaJuegoController = new PantallaJuegoController(stage, mediaPlayer);
    }

    @Override
    public void handle(ActionEvent event) {
        ActionHandler.actionSound();
        // Delegar la acción al controlador
        pantallaJuegoController.iniciarPantallaJuego();
    }
}
