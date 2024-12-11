package org.example;

import org.example.Comodin.Comodin;
import org.example.Comodin.ComodinBase;
import org.example.Tarot.Tarot;
import org.example.Tarot.TarotSobreMano;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestSemana1 {
    @Test
    public void test01TieneCartasSuficientes() {
        Mazo mazo = new Mazo();
        int cartasEsperadas = 52;

        int cartasObtenidas = mazo.cantidadDeCartas();

        assertEquals(cartasObtenidas, cartasEsperadas);
    }

    @Test
    public void test02RepartirCartas() {
        Jugador jugador = new Jugador();
        int cartasEsperadasJugador = 8;

        jugador.repartirCartas();
        int cartasObtenidasJugador = jugador.cantidadDeCartasDisponibles(); //

        assertEquals(cartasEsperadasJugador, cartasObtenidasJugador, "El jugador debería tener 8 cartas en su mano después de repartir.");
    }

    @Test
    public void test03JugarMano(){
        String manoEsperada = "escalera de color";
        ManoPoker manoPoker = new ManoPoker();
        for (int i = 2; i <= 6; i++) {
            Carta carta = new Carta("diamantes", i);
            manoPoker.agregarCarta(carta);
        }

        manoPoker.definirTipodeMano();

        assertTrue(manoPoker.manoNombreEsIgual(manoEsperada));
    }


    @Test
    public void test04EvaluarMano(){
        int puntajeEsperado = 960; // (100 por mano (escalera de color) + 20 por las cartas) * 8 por mano
        ManoPoker manoJugada = new ManoPoker();
        for (int i = 2; i <= 6; i++) {
            Carta carta = new Carta("diamantes", i);
            manoJugada.agregarCarta(carta);
        }

        float puntajeObtenido = manoJugada.evaluar();
        assertEquals(puntajeEsperado,puntajeObtenido);
    }

    @Test
    public void test05EvaluarManoConComodin(){
        float puntajeEsperado = 14;

        ManoPoker manoJugada = new ManoPoker();
        Carta c1 = new Carta("diamantes", 2);
        manoJugada.agregarCarta(c1);

        //Comodin
        Comodin comodin = new ComodinBase(0,2, "siempre");
        ArrayList<Comodin> comodines = new ArrayList<>();
        comodines.add(comodin);
        Jugada jugada = new Jugada(manoJugada,  comodines, 0);

        // Evaluar
        float puntajeObtenido = jugada.evaluarJugada();

        assertEquals(puntajeEsperado, puntajeObtenido);
    }

    @Test
    public void test06TarotAgregar10PuntosModificaPuntosCorrectamente() {
        float puntajeEsperado = 19; // (5 por mano (carta alta) + 4 por la carta + 10 por tarot) * 1 por mano

        // Cartas
        Carta carta = new Carta("diamantes", 4);
        ManoPoker manoJugada = new ManoPoker();
        manoJugada.agregarCarta(carta);

        // Tarots
        TarotSobreMano tarot = new TarotSobreMano("agregador10pts", "agrega10pts", "mano", "cualquiera", 10, 0);
        ArrayList<Tarot> tarots = new ArrayList<>();
        tarots.add(tarot);

        Jugada jugada = new Jugada(manoJugada,  new ArrayList<Comodin>(), 0, tarots);

        // Evaluar
        float puntajeObtenido = jugada.evaluarJugada();

        assertEquals(puntajeEsperado, puntajeObtenido, "El puntaje obtenido debería ser 19.");
    }

    @Test
    public void test07TarotMultiplicadorX6ModificaPuntosCorrectamente(){
        int puntajeEsperado = 64; // (5 por mano (carta alta) + 4 por la carta) * (1 por mano (carta alta) + 6 por tarot)
        Carta carta1 = new Carta("diamantes", 2);
        Carta carta2 = new Carta("diamantes", 2);
        Carta carta3 = new Carta("trebol", 4);
        Carta carta4 = new Carta("trebol", 4);
        Tarot tarot = new TarotSobreMano("La Suma Sacerdotisa", "Mejora la mano doble par", "mano", "doble par", 20, 2);
        ManoPoker manoJugada = new ManoPoker();
        manoJugada.agregarCarta(carta1);
        manoJugada.agregarCarta(carta2);
        manoJugada.agregarCarta(carta3);
        manoJugada.agregarCarta(carta4);
        manoJugada.definirTipodeMano();
        tarot.aplicarEfecto(manoJugada);
        float puntajeObtenido = manoJugada.evaluar();

        assertEquals(puntajeEsperado, puntajeObtenido, "El puntaje obtenido debería ser 63.");
    }
}