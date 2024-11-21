package org.example.Evaluadores;

import org.example.Carta;

import java.util.ArrayList;
import java.util.Comparator;

public class EvaluadorEscaleraReal extends EvaluadorAbstracto {
    @Override
    protected String evaluarMano(ArrayList<Carta> cartas) {
        if (esColor(cartas) && esEscalera(cartas) && contieneAs(cartas)) {
            return "Escalera Real";
        }
        return null;
    }

    private boolean esColor(ArrayList<Carta> cartas) {
        // Lógica para verificar si todas las cartas son del mismo palo
        return cartas.stream().allMatch(c -> c.getPalo().equals(cartas.get(0).getPalo()));
    }

    private boolean esEscalera(ArrayList<Carta> cartas) {
        // Lógica para verificar si las cartas forman una secuencia
        cartas.sort(Comparator.comparingInt(Carta::getValor));
        for (int i = 0; i < cartas.size() - 1; i++) {
            if (cartas.get(i).getValor() + 1 != cartas.get(i + 1).getValor()) {
                return false;
            }
        }
        return true;
    }

    private boolean contieneAs(ArrayList<Carta> cartas) {
        // Verifica si hay un As en la mano
        return cartas.stream().anyMatch(c -> c.getValor() == 14);
    }
}