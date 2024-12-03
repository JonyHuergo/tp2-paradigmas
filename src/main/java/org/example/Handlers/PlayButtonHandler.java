package org.example.Handlers;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import org.example.Carta;
import org.example.Controladores.FlujoJuegoController;
import org.example.Controladores.FlujoRondasController;
import org.example.Controladores.PantallaJuegoController;
import org.example.Controladores.PantallaTiendaController;
import org.example.Pantallas.JuegoScreen;
import javafx.scene.layout.StackPane;
import org.example.Ronda;

import java.util.ArrayList;
import java.util.List;

public class PlayButtonHandler extends ButtonHandler {

//    private final PantallaJuegoController pantallaJuegoController;

    public PlayButtonHandler(Stage stage, MediaPlayer mediaPlayer) {
        super(stage, mediaPlayer);
//        this.pantallaJuegoController = new PantallaJuegoController(stage, mediaPlayer);
    }

    @Override
    public void handle(ActionEvent event) {
        ActionHandler.actionSound();
        // Delegar la acción al controlador
//        pantallaJuegoController.iniciarPantallaJuego();
        FlujoRondasController flujoRondas = new FlujoRondasController(stage, mediaPlayer);
        flujoRondas.iniciarFlujo();
    }
}
