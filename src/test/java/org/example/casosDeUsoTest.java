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

    //Verificar que al modificar una carta al utilizar un tarot que cambia sus puntos por 10,
    // se aplique el puntaje correcto en el mazo.
    @Test
    public void test06TarotAgregar10PuntosModificaPuntosCorrectamente(){
        Puntaje puntajeEsperado = new Puntaje(30);
        TarotAgregar10Puntos tarot = new TarotAgregar10Puntos();
        Carta carta = new Carta("Diamante", 1);
        tarot.aplicarEfecto(carta);
        ManoPoker manoJugada = new ManoPoker();
        Puntaje manoJugada.evaluar();

    }

    //Verificar que al modificar una carta utilizando un tarot que cambia su multiplicador
    // a un x6 se aplique el valor correspondiente.
    @Test
    public void test07TarotPorSiesModificaPuntosCorrectamente(){
        TarotMultiplicadorX6 tarot = new TarotMultiplicadorX6();
        Carta carta = new Carta("Diamante", 5);
        tarot.aplicarEfecto(carta);
        ManoPoker manoJugada = new ManoPoker();
    }
}
