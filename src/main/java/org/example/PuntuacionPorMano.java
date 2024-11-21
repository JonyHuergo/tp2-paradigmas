package org.example;

import org.example.Manos.Mano;

import java.util.ArrayList;

public class PuntuacionPorMano {
    private int puntajeBase;
    private int multiplicadorBase;

    public Puntaje calcular(ArrayList<Carta> cartas, Mano mano){
        definirTipoDeMano(cartas, mano);
        for (Carta carta : cartas) {
            puntajeBase = carta.actualizarPuntajeTotal(puntajeBase);
            multiplicadorBase = carta.actualizarMultiplicadorTotal(multiplicadorBase);
        }
        return new Puntaje(puntajeBase * multiplicadorBase);
    }

    private void definirTipoDeMano(ArrayList<Carta> cartas, Mano mano){
        puntajeBase = mano.getPuntajeBase();
        multiplicadorBase = mano.getMultiplicadorBase();

    }
}
