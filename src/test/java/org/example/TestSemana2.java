package org.example;

import org.example.Comodin.Comodin;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TestSemana2 {
    @Test
    public void test01ComodinSuma8AlMultiplicador() {
        int puntajeEsperado = 90;

        ManoPoker manoPoker = new ManoPoker();
        Carta carta = new Carta("diamantes", 5);
        manoPoker.agregarCarta(carta);
        Comodin comodin = new Comodin(0,8,1);
        ArrayList<Comodin> comodines = null;
        comodines.add(comodin);
        Jugador jugador = new Jugador();
        jugador.setManoPoker(manoPoker);
        jugador.setComodines(comodines);
        jugador.evaluarMano();

//        int puntajeObtenido = manoPoker.evaluar(comodin);
        assertTrue(jugador.puntajeEsIgual(puntajeEsperado));
    }

    @Test
    public void test02ComodinAumentaPor3AlMultiplicadorSiTieneEscalera() {

    }

    @Test
    public void test03Comodin10PuntosPorDescarte() {

    }

    @Test
    public void test04ComodinConChanceDeRomperseSeRompe() {

    }

    @Test
    public void test05ComodinConCombinacionDeEfectos() {

    }

//    @Test
//    public void test06JSONSeLeeYConvierteCorrectamente() {
//
//    }
}
