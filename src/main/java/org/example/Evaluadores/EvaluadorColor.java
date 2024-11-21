package org.example.Evaluadores;

import org.example.Carta;

import java.util.ArrayList;

public class EvaluadorColor extends EvaluadorAbstracto {

    @Override
    public String evaluarMano(ArrayList<Carta> cartas) {
        if (esColor(cartas)) {
            return "Color";
        }
        return null;
    }

    private boolean esColor(ArrayList<Carta> cartas) {
        String palo = cartas.get(0).getPalo();
        for (Carta carta : cartas) {
            if (!carta.getPalo().equals(palo)) {
                return false;
            }
        }
        return true;
    }
}
