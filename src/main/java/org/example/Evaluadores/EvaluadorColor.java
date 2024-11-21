package org.example.Evaluadores;

import org.example.Carta;
import org.example.Manos.Color;
import org.example.Manos.Mano;

import java.util.ArrayList;

public class EvaluadorColor extends EvaluadorAbstracto {

    @Override
    public Mano evaluarMano(ArrayList<Carta> cartas) {
        if (esColor(cartas)) {
            return new Color();
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
