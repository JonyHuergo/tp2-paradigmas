package org.example;
import org.example.Comodin.Comodin;
import org.example.Tarot.Tarot;
import org.example.Tarot.TarotSobreMano;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JuegoTest {

    private Mazo mazo;
    private Juego juego;


    @Test
    // Jugador compra primero un comodin juega gana la primera ronda,
    // pasa a la segunda compra un tarot y pierde
    public void end2end() {
        // Leer archivo JSON
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

        // Configurar juego
        Juego juego = new Juego();
        juego.leerArchivo();
        Jugador jugador = juego.getJugador();

        // Ronda 1
        int numeroRonda = 0;
        Ronda ronda = rondas.get(numeroRonda);

        int descartes = ronda.getDescartes();
        jugador.setCantidadDeDescartes(descartes);

        int manos = ronda.getCantidadDeManos();
        jugador.setCantidadDeManos(manos);

        // Tienda Ronda 1
        Tienda tienda = ronda.obtenerTienda();
        List<Comodin> comodines = tienda.obtenerComodines();
        jugador.agregarComodin(comodines.get(0));//"Comodin Astuto"

        // Juego Ronda 1
        float puntaje = 0;
        while (puntaje < ronda.getPuntajeASuperar() && manos > 0) {
            manos -= 1;
            for (int i = 2; i < 7; i++) {
                Carta carta = new Carta("diamantes", i);
                jugador.agregarCarta(carta);
            }
            puntaje = jugador.jugar(numeroRonda);
            assertFalse(jugador.perdio());
        }

        // Ronda 2
        numeroRonda = 1;
        ronda = rondas.get(numeroRonda);

        descartes = ronda.getDescartes();
        jugador.setCantidadDeDescartes(descartes);

        manos = ronda.getCantidadDeManos();
        jugador.setCantidadDeManos(manos);

        // Tienda Ronda 2
        tienda = ronda.obtenerTienda();
        List<Tarot> tarots = tienda.obtenerTarots();
        jugador.agregarTarot(tarots.get(0));//"FUERZA"

        // Juego Ronda 2
        puntaje = 0;
        while (puntaje < ronda.getPuntajeASuperar() && manos > 0) {//(60+(4x4)+30)*((7+3)+(15x3))=5830, deberia dar 7420 pero jugador tiene los descartes hardcodeados
            Carta carta1 = new Carta("trebol", 4);
            Carta carta2 = new Carta("picas", 4);
            Carta carta3 = new Carta("diamantes", 4);
            Carta carta4 = new Carta("corazones", 4);
            jugador.agregarCarta(carta1);
            jugador.agregarCarta(carta2);
            jugador.agregarCarta(carta3);
            jugador.agregarCarta(carta4);
            puntaje = jugador.jugar(numeroRonda);
            manos -= 1;
        }
        assertTrue(puntaje > ronda.getPuntajeASuperar());

        //Ronda 3
        numeroRonda = 2;
        ronda = rondas.get(numeroRonda);

        descartes = ronda.getDescartes();
        jugador.setCantidadDeDescartes(descartes);

        manos = ronda.getCantidadDeManos();
        jugador.setCantidadDeManos(manos);

        // Tienda Ronda 3
        tienda = ronda.obtenerTienda();
        Carta carta = tienda.obtenerCarta();
        jugador.agregarCartaMazo(carta);

        // Juego Ronda 3
        puntaje = 0;
        while (puntaje < ronda.getPuntajeASuperar() && manos > 0){
            Carta cartaDePerdedor1 = new Carta("trebol", 2);
            jugador.agregarCarta(cartaDePerdedor1);
            puntaje = jugador.jugar(numeroRonda);
            manos -= 1;
        }

        assertTrue(puntaje < ronda.getPuntajeASuperar());
    }
}
