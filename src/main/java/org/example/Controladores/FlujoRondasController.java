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




        public void iniciarFlujo(){

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
            Juego juego = new Juego(mazo);
            Jugador jugador = juego.getJugador();


            Ronda ronda = rondas.get(0);
            int descartes = ronda.getDescartes();
            PantallaTiendaController pantallaTienda = new PantallaTiendaController(stage, mediaPlayer, mazo, ronda.getPuntajeASuperar(), jugador);
            pantallaTienda.iniciarPantallaTienda(ronda.obtenerTienda());


//            for (int i = rondas.size() - 1; i >= 0; i--) {
//
////                Ronda currentRonda = rondas.get(i);
////                ArrayList<Carta> cartasIniciales = jugador.getMazo().repartirCartas(5);
////                int puntajeASuperar = currentRonda.getPuntajeASuperar();
////                mostrarRonda(primaryStage, cartasIniciales, puntajeASuperar);
//            }
        }
}
