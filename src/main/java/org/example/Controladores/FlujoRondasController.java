package org.example.Controladores;

import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import org.example.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FlujoRondasController {
    private final Stage stage;
    private final MediaPlayer mediaPlayer;

        public FlujoRondasController(Stage stage, MediaPlayer mediaPlayer) {
            this.mediaPlayer = mediaPlayer;
            this.stage = stage;
        }

        public void iniciarFlujo( List<Ronda> rondas, int numeroRonda, Jugador jugador){
//            Juego juego = new Juego();
//            LectorArchivosJson lectorArchivosJson = new LectorArchivosJson();
//            ArrayList<Carta> cartasLeidas = null;
////            List<Ronda> rondas = null;
//            try {
//                rondas = lectorArchivosJson.leerBalatro();
//                cartasLeidas = lectorArchivosJson.leerMazo();
//            } catch (IOException e) {
//                e.printStackTrace();
//                System.out.println("Error al leer los archivos JSON.");
//            }
//            Mazo mazo = new Mazo(cartasLeidas);
//            Juego juego = new Juego(mazo);
////            Jugador jugador = juego.getJugador();
//
////            int numeroRonda = 0;
//            Ronda ronda = rondas.get(numeroRonda);
//            int descartes = ronda.getDescartes();
//            jugador.setCantidadDeDescartes(descartes);
//
//            int manos= ronda.getCantidadDeManos();
//            jugador.setCantidadDeManos(manos);

            Ronda ronda = rondas.get(numeroRonda);

            PantallaTiendaController pantallaTienda = new PantallaTiendaController(stage, mediaPlayer, null, rondas, numeroRonda, jugador);
            pantallaTienda.iniciarPantallaTienda(ronda.obtenerTienda());



            PantallaJuegoController pantallaJuego= new PantallaJuegoController(stage, mediaPlayer);

        }
}
