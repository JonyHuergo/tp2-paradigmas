package org.example.Evaluadores;

import org.example.Carta;
import org.example.Manos.Escalera;
import org.example.Manos.Mano;

import java.util.ArrayList;
import java.util.Comparator;

public class EvaluadorEscalera extends EvaluadorAbstracto {
    @Override
    protected Mano evaluarMano(ArrayList<Carta> cartas) {
        cartas.sort(Comparator.comparingInt(Carta::getValor));
        for (int i = 0; i < cartas.size() - 1; i++) {
            if (cartas.get(i).getValor() + 1 != cartas.get(i + 1).getValor()) {
                return null;
            }
        }
        return new Escalera();
    }
}