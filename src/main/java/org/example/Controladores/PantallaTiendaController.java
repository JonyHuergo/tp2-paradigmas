package org.example.Controladores;

import javafx.scene.Scene;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import org.example.Jugador;
import org.example.Mazo;
import org.example.Pantallas.TiendaScreen;
import org.example.Ronda;
import org.example.Tienda;

import java.util.List;

public class PantallaTiendaController {
    private final Stage stage;
    private final MediaPlayer mediaPlayer;
    private Mazo mazo;
    private int puntajeASuperar;
    private Jugador jugador;
    private List<Ronda> rondas;
    private Ronda ronda;
    private int numeroRonda;

    public PantallaTiendaController(Stage stage, MediaPlayer mediaPlayer, Mazo mazo, List<Ronda> rondas, int numeroRonda,Jugador jugador) {
        this.stage = stage;
        this.mediaPlayer = mediaPlayer;
        this.mazo = mazo;
        this.jugador = jugador;
        this.rondas = rondas;
        this.numeroRonda = numeroRonda;
        this.ronda = rondas.get(numeroRonda);
        this.puntajeASuperar = ronda.getPuntajeASuperar();

    }

    public void iniciarPantallaTienda(Tienda tienda){
        int descartes = ronda.getDescartes();
        jugador.setCantidadDeDescartes(descartes);

        int manos= ronda.getCantidadDeManos();
        jugador.setCantidadDeManos(manos);

        TiendaScreen pantallaTienda = new TiendaScreen(tienda, stage, mediaPlayer, mazo , rondas, numeroRonda, jugador);
        Scene scene = new Scene(pantallaTienda, 800, 600);
        stage.setScene(scene);
    }

}
