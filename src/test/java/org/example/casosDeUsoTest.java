package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class casosDeUsoTest {
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
    public void test03ReconocerTipoDeManoEscalera(){

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
}
