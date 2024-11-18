package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

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
        //int cartasEsperadasMazo = 44;
        
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
        ManoPoker manoJugada = new ManoPoker();
        for (int i = 1; i <= 5; i++) {
            Carta c1 = new Carta("diamantes", i);
            manoJugada.agregarCarta(c1);
        }
        ArrayList<Comodin> comodines = new ArrayList<Comodin>();
        comodines.add(new Comodin(10,2));
        comodines.add(new Comodin(10,5));
        ManoComodines mano1Comodines = new ManoComodines(comodines);

        ArrayList<Comodin> comodines2 = new ArrayList<Comodin>();
        comodines2.add(new Comodin(10,5));
        comodines2.add(new Comodin(10,2));
        ManoComodines mano2Comodines = new ManoComodines(comodines2);

        Puntaje puntajeObtenido1 = mano1Comodines.aplicarComodines(manoJugada);
        Puntaje puntajeObtenido2 = mano2Comodines.aplicarComodines(manoJugada);

        assertFalse(puntajeObtenido1.equals(puntajeObtenido2));
    }

    @Test
    public void test06TarotAgregar10PuntosModificaPuntosCorrectamente() {
        Puntaje puntajeEsperado = new Puntaje(19); // (5 por mano (carta alta) + 4 por la carta + 10 por tarot) * 1 por mano
        Carta carta = new Carta("diamantes", 4);
        TarotAgregar10Puntos tarot = new TarotAgregar10Puntos("Agregador10pts", "Agrega10pts", new Efecto(10, 1), "cualquiera", "cualquiera");
        ManoPoker manoJugada = new ManoPoker();
        
        tarot.aplicarEfecto(carta);
        manoJugada.agregarCarta(carta);
        Puntaje puntajeObtenido = manoJugada.evaluar();

        assertEquals(puntajeEsperado, puntajeObtenido, "El puntaje obtenido debería ser 19.");
    }

    @Test
    public void test07TarotMultiplicadorX6ModificaPuntosCorrectamente(){
        Puntaje puntajeEsperado = new Puntaje(63); // (5 por mano (carta alta) + 4 por la carta) * (1 por mano (carta alta) + 6 por tarot)
        Carta carta = new Carta("diamantes", 4);
        TarotMultiplicadorX6 tarot = new TarotMultiplicadorX6("MultiplicadorX6", "multiplicador añade 6pts", new Efecto(1, 6), "cualquiera", "cualquiera");
        ManoPoker manoJugada = new ManoPoker();
        
        tarot.aplicarEfecto(carta);
        manoJugada.agregarCarta(carta);
        Puntaje puntajeObtenido = manoJugada.evaluar();

        assertEquals(puntajeEsperado, puntajeObtenido, "El puntaje obtenido debería ser 63.");
    }
}
