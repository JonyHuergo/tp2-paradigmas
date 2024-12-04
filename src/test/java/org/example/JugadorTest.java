package org.example;

import org.example.Comodin.ActivacionSiempre;
import org.example.Comodin.ActivacionTipoDeMano;
import org.example.Comodin.Comodin;
import org.example.Comodin.ComodinBase;
import org.example.Tarot.TarotAgregaPuntos;
import org.example.Tarot.TarotMultiplicador;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JugadorTest {

    @Test
    public void test01UnJugadorRealizaUnaJugadaCorrectamente() {
        int puntajeEsperado = 28;

        ManoPoker manoPoker = new ManoPoker();
        for (int i = 1; i <= 2; i++) {
            Carta carta = new Carta("diamantes", 2);
            manoPoker.agregarCarta(carta);
        }

        Jugador jugador = new Jugador();
        jugador.setManoPoker(manoPoker);

        float puntajeObtenido = jugador.jugar();

        assertEquals(puntajeEsperado, puntajeObtenido);
    }

    @Test
    public void test02UnJugadorRealizaDosJugadasYSeCalculaSoloLaultima() {
        int puntajeEsperado = 15;

        // Jugada 1
        ManoPoker manoPoker1 = new ManoPoker();
        for (int i = 1; i <= 2; i++) {
            Carta carta = new Carta("diamantes", 2);
            manoPoker1.agregarCarta(carta);
        }

        Jugador jugador = new Jugador();
        jugador.setManoPoker(manoPoker1);
        jugador.jugar();

        // Jugada 2
        ManoPoker manoPoker2 = new ManoPoker();
        manoPoker2.agregarCarta(new Carta("diamantes", 10));
        jugador.setManoPoker(manoPoker2);
        jugador.crearJugada();

        float puntajeObtenido = jugador.evaluarJugadaActual();

        assertEquals(puntajeEsperado, puntajeObtenido);
    }

    @Test
    public void test03UnJugadorRealizaDosJugadasYCalculaTodas() {
        int puntajeEsperado = 43;

        // Jugada 1
        ManoPoker manoPoker1 = new ManoPoker();
        for (int i = 1; i <= 2; i++) {
            Carta carta = new Carta("diamantes", 2);
            manoPoker1.agregarCarta(carta);
        }

        Jugador jugador = new Jugador();
        jugador.setManoPoker(manoPoker1);
        jugador.crearJugada();

        // Jugada 2
        ManoPoker manoPoker2 = new ManoPoker();
        manoPoker2.agregarCarta(new Carta("diamantes", 10));
        jugador.setManoPoker(manoPoker2);

        float puntajeObtenido = jugador.jugar();

        assertEquals(puntajeEsperado, puntajeObtenido);
    }
}
