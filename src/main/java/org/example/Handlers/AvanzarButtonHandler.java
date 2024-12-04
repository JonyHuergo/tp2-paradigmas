package org.example.Handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import org.example.Controladores.PantallaJuegoController;
import org.example.Mazo;

public class AvanzarButtonHandler implements EventHandler<ActionEvent> {

    private final PantallaJuegoController pantallaJuegoController;
    private Mazo mazo;
    private int puntajeASuperar;

    public AvanzarButtonHandler(Stage stage, MediaPlayer mediaPlayer, Mazo mazo, int puntajeASuperar) {
//        super(stage, mediaPlayer);
        this.pantallaJuegoController = new PantallaJuegoController(stage, mediaPlayer);
        this.mazo = mazo;
        this.puntajeASuperar = puntajeASuperar;
    }
    @Override
    public void handle(ActionEvent event) {
        ActionHandler.actionSound();
        pantallaJuegoController.iniciarPantallaJuego(mazo, puntajeASuperar);

    }
}
