package org.example;

import org.example.Comodin.*;
import org.example.Tarot.TarotAgregaPuntos;
import org.example.Tarot.TarotMultiplicador;
import org.example.Tarot.TarotSobreCarta;
import org.example.Tarot.TarotSobreMano;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JugadaTest {
    @Test
    public void test01UnaJugadaSeRealizaCorrectamente() {
        int puntajeEsperado = 960;
        ManoPoker manoPoker = new ManoPoker();
        for (int i = 2; i <= 6; i++) {
            Carta carta = new Carta("diamantes", i);
            manoPoker.agregarCarta(carta);
        }

        Jugada jugada = new Jugada(manoPoker, new ArrayList<Comodin>(), 3);
        float puntajeObtenido = jugada.evaluarJugada();

        assertEquals(puntajeEsperado, puntajeObtenido);
    }

    @Test
    public void test02UnaJugadaConComodinSeRealizaCorrectamente() {
        int puntajeEsperado = 1200; // Escalera Color: 100 * 8 por mano -> 120 * 8 por cartas -> 120 * 10 por comodin

        ManoPoker manoPoker = new ManoPoker();
        for (int i = 2; i <= 6; i++) {
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
        int puntajeEsperado = 12000; // Escalera Color: 100 * 8 por mano -> 120 * 8 por cartas -> 120 * 10 por comodin -> 120 * 100 por comodin

        ManoPoker manoPoker = new ManoPoker();
        for (int i = 2; i <= 6; i++) {
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
        int puntajeEsperado = 1245; // 45 de Jugada1 + 1200 de Jugada2
        float puntajeObtenido = 0;

        // Jugada 1: [5 por mano (carta alta) + 10 por las cartas] * (1 por mano * 3 por comodin) = 45
        ManoPoker manoPoker1 = new ManoPoker();
        manoPoker1.agregarCarta(new Carta("diamantes", 10));

        ArrayList<Comodin> comodines1 = new ArrayList<Comodin>();
        comodines1.add(new ComodinBase(0,3, new ActivacionSiempre()));

        Jugada jugada2 = new Jugada(manoPoker1, comodines1, 3);

        // Jugada 2: [100 por mano (escalera de color) + 20 por las cartas] * (8 por mano + 2 por comodin) = 1200
        ManoPoker manoPoker2 = new ManoPoker();
        for (int i = 2; i <= 6; i++) {
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

    @Test
    public void test05unaJugadaPreviaAUnTarotCalculaCorrectamente() {
        int puntajeEsperado = 3840;//(puntajeCartas + puntajeEscaleraColor)->(20 + 100) * (8 * 4)->(mulCartas + multDeEscaleraColor)
        ManoPoker manoPoker = new ManoPoker();
        TarotSobreCarta tarot = new TarotSobreCarta( "La Emperatriz","Mejora 1 carta seleccionada y la convierte en una multicarta.", "carta", "cualquiera",1, 4);

        Carta cartaMejorada = new Carta("diamantes", 2);
        tarot.usarSobre(cartaMejorada);//guardo la carta en el tarot

        manoPoker.agregarCarta(cartaMejorada);
        tarot.aplicarEfecto(manoPoker);
        //Carta aux = null;
        for (int i = 3; i <= 6; i++) {
            Carta carta = new Carta("diamantes", i);
            //aux = carta;
            manoPoker.agregarCarta(carta);
        }

        Jugada jugada = new Jugada(manoPoker, new ArrayList<Comodin>(), 3);

        // Se aplica el tarot
        tarot.aplicarEfecto(manoPoker);

        float puntajeObtenido = jugada.evaluarJugada();

        assertEquals(puntajeEsperado, puntajeObtenido);
    }
}