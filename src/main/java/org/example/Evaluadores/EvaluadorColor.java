package org.example.Evaluadores;

import org.example.Carta;
import org.example.Manos.Color;
import org.example.Manos.Mano;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EvaluadorColor extends EvaluadorAbstracto {

    @Override
    public Mano evaluarMano(ArrayList<Carta> cartas) {
        if (esColor(cartas)) {
            return new Color();
        }
        return null;
    }

    private boolean esColor(ArrayList<Carta> cartas) {
        if (cartas.size() == 5) {
            String palo = cartas.get(0).getPalo();
            for (Carta carta : cartas) {
                if (!carta.getPalo().equals(palo)) {
                    return false;
                }
            }
            return true;
        };
        return false;
    }

    @Override
    protected ArrayList<Carta> calcularCartasRelevantes(ArrayList<Carta> cartas) {

        Map<String, List<Carta>> cartasPorPalo = cartas.stream()
                .collect(Collectors.groupingBy(Carta::getPalo));

        for (Map.Entry<String, List<Carta>> entrada : cartasPorPalo.entrySet()) {
            List<Carta> cartasDelMismoPalo = entrada.getValue();
            if (cartasDelMismoPalo.size() >= 5) {

                return cartasDelMismoPalo.stream()
                        .sorted(Comparator.comparingInt(Carta::getValor).reversed())
                        .limit(5)
                        .collect(Collectors.toCollection(ArrayList::new));
            }
        }

        return new ArrayList<>();
    }

}
