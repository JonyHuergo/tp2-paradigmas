package org.example.Evaluadores;

import org.example.Carta;

import java.util.ArrayList;
import java.util.Comparator;

public class EvaluadorEscalera extends EvaluadorAbstracto {
    @Override
    protected String evaluarMano(ArrayList<Carta> cartas) {
        cartas.sort(Comparator.comparingInt(Carta::getValor));
        for (int i = 0; i < cartas.size() - 1; i++) {
            if (cartas.get(i).getValor() + 1 != cartas.get(i + 1).getValor()) {
                return null;
            }
        }
        return "Escalera";
    }
}