package org.example;

import org.example.Tarot.TarotAgregaPuntos;
import org.example.Tarot.TarotMultiplicador;
import org.junit.jupiter.api.Test;
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
        String manoEsperada = "Escalera Color";
        ManoPoker manoPoker = new ManoPoker();
        for (int i = 1; i <= 5; i++) {
            Carta carta = new Carta("diamantes", i);
            manoPoker.agregarCarta(carta);
        }

        manoPoker.definirTipodeMano();

        assertTrue(manoPoker.manoNombreEsIgual(manoEsperada));
    }


    @Test
    public void test04EvaluarMano(){
        int puntajeEsperado = 920; // (100 por mano (escalera de color) + 15 por las cartas) * 8 por mano
        ManoPoker manoJugada = new ManoPoker();
        for (int i = 1; i <= 5; i++) {
            Carta carta = new Carta("diamantes", i);
            manoJugada.agregarCarta(carta);
        }

        int puntajeObtenido = manoJugada.evaluar();
        assertEquals(puntajeEsperado,puntajeObtenido);
    }

    @Test
    public void test05EvaluarManoConComodin(){
        int puntajeEsperado = 30;
        ManoPoker manoJugada = new ManoPoker();
        for (int i = 1; i <= 5; i++) {
            Carta c1 = new Carta("diamantes", i);
            manoJugada.agregarCarta(c1);
        }
        //Comodin
    }

    @Test
    public void test06TarotAgregar10PuntosModificaPuntosCorrectamente() {
        int puntajeEsperado = 19; // (5 por mano (carta alta) + 4 por la carta + 10 por tarot) * 1 por mano
        Carta carta = new Carta("diamantes", 4);
        TarotAgregaPuntos tarot = new TarotAgregaPuntos(10);
        ManoPoker manoJugada = new ManoPoker();
        
        tarot.aplicarEfecto(carta);
        manoJugada.agregarCarta(carta);
        int puntajeObtenido = manoJugada.evaluar();

        assertEquals(puntajeEsperado, puntajeObtenido, "El puntaje obtenido debería ser 19.");
    }

    @Test
    public void test07TarotMultiplicadorX6ModificaPuntosCorrectamente(){
        int puntajeEsperado = 63; // (5 por mano (carta alta) + 4 por la carta) * (1 por mano (carta alta) + 6 por tarot)
        Carta carta = new Carta("diamantes", 4);
        TarotMultiplicador tarot = new TarotMultiplicador(6);
        ManoPoker manoJugada = new ManoPoker();
        
        tarot.aplicarEfecto(carta);
        manoJugada.agregarCarta(carta);
        int puntajeObtenido = manoJugada.evaluar();

        assertEquals(puntajeEsperado, puntajeObtenido, "El puntaje obtenido debería ser 63.");
    }
}
