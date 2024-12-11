package org.example;

import org.example.Comodin.*;
import org.example.Tarot.TarotSobreCarta;
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

        jugador.setCantidadDeDescartes(3);
        jugador.setCantidadDeManos(5);

        float puntajeObtenido = jugador.jugar(0);

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
        jugador.jugar(0);

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

        jugador.setCantidadDeDescartes(3);
        jugador.setCantidadDeManos(5);

        jugador.crearJugada();

        // Jugada 2
        ManoPoker manoPoker2 = new ManoPoker();
        manoPoker2.agregarCarta(new Carta("diamantes", 10));
        jugador.setManoPoker(manoPoker2);

        float puntajeObtenido = jugador.jugar(0);

        assertEquals(puntajeEsperado, puntajeObtenido);
    }

    @Test
    public void test04UnJugadorRealizaUnaJugadaConUnComodinYUnTarot() {
        int puntajeEsperado = 448; //(puntajePar + puntajeCartas)<-(10 + 4) * (2+(2x3)4))->((multiplicarParmultiplicadorPorComodin) + multiplicadorCartas)

        ManoPoker manoPoker = new ManoPoker();
        Jugador jugador = new Jugador();

        // Comodín
        Comodin comodin = new ComodinPorDescarte(0, 2, "Descarte");
        ArrayList<Comodin> comodines = new ArrayList<Comodin>();
        comodines.add(comodin);
        jugador.setComodines(comodines);

        // Tarot
        TarotSobreCarta tarot = new TarotSobreCarta("La Emperatriz", "Mejora 1 carta seleccionada y la convierte en una multicarta.", "carta", "cualquiera", 1, 4);

        // Cartas (Pareja de 2)
        Carta carta = new Carta("carta", "diamantes", "2", 2, "1");
        manoPoker.agregarCarta(carta);

        Carta cartaMejorada = new Carta("cartaAMejorar", "diamantes", "2", 2, "1");
        tarot.usarSobre(cartaMejorada);
        manoPoker.agregarCarta(cartaMejorada);

        jugador.setManoPoker(manoPoker);
        jugador.agregarTarot(tarot);
        jugador.setCantidadDeManos(5);
        jugador.setCantidadDeDescartes(3);

        // Jugar
        float puntajeObtenido = jugador.jugar(0);

        assertEquals(puntajeEsperado, puntajeObtenido);
    }

}
