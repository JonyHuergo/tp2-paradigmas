package org.example.Controladores;

import javafx.scene.Scene;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import org.example.Carta;
import org.example.Handlers.CollectionButtonHandler;
import org.example.Handlers.ExitButtonHandler;
import org.example.Handlers.OptionsButtonHandler;
import org.example.Handlers.PlayButtonHandler;
import org.example.Jugador;
import org.example.LectorArchivosJson;
import org.example.Pantallas.PantallaInicioScreen;
import org.example.Ronda;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PantallaInicioController {
    private final Stage stage;
    private final MediaPlayer mediaPlayer;

    public PantallaInicioController(Stage stage, MediaPlayer mediaPlayer) {
        this.stage = stage;
        this.mediaPlayer = mediaPlayer;
    }

    public void iniciarPantallaInicio(List<Ronda> rondas, int numeroRonda, Jugador jugador) {

        PantallaInicioScreen pantallaInicio = new PantallaInicioScreen();

        // Asignar los controladores a cada botón
        pantallaInicio.playButton.setOnAction(new PlayButtonHandler(stage, mediaPlayer, rondas, numeroRonda, jugador));
        pantallaInicio.optionsButton.setOnAction(new OptionsButtonHandler(stage, mediaPlayer));
        pantallaInicio.collectionButton.setOnAction(new CollectionButtonHandler(stage, mediaPlayer));
        pantallaInicio.exitButton.setOnAction(new ExitButtonHandler(stage, mediaPlayer));

        Scene scene = new Scene(pantallaInicio, 800, 600);
        stage.setScene(scene);
    }
}
