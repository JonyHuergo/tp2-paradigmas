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






            for(int i = 0; i <= rondas.size()-1; i++) {
                Ronda ronda = rondas.get(0);
                int descartes = ronda.getDescartes();
                jugador.setCantidadDeDescartes(descartes);

                int manos= ronda.getCantidadDeManos();
                jugador.setCantidadDeManos(manos);

                int numeroRonda = 0;

                PantallaTiendaController pantallaTienda = new PantallaTiendaController(stage, mediaPlayer, mazo, rondas, numeroRonda, jugador);
                pantallaTienda.iniciarPantallaTienda(ronda.obtenerTienda());


            }


            PantallaJuegoController pantallaJuego= new PantallaJuegoController(stage, mediaPlayer);
//            pantallaJuego.iniciarPantallaJuego(mazo, ronda, jugador);


//            for (int i = ronda.getCantidadDeManos()-1; i<=0; i--){
//                pantallaJuego.iniciarPantallaJuego(mazo, ronda, jugador);
//            }


//        for (int i = rondas.size() - 1; i >= 0; i--) {
//            PantallaTiendaController pantallaTienda = new PantallaTiendaController(stage, mediaPlayer, mazo, ronda, jugador);
//            pantallaTienda.iniciarPantallaTienda(ronda.obtenerTienda());
//            Ronda currentRonda = rondas.get(i);
//            for (int j = currentRonda.getCantidadDeManos() - 1; j >= 0; j--) {
//                int descartes = ronda.getDescartes();
//                jugador.setCantidadDeDescartes(descartes);
//
//
//            }


//        }

//            for (int i = rondas.size() - 1; i >= 0; i--) {
//
////                Ronda currentRonda = rondas.get(i);
////                ArrayList<Carta> cartasIniciales = jugador.getMazo().repartirCartas(5);
////                int puntajeASuperar = currentRonda.getPuntajeASuperar();
////                mostrarRonda(primaryStage, cartasIniciales, puntajeASuperar);
//            }
        }
}
