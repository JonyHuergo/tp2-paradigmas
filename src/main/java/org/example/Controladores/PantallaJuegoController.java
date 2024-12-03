package org.example.Controladores;

import javafx.scene.Scene;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import org.example.*;
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
        ArrayList<Carta> cartasLeidas = null;
        List<Ronda> rondas = null;
        try {
            rondas = lectorArchivosJson.leerBalatro();
            cartasLeidas = lectorArchivosJson.leerMazo();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al leer los archivos JSON.");
        }

        Mazo mazo = new Mazo(cartasLeidas);
        FlujoJuegoController juegoController = new FlujoJuegoController();
        JuegoScreen juegoScreen = new JuegoScreen(mazo.repartirCartas(8), rondas.get(0).getPuntajeASuperar(), juegoController);

        Scene scene = new Scene(juegoScreen, 800, 600);
        stage.setTitle("BALATRO");
        stage.setScene(scene);
        stage.show();
    }
}
