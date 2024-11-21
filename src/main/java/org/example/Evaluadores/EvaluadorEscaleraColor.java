package org.example.Evaluadores;

import org.example.Carta;

import java.util.ArrayList;
import java.util.Comparator;

public class EvaluadorEscaleraColor extends EvaluadorAbstracto {

    @Override
    public String evaluarMano(ArrayList<Carta> cartas) {
        if (esEscalera(cartas) && esColor(cartas)) {
            return "Escalera Color";
        }
        return null;
    }

    private boolean esEscalera(ArrayList<Carta> cartas) {
        cartas.sort(Comparator.comparingInt(Carta::getValor));
        for (int i = 0; i < cartas.size() - 1; i++) {
            if (cartas.get(i).getValor() + 1 != cartas.get(i + 1).getValor()) {
                return false;
            }
        }
        return true;
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
