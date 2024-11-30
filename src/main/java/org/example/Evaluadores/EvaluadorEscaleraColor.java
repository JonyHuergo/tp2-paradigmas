package org.example.Evaluadores;

import org.example.Carta;
import org.example.Manos.EscaleraColor;
import org.example.Manos.Mano;

import java.util.ArrayList;
import java.util.Comparator;

public class EvaluadorEscaleraColor extends EvaluadorAbstracto {

    @Override
    public Mano evaluarMano(ArrayList<Carta> cartas) {
        if (cartas.size() == 5 && esEscalera(cartas) && esColor(cartas)) {
            return new EscaleraColor();
        }
        return null;
    }

    private boolean esEscalera(ArrayList<Carta> cartas) {
        cartas.sort(Comparator.comparingInt(Carta::getValor));
        Carta cartaAnterior = null;
        for (Carta carta : cartas) {
            if (cartaAnterior != null && carta.getValor() != cartaAnterior.getValor() + 1) {
                return false;
            }
            cartaAnterior = carta;  // Actualiza la cartaAnterior para la siguiente iteración
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
