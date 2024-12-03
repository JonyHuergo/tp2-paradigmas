package org.example.Controladores;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import org.example.*;
import org.example.Handlers.CollectionButtonHandler;
import org.example.Handlers.ExitButtonHandler;
import org.example.Handlers.OptionsButtonHandler;
import org.example.Handlers.PlayButtonHandler;
import org.example.Pantallas.JuegoScreen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PantallaJuegoController {
    private final Stage stage;
    private final MediaPlayer mediaPlayer;

    public PantallaJuegoController(Stage stage, MediaPlayer mediaPlayer) {
        this.stage = stage;
        this.mediaPlayer = mediaPlayer;
    }

    public void iniciarPantallaJuego() {
        LectorArchivosJson lectorArchivosJson = new LectorArchivosJson();
        ArrayList<Carta> mazo = null;
        List<Ronda> rondas = null;
        try {
            rondas = lectorArchivosJson.leerBalatro();
            mazo = lectorArchivosJson.leerMazo();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al leer los archivos JSON.");
        }
        JuegoScreen pantallaJuego = new JuegoScreen(mazo, rondas.get(0).getPuntajeASuperar());

        /*pantallaInicio.playButton.setOnAction(new PlayButtonHandler(stage, mediaPlayer));
        pantallaInicio.optionsButton.setOnAction(new OptionsButtonHandler(stage, mediaPlayer));
        pantallaInicio.collectionButton.setOnAction(new CollectionButtonHandler(stage, mediaPlayer));
        pantallaInicio.exitButton.setOnAction(new ExitButtonHandler(stage, mediaPlayer));*/

        Scene gameScene = new Scene(pantallaJuego, 800, 600);
        stage.setScene(gameScene);

        /*Juego juego = new Juego(new Mazo(mazo));
            juego.setRondas(rondas);
            System.out.println("Juego iniciado.");
            juego.jugar(primaryStage);
            System.out.println("Juego terminado.");*/
    }
}
