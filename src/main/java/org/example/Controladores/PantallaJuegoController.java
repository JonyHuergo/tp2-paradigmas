package org.example.Controladores;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
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

    public void iniciarPantallaJuego(Mazo mazo, Ronda ronda, Jugador jugador) {
        Label manoLabel = new Label("Mano: Ninguna");

        Label puntajeLabel = new Label("0");
        puntajeLabel.setFont(Font.font("RetroFont", 20));

        Label multiplicadorLabel = new Label("0");
        multiplicadorLabel.setFont(Font.font("RetroFont", 20));

        manoLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: white; -fx-padding: 10;");

        jugador.repartirCartas();
        ArrayList<Carta> cartasIniciales = jugador.getCartasDisponibles();
        FlujoJuegoController juegoController = new FlujoJuegoController(stage, ronda,manoLabel, puntajeLabel, multiplicadorLabel, cartasIniciales, mazo, jugador);

        JuegoScreen juegoScreen = new JuegoScreen(cartasIniciales, ronda, juegoController, mazo, jugador, jugador.getComodines());

        Scene scene = new Scene(juegoScreen, 800, 600);
        stage.setTitle("BALATRO");
        stage.setScene(scene);
        stage.show();

    }

}
