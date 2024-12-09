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
        // Agrupar cartas por palo
        Map<String, List<Carta>> cartasPorPalo = cartas.stream()
                .collect(Collectors.groupingBy(Carta::getPalo));

        // Buscar el grupo con al menos 5 cartas del mismo palo
        for (Map.Entry<String, List<Carta>> entrada : cartasPorPalo.entrySet()) {
            List<Carta> cartasDelMismoPalo = entrada.getValue();
            if (cartasDelMismoPalo.size() >= 5) {
                // Retornar las primeras 5 cartas del mismo palo ordenadas por valor
                return cartasDelMismoPalo.stream()
                        .sorted(Comparator.comparingInt(Carta::getValor).reversed()) // Ordenar por valor descendente
                        .limit(5) // Tomar las primeras 5 cartas
                        .collect(Collectors.toCollection(ArrayList::new));
            }
        }

        // Si no hay Color, devolver lista vacía
        return new ArrayList<>();
    }

}
