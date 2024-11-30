package org.example;

import org.example.Comodin.*;
import org.example.Tarot.TarotAgregaPuntos;
import org.example.Tarot.TarotMultiplicador;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JugadaTest {
    @Test
    public void test01UnaJugadaSeRealizaCorrectamente() {
        int puntajeEsperado = 920;
        ManoPoker manoPoker = new ManoPoker();
        for (int i = 1; i <= 5; i++) {
            Carta carta = new Carta("diamantes", i);
            manoPoker.agregarCarta(carta);
        }

        Jugada jugada = new Jugada(manoPoker, new ArrayList<Comodin>(), 3);
        float puntajeObtenido = jugada.evaluarJugada();

        assertEquals(puntajeEsperado, puntajeObtenido);
    }

    @Test
    public void test02UnaJugadaConComodinSeRealizaCorrectamente() {
        int puntajeEsperado = 1150; // Escalera Color: 100 * 8 por mano -> 115 * 8 por cartas -> 115 * 10 por comodin

        ManoPoker manoPoker = new ManoPoker();
        for (int i = 1; i <= 5; i++) {
            Carta carta = new Carta("diamantes", i);
            manoPoker.agregarCarta(carta);
        }

        ArrayList<Comodin> comodines = new ArrayList<Comodin>();
        comodines.add(new ComodinPorManoJugada("nombre","descripcion", new ActivacionSiempre(),0,2));
        Jugada jugada = new Jugada(manoPoker, comodines, 3);
        float puntajeObtenido = jugada.evaluarJugada();

        assertEquals(puntajeEsperado, puntajeObtenido);
    }

    @Test
    public void test03UnaJugadaConMultiplesComodinesSeRealizaCorrectamente() {
        int puntajeEsperado = 11500; // Escalera Color: 100 * 8 por mano -> 115 * 8 por cartas -> 115 * 10 por comodin -> 115 * 100 por comodin

        ManoPoker manoPoker = new ManoPoker();
        for (int i = 1; i <= 5; i++) {
            Carta carta = new Carta("diamantes", i);
            manoPoker.agregarCarta(carta);
        }

        ArrayList<Comodin> comodines = new ArrayList<Comodin>();
        comodines.add(new ComodinPorManoJugada("nombre", "descripcion", new ActivacionSiempre(),0,2));
        comodines.add(new ComodinBase(0,10, new ActivacionSiempre()));

        Jugada jugada = new Jugada(manoPoker, comodines, 3);
        float puntajeObtenido = jugada.evaluarJugada();

        assertEquals(puntajeEsperado, puntajeObtenido);
    }


    @Test
    public void test04UnaListaDeJugadasSeRealizanCorrectamente() {
        int puntajeEsperado = 1195; // 45 de Jugada1 + 1150 de Jugada2
        float puntajeObtenido = 0;

        // Jugada 1: [5 por mano (carta alta) + 10 por las cartas] * (1 por mano * 3 por comodin) = 45
        ManoPoker manoPoker1 = new ManoPoker();
        manoPoker1.agregarCarta(new Carta("diamantes", 10));

        ArrayList<Comodin> comodines1 = new ArrayList<Comodin>();
        comodines1.add(new ComodinBase(0,3, new ActivacionSiempre()));

        Jugada jugada2 = new Jugada(manoPoker1, comodines1, 3);

        // Jugada 2: [100 por mano (escalera de color) + 15 por las cartas] * (8 por mano + 2 por comodin) = 1150
        ManoPoker manoPoker2 = new ManoPoker();
        for (int i = 1; i <= 5; i++) {
            Carta carta = new Carta("diamantes", i);
            manoPoker2.agregarCarta(carta);
        }

        ArrayList<Comodin> comodines2 = new ArrayList<Comodin>();
        comodines2.add(new ComodinPorManoJugada("nombre", "descripcion", new ActivacionSiempre(),0,2));

        Jugada jugada1 = new Jugada(manoPoker2, comodines2, 3);

        // Lista de Jugadas
        ArrayList<Jugada> jugadas = new ArrayList<Jugada>();
        jugadas.add(jugada1);
        jugadas.add(jugada2);

        for (Jugada jugada : jugadas){
            puntajeObtenido += jugada.evaluarJugada();
        }

        assertEquals(puntajeEsperado, puntajeObtenido);
    }


}
