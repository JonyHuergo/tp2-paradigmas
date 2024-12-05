package org.example.Controladores;

import javafx.scene.Scene;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import org.example.Jugador;
import org.example.Mazo;
import org.example.Pantallas.TiendaScreen;
import org.example.Tienda;

public class PantallaTiendaController {
    private final Stage stage;
    private final MediaPlayer mediaPlayer;
    private Mazo mazo;
    private int puntajeASuperar;
    private Jugador jugador;

    public PantallaTiendaController(Stage stage, MediaPlayer mediaPlayer, Mazo mazo, int puntajeASuperar, Jugador jugador) {
        this.stage = stage;
        this.mediaPlayer = mediaPlayer;
        this.mazo = mazo;
        this.puntajeASuperar = puntajeASuperar;
        this.jugador = jugador;
    }

    public void iniciarPantallaTienda(Tienda tienda){
        TiendaScreen pantallaTienda = new TiendaScreen(tienda, stage, mediaPlayer, mazo , puntajeASuperar, jugador);
        Scene scene = new Scene(pantallaTienda, 800, 600);
        stage.setScene(scene);
    }

}
