package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CasosDeUsoTest {
    @Test
    public void test01TieneCartasSuficientes() {
        Mazo mazo = new Mazo();
        int cartasEsperadas = 52;

        int cartasObtenidas = mazo.getCartas().size();

        assertEquals(cartasObtenidas, cartasEsperadas);
    }

    @Test
    public void test02RepartirCartas() {
        Jugador jugador = new Jugador();
        jugador.repartirCartas(8);

        int cartasEsperadas = 8;
        int cartasObtenidas = jugador.getCantidadCartas();

        assertEquals(cartasEsperadas, cartasObtenidas);
//        assertTrue(true, "El mazo debería tener 44 cartas después de repartir 8.");
    }

    @Test
    public void test04EvaluarMano(){
        Puntaje puntajeEsperado = new Puntaje(800);
        ManoPoker manoJugada = new ManoPoker();
        for (int i = 1; i <= 5; i++) {
            Carta c1 = new Carta("Diamante", i);
            manoJugada.agregarCarta(c1);
        }
        assertTrue(puntajeEsperado.equals(manoJugada.evaluar()));
    }


    @Test
    public void test03JugarMano(){
        //work in progress
    }

    @Test
    public void test05EvaluarManoConComodin(){
        Puntaje puntajeEsperado = new Puntaje(30);
        ManoPoker manoJugada = new ManoPoker();
        for (int i = 1; i <= 5; i++) {
            Carta c1 = new Carta("Diamante", i);
            manoJugada.agregarCarta(c1);
        }
        //Comodin
    }

    @Test
    public void test06TarotAgregar10PuntosModificaPuntosCorrectamente() {
        Puntaje puntajeEsperado = new Puntaje(19); // (5 por mano (carta alta) + 4 por la carta + 10 por tarot) * 1 por mano
        Carta carta = new Carta("Diamante", 4);
        TarotAgregar10Puntos tarot = new TarotAgregar10Puntos();
        ManoPoker manoJugada = new ManoPoker();
        
        tarot.aplicarEfecto(carta);
        manoJugada.agregarCarta(carta);
        Puntaje puntajeObtenido = manoJugada.evaluar();

        assertEquals(puntajeEsperado, puntajeObtenido, "El puntaje obtenido debería ser 19.");
    }

    @Test
    public void test07TarotMultiplicadorX6ModificaPuntosCorrectamente(){
        Puntaje puntajeEsperado = new Puntaje(54); // (5 por mano (carta alta) + 4 por la carta) * 6 por tarot
        Carta carta = new Carta("Diamante", 4);
        TarotMultiplicadorX6 tarot = new TarotMultiplicadorX6();
        ManoPoker manoJugada = new ManoPoker();
        
        tarot.aplicarEfecto(carta);
        manoJugada.agregarCarta(carta);
        Puntaje puntajeObtenido = manoJugada.evaluar();

        assertEquals(puntajeEsperado, puntajeObtenido, "El puntaje obtenido debería ser 54.");
    }
}
