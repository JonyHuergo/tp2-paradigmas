package org.example;

import org.example.Comodin.*;
import org.example.Tarot.Tarot;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


public class TestSemana2 {
    @Test
    public void test01ComodinSuma8AlMultiplicador() {
        float puntajeEsperado = 90; // Carta Alta: 5 * 1 -> 10 * 1 por ser un 5 -> 10 * 9 por el comodin

        ManoPoker manoPoker = new ManoPoker();
        Carta carta = new Carta("diamantes", 5);
        manoPoker.agregarCarta(carta);
        Comodin comodin = new ComodinBase(0,9, "siempre");
        ArrayList<Comodin> comodines = new ArrayList<>();
        comodines.add(comodin);
        Jugador jugador = new Jugador();
        jugador.setManoPoker(manoPoker);
        jugador.setComodines(comodines);

        float puntajeObtenido = jugador.jugar();

        assertEquals(puntajeEsperado, puntajeObtenido);
    }

    @Test
    public void test02ComodinAumentaPor3AlMultiplicadorSiTieneEscalera() {
        int puntajeEsperado = 600; // Escalera: 30 * 4 -> 50 * 4 por las 5 cartas -> 50 * 12 por el comodin

        ManoPoker manoPoker = new ManoPoker();
        for (int i = 2; i <= 5; i++) {
            Carta carta = new Carta("diamantes", i);
            manoPoker.agregarCarta(carta);
        }

        manoPoker.agregarCarta(new Carta("corazon", 6));
        //ComodinBase(int puntajeAdicional, float multiplicador, String activacion)
        Comodin comodin = new ComodinBase(0,3,  "Escalera");
        ArrayList<Comodin> comodines = new ArrayList<>();
        comodines.add(comodin);

        Jugador jugador = new Jugador();
        jugador.setManoPoker(manoPoker);
        jugador.setComodines(comodines);

        float puntajeObtenido = jugador.jugar();

        assertEquals(puntajeEsperado, puntajeObtenido);
    }

    @Test
    public void test03Comodin10PuntosPorCadaDescarte() {
        int puntajeEsperado = 40; // Carta Alta: 5 * 1 -> 10 * 1 por ser un 5 -> 40 * 1 por los tres descartes

        ManoPoker manoPoker = new ManoPoker();
        Carta carta = new Carta("diamantes", 5);
        manoPoker.agregarCarta(carta);
        Comodin comodin = new ComodinPorDescarte(10,1, "Descarte");
        ArrayList<Comodin> comodines = new ArrayList<>();
        comodines.add(comodin);
        Jugador jugador = new Jugador();
        jugador.setManoPoker(manoPoker);
        jugador.setComodines(comodines);

        float puntajeObtenido = jugador.jugar();

        assertEquals(puntajeEsperado, puntajeObtenido);
    }

    @Test
    public void test04ComodinConProbabilidad1Sobre1000TieneEfecto() {
        int puntajeEsperado = 40; // Carta Alta: 5 * 1 -> 10 * 1 por ser un 5 -> 40 * 1 puntaje adicional

        ActivacionProbabilidad activacionMock = Mockito.mock(ActivacionProbabilidad.class);
        when(activacionMock.revisarCondicion(any(Jugada.class))).thenReturn(true);

        ManoPoker manoPoker = new ManoPoker();
        Carta carta = new Carta("diamantes", 5);

        Comodin comodin = new ComodinBase(30,1, activacionMock);
        ArrayList<Comodin> comodines = new ArrayList<>();

        Jugador jugador = new Jugador();
        manoPoker.agregarCarta(carta);
        comodines.add(comodin);
        jugador.setManoPoker(manoPoker);
        jugador.setComodines(comodines);

        float puntajeObtenido = jugador.jugar();

        assertEquals(puntajeEsperado, puntajeObtenido);
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
        when(activacionProbabilidad.revisarCondicion(any(Jugada.class))).thenReturn(true);

        Comodin bonusDeMano = new ComodinBase(10,  1, activacionPorMano); // Suma 10 puntos
        Comodin puntajeAumentado = new ComodinBase(20, 1, activacionSiempre); // Suma 20 puntos
        Comodin activacionAleatoria = new ComodinBase(0, 2, activacionProbabilidad); // Multiplica el puntaje por 2

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

        float puntajeObtenido = jugador.jugar();

        assertEquals(puntajeEsperado, puntajeObtenido);
    }

    @Test
    public void test06JSONSeLeeYConvierteCorrectamente() throws IOException {
        LectorArchivosJson lectorArchivosJson = new LectorArchivosJson();
        List<Ronda> rondas = lectorArchivosJson.leerBalatro();
        for (Ronda ronda : rondas) {
            assertTrue(ronda instanceof Ronda);
            Tienda tienda = ronda.obtenerTienda();
            for (Comodin comodin : tienda.obtenerComodines()) {
                assertTrue(comodin instanceof Comodin);
            }
            for (Tarot tarot : tienda.obtenerTarots()) {
                assertTrue(tarot instanceof Tarot);
            }
            Carta carta = tienda.obtenerCarta();
            assertTrue(carta instanceof Carta);
        }
    }
}