package org.example;

import org.example.Comodin.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TestSemana2 {
    @Test
    public void test01ComodinSuma8AlMultiplicador() {
        int puntajeEsperado = 90; // Carta Alta: 5 * 1 -> 10 * 1 por ser un 5 -> 10 * 9 por el comodin

        ManoPoker manoPoker = new ManoPoker();
        Carta carta = new Carta("diamantes", 5);
        manoPoker.agregarCarta(carta);
        Comodin comodin = new ComodinBase(0,8,1, new ActivacionSiempre());
        ArrayList<Comodin> comodines = new ArrayList<Comodin>();
        comodines.add(comodin);
        Jugador jugador = new Jugador();
        jugador.setManoPoker(manoPoker);
        jugador.setComodines(comodines);
        jugador.evaluarMano();

        assertTrue(jugador.puntajeEsIgual(puntajeEsperado));
    }

    @Test
    public void test02ComodinAumentaPor3AlMultiplicadorSiTieneEscalera() {
        int puntajeEsperado = 540; // Escalera: 30 * 4 -> 45 * 4 por las 5 cartas -> 45 * 12 por el comodin

        ManoPoker manoPoker = new ManoPoker();
        for (int i = 1; i <= 4; i++) {
            Carta carta = new Carta("diamantes", i);
            manoPoker.agregarCarta(carta);
        }

        manoPoker.agregarCarta(new Carta("corazon", 5));

        Comodin comodin = new ComodinBase(0,0,3,  new ActivacionTipoDeMano("Escalera"));
        ArrayList<Comodin> comodines = new ArrayList<Comodin>();
        comodines.add(comodin);

        Jugador jugador = new Jugador();
        jugador.setManoPoker(manoPoker);
        jugador.setComodines(comodines);

        jugador.evaluarMano();

        assertTrue(jugador.puntajeEsIgual(puntajeEsperado));
    }

    @Test
    public void test03Comodin10PuntosPorCadaDescarte() {
        int puntajeEsperado = 40; // Carta Alta: 5 * 1 -> 10 * 1 por ser un 5 -> 40 * 1 por los tres descartes

        ManoPoker manoPoker = new ManoPoker();
        Carta carta = new Carta("diamantes", 5);
        manoPoker.agregarCarta(carta);
        Comodin comodin = new ComodinPorDescarte(0,0,1, new ActivacionDescartes());
        ArrayList<Comodin> comodines = new ArrayList<Comodin>();
        comodines.add(comodin);
        Jugador jugador = new Jugador();
        jugador.setManoPoker(manoPoker);
        jugador.setComodines(comodines);
        jugador.evaluarMano();

        assertTrue(jugador.puntajeEsIgual(puntajeEsperado));
    }

    @Test
    public void test04ComodinConProbabilidad1Sobre1000TieneEfecto() {
        // Usar mokito !!!
        int puntajeEsperado = 40; // Carta Alta: 5 * 1 -> 10 * 1 por ser un 5 -> 40 * 1 puntaje adicional

        ManoPoker manoPoker = new ManoPoker();
        Carta carta = new Carta("diamantes", 5);
        manoPoker.agregarCarta(carta);
        Comodin comodin = new ComodinBase(30,0,1, new ActivacionProbabilidad(1000));
        ArrayList<Comodin> comodines = new ArrayList<Comodin>();
        comodines.add(comodin);
        Jugador jugador = new Jugador();
        jugador.setManoPoker(manoPoker);
        jugador.setComodines(comodines);
        jugador.evaluarMano();

//        assertTrue(jugador.puntajeEsIgual(puntajeEsperado));

    }

    @Test
    public void test05ComodinConCombinacionDeEfectos() {
        // no entiendo estos tipos de comodines
    }

    @Test
    public void test06JSONSeLeeYConvierteCorrectamente() {

    }
}
