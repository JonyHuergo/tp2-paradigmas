package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CasosDeUsoTest {
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
        int cartasEsperadasMazo = 44;
        
        jugador.repartirCartas(8);
        int cartasObtenidasJugador = jugador.getCartasEnMano().size();
        //int cartasObtenidasMazo = mazo.cantidadDeCartas();

        // Les parece bien que haya dos asserts? Lo dividirian en dos tests?
        assertEquals(cartasEsperadasJugador, cartasObtenidasJugador, "El jugador debería tener 8 cartas en su mano después de repartir.");
        //assertEquals(cartasEsperadasMazo, cartasObtenidasMazo, "El mazo debería tener 44 cartas después de repartir 8.");
    }

    @Test
    public void test04EvaluarMano(){
        Puntaje puntajeEsperado = new Puntaje(920); // (100 por mano (escalera de color) + 15 por las cartas) * 8 por mano
        ManoPoker manoJugada = new ManoPoker();
        for (int i = 1; i <= 5; i++) {
            Carta carta = new Carta("diamantes", i);
            manoJugada.agregarCarta(carta);
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
            Carta c1 = new Carta("diamantes", i);
            manoJugada.agregarCarta(c1);
        }
        //Comodin
    }

    @Test
    public void test06TarotAgregar10PuntosModificaPuntosCorrectamente() {
        Puntaje puntajeEsperado = new Puntaje(19); // (5 por mano (carta alta) + 4 por la carta + 10 por tarot) * 1 por mano
        Carta carta = new Carta("diamantes", 4);
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
        Carta carta = new Carta("diamantes", 4);
        TarotMultiplicadorX6 tarot = new TarotMultiplicadorX6();
        ManoPoker manoJugada = new ManoPoker();
        
        tarot.aplicarEfecto(carta);
        manoJugada.agregarCarta(carta);
        Puntaje puntajeObtenido = manoJugada.evaluar();

        assertEquals(puntajeEsperado, puntajeObtenido, "El puntaje obtenido debería ser 54.");
    }
}
