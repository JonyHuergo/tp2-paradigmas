package org.example.Controladores;

import javafx.scene.Scene;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import org.example.Jugador;
import org.example.Mazo;
import org.example.Pantallas.TiendaScreen;
import org.example.Ronda;
import org.example.Tienda;

public class PantallaTiendaController {
    private final Stage stage;
    private final MediaPlayer mediaPlayer;
    private Mazo mazo;
    private int puntajeASuperar;
    private Jugador jugador;
    private Ronda ronda;

    public PantallaTiendaController(Stage stage, MediaPlayer mediaPlayer, Mazo mazo, Ronda ronda, Jugador jugador) {
        this.stage = stage;
        this.mediaPlayer = mediaPlayer;
        this.mazo = mazo;
        this.puntajeASuperar = ronda.getPuntajeASuperar();
        this.jugador = jugador;
        this.ronda = ronda;
    }

    public void iniciarPantallaTienda(Tienda tienda){
        TiendaScreen pantallaTienda = new TiendaScreen(tienda, stage, mediaPlayer, mazo , ronda, jugador);
        Scene scene = new Scene(pantallaTienda, 800, 600);
        stage.setScene(scene);
    }

}
