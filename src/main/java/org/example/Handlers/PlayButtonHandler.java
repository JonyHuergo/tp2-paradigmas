package org.example.Handlers;

import javafx.event.ActionEvent;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import org.example.Controladores.FlujoRondasController;
import org.example.Jugador;
import org.example.Ronda;
import java.util.List;

public class PlayButtonHandler extends ButtonHandler {

    private final List<Ronda> rondas;
    private final int numeroRonda;
    private final Jugador jugador;

    public PlayButtonHandler(Stage stage, MediaPlayer mediaPlayer, List<Ronda> rondas, int numeroRonda, Jugador jugador) {
        super(stage, mediaPlayer);
        this.rondas = rondas;
        this.numeroRonda = numeroRonda;
        this.jugador = jugador;
    }

    @Override
    public void handle(ActionEvent event) {
        ActionHandler.actionSound();
        FlujoRondasController flujoRondas = new FlujoRondasController(stage, mediaPlayer);
        flujoRondas.iniciarFlujo(rondas, numeroRonda, jugador);
    }
}
