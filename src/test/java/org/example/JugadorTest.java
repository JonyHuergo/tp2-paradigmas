package org.example;

import org.example.Comodin.*;
import org.example.Tarot.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JugadorTest {

    @Test
    public void test01UnJugadorRealizaUnaJugadaCorrectamente() {
        int puntajeEsperado = 28;

        ManoPoker manoPoker = new ManoPoker();
        for (int i = 1; i <= 2; i++) {
            Carta carta = new Carta("diamantes", 2);
            manoPoker.agregarCarta(carta);
        }

        Jugador jugador = new Jugador();
        jugador.setManoPoker(manoPoker);

        float puntajeObtenido = jugador.jugar();

        assertEquals(puntajeEsperado, puntajeObtenido);
    }

    @Test
    public void test02UnJugadorRealizaDosJugadasYSeCalculaSoloLaultima() {
        int puntajeEsperado = 15;

        // Jugada 1
        ManoPoker manoPoker1 = new ManoPoker();
        for (int i = 1; i <= 2; i++) {
            Carta carta = new Carta("diamantes", 2);
            manoPoker1.agregarCarta(carta);
        }

        Jugador jugador = new Jugador();
        jugador.setManoPoker(manoPoker1);
        jugador.jugar();

        // Jugada 2
        ManoPoker manoPoker2 = new ManoPoker();
        manoPoker2.agregarCarta(new Carta("diamantes", 10));
        jugador.setManoPoker(manoPoker2);
        jugador.crearJugada();

        float puntajeObtenido = jugador.evaluarJugadaActual();

        assertEquals(puntajeEsperado, puntajeObtenido);
    }
    /*
        @Test
        public void test03UnJugadorRealizaDosJugadasYCalculaTodas() {
            int puntajeEsperado = 43;

            // Jugada 1
            ManoPoker manoPoker1 = new ManoPoker();
            for (int i = 1; i <= 2; i++) {
                Carta carta = new Carta("diamantes", 2);
                manoPoker1.agregarCarta(carta);
            }

            Jugador jugador = new Jugador();
            jugador.setManoPoker(manoPoker1);
            jugador.crearJugada();

            // Jugada 2
            ManoPoker manoPoker2 = new ManoPoker();
            manoPoker2.agregarCarta(new Carta("diamantes", 10));
            jugador.setManoPoker(manoPoker2);

            float puntajeObtenido = jugador.jugar();

            assertEquals(puntajeEsperado, puntajeObtenido);
        }

        @Test
        public void test04UnJugadorRealizaUnaJugadaConUnComodin() {
            float puntajeEsperado = 512; //(puntajePar + puntajeCartas) * (multplicarPar * 2 del mult por cada descarte) -> (20 + 2 + 2 + 4 + 4) * (2 * 2 * 2 * 2)

            Jugador jugador = new Jugador();
            Comodin comodin = new ComodinPorDescarte(0,2, "Descarte");
            jugador.agregarComodin(comodin);

            ManoPoker manoPoker = new ManoPoker();
            for (int i = 1; i <= 2; i++) {
                Carta carta = new Carta("diamantes", 2);
                manoPoker.agregarCarta(carta);
            }
            for (int i = 1; i <= 2; i++) {
                Carta carta = new Carta("trebol", 4);
                manoPoker.agregarCarta(carta);
            }
            jugador.setManoPoker(manoPoker);

            float puntajeObtenido = jugador.jugar();

            assertEquals(puntajeEsperado, puntajeObtenido);

        }


        @Test
        public void test05UnJugadorRealizaUnaJugadaConUnTarot() {
            float puntajeEsperado = 256; //(puntajePar + puntajeCartas) * (multplicarPar + ) -> (20 + 2 + 2 + 4 + 4) * (2 * 4)

            Jugador jugador = new Jugador();
            TarotSobreCarta tarot = new TarotSobreCarta( "La Emperatriz","Mejora 1 carta seleccionada y la convierte en una multicarta.", "carta", "cualquiera",1, 4);

            ManoPoker manoPoker = new ManoPoker();
            for (int i = 1; i <= 2; i++) {
                Carta carta = new Carta("diamantes", 2);
                manoPoker.agregarCarta(carta);
                tarot.usarSobre(carta);
            }
            jugador.usarTarot(tarot);
            for (int i = 1; i <= 2; i++) {
                Carta carta = new Carta("trebol", 4);
                manoPoker.agregarCarta(carta);
            }
            jugador.setManoPoker(manoPoker);

            float puntajeObtenido = jugador.jugar();

            assertEquals(puntajeEsperado, puntajeObtenido);

        }

        @Test
        public void testXXUnJugadorRealizaUnaJugadaConUnComodinYUnTarot() {
            int puntajeEsperado = 196;//(puntajePar + puntajeCartas)<-(10 + 4)*(2x(2x3)+4))->(multiplicarPar)

            ManoPoker manoPoker = new ManoPoker();
            //TarotSobreCarta tarot = new TarotSobreCarta( "La Emperatriz","Mejora 1 carta seleccionada y la convierte en una multicarta.", "carta", "cualquiera",1, 4);
            //Comodin comodin = new ComodinPorDescarte(0,2, "Descarte");

            Carta cartaMejorada = new Carta("diamantes", 2);
            //tarot.usarSobre(cartaMejorada);
            Carta carta = new Carta("diamantes", 2);
            manoPoker.agregarCarta(cartaMejorada);
            manoPoker.agregarCarta(carta);
            Jugador jugador = new Jugador();
            jugador.setManoPoker(manoPoker);
            //ArrayList<Comodin> comodines = new ArrayList<Comodin>();
            //comodines.add(comodin);
            //jugador.setComodines(comodines);
            //tarot.usarSobre(cartaMejorada);
            //jugador.usarTarot(tarot);

            float puntajeObtenido = jugador.jugar();
            assertEquals(puntajeEsperado, puntajeObtenido);
        }


         */
    @Test
    public void test03UnJugadorRealizaUnaJugadaConUnComodinYUnTarot() {
        int puntajeEsperado = 224;//(puntajePar + puntajeCartas)<-(10 + 4)*(2x(2x3)+4))->((multiplicarPar*multiplicadorPorComodin) + multiplicadorCartas)

        ManoPoker manoPoker = new ManoPoker();
        TarotSobreCarta tarot = new TarotSobreCarta( "La Emperatriz","Mejora 1 carta seleccionada y la convierte en una multicarta.", "carta", "cualquiera",1, 4);
        Comodin comodin = new ComodinPorDescarte(0,2, "Descarte");

        Carta cartaMejorada = new Carta("cartaAMejorar", "diamantes", "2", 2, "0");
        Carta carta = new Carta("carta", "diamantes", "2", 2, "0");;
        manoPoker.agregarCarta(cartaMejorada);
        manoPoker.agregarCarta(carta);
        Jugador jugador = new Jugador();
        jugador.setManoPoker(manoPoker);
        ArrayList<Comodin> comodines = new ArrayList<Comodin>();
        comodines.add(comodin);
        jugador.setComodines(comodines);
        tarot.usarSobre(cartaMejorada);
        jugador.usarTarot(tarot);

        float puntajeObtenido = jugador.jugar();
        assertEquals(puntajeEsperado, puntajeObtenido);
    }
}
