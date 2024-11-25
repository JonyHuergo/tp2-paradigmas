package org.example;

import org.example.Comodin.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


public class TestSemana2 {
    @Test
    public void test01ComodinSuma8AlMultiplicador() {
        int puntajeEsperado = 90; // Carta Alta: 5 * 1 -> 10 * 1 por ser un 5 -> 10 * 9 por el comodin

        ManoPoker manoPoker = new ManoPoker();
        Carta carta = new Carta("diamantes", 5);
        manoPoker.agregarCarta(carta);
        Comodin comodin = new ComodinBase(0,8,1, new ActivacionSiempre());
        ArrayList<Comodin> comodines = new ArrayList<>();
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
        ArrayList<Comodin> comodines = new ArrayList<>();
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
        ArrayList<Comodin> comodines = new ArrayList<>();
        comodines.add(comodin);
        Jugador jugador = new Jugador();
        jugador.setManoPoker(manoPoker);
        jugador.setComodines(comodines);
        jugador.evaluarMano();

        assertTrue(jugador.puntajeEsIgual(puntajeEsperado));
    }

    @Test
    public void test04ComodinConProbabilidad1Sobre1000TieneEfecto() {
        int puntajeEsperado = 40; // Carta Alta: 5 * 1 -> 10 * 1 por ser un 5 -> 40 * 1 puntaje adicional

        ActivacionProbabilidad activacionMock = Mockito.mock(ActivacionProbabilidad.class);
        when(activacionMock.revisarCondicion(any(Jugador.class))).thenReturn(true);

        ManoPoker manoPoker = new ManoPoker();
        Carta carta = new Carta("diamantes", 5);

        Comodin comodin = new ComodinBase(30, 0, 1, activacionMock);
        ArrayList<Comodin> comodines = new ArrayList<>();

        Jugador jugador = new Jugador();
        manoPoker.agregarCarta(carta);
        comodines.add(comodin);
        jugador.setManoPoker(manoPoker);
        jugador.setComodines(comodines);

        jugador.evaluarMano();

        assertTrue(jugador.puntajeEsIgual(puntajeEsperado));
    }

    @Test
    public void test05ComodinConCombinacionDeEfectos() {
        // Puntaje inicial por mano: 5
        // Puntaje por cartas: +5 => 10
        // Después de bonusDeMano: +10 => 20
        // Después de puntajeAumentado: +20 => 40
        // Después de activacionAleatoria: x2 => 80
        int puntajeEsperado = 80;

        ActivacionTipoDeMano activacionPorMano = new ActivacionTipoDeMano("Carta Alta");
        ActivacionSiempre activacionSiempre = new ActivacionSiempre();
        ActivacionProbabilidad activacionProbabilidad = Mockito.mock(ActivacionProbabilidad.class);
        when(activacionProbabilidad.revisarCondicion(any(Jugador.class))).thenReturn(true);

        Comodin bonusDeMano = new ComodinBase(10, 0, 1, activacionPorMano); // Suma 10 puntos
        Comodin puntajeAumentado = new ComodinBase(20, 0, 1, activacionSiempre); // Suma 20 puntos
        Comodin activacionAleatoria = new ComodinBase(0, 0, 2, activacionProbabilidad); // Multiplica el puntaje por 2

        ArrayList<Comodin> comodinesEnCombo = new ArrayList<>();
        comodinesEnCombo.add(bonusDeMano);
        comodinesEnCombo.add(puntajeAumentado);
        comodinesEnCombo.add(activacionAleatoria);
        ComodinCombo combo = new ComodinCombo(comodinesEnCombo);

        ArrayList<Comodin> comodinesDelJugador = new ArrayList<>();
        comodinesDelJugador.add(combo);

        ManoPoker manoPoker = new ManoPoker();
        Carta carta = new Carta("diamantes", 5);
        manoPoker.agregarCarta(carta);

        Jugador jugador = new Jugador();
        jugador.setManoPoker(manoPoker);
        jugador.setComodines(comodinesDelJugador);

        jugador.evaluarMano();
        combo.usar(jugador);

        assertTrue(jugador.puntajeEsIgual(puntajeEsperado));
    }

    @Test
    public void test06JSONSeLeeYConvierteCorrectamente() {

    }
}
