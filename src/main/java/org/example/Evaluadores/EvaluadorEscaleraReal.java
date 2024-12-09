package org.example.Evaluadores;

import org.example.Carta;
import org.example.Manos.EscaleraReal;
import org.example.Manos.Mano;

import java.util.*;
import java.util.stream.Collectors;

public class EvaluadorEscaleraReal extends EvaluadorAbstracto {
    @Override
    protected Mano evaluarMano(ArrayList<Carta> cartas) {
        if (cartas.size() == 5 && esColor(cartas) && esEscalera(cartas) && contieneAs(cartas)) {
            return new EscaleraReal();
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

    @Override
    protected ArrayList<Carta> calcularCartasRelevantes(ArrayList<Carta> cartas) {
        // Agrupar las cartas por palo
        Map<String, List<Carta>> cartasPorPalo = cartas.stream()
                .collect(Collectors.groupingBy(Carta::getPalo));

        // Conjunto de valores necesarios para la Escalera Real
        Set<Integer> valoresEscaleraReal = new HashSet<>(Arrays.asList(10, 11, 12, 13, 14));


        // Verificar cada grupo de cartas por palo
        for (List<Carta> cartasDelPalo : cartasPorPalo.values()) {
            // Extraer los valores de las cartas del mismo palo
            Set<Integer> valoresDelPalo = cartasDelPalo.stream()
                    .map(Carta::getValor)
                    .collect(Collectors.toSet());

            // Verificar si contiene todos los valores de la Escalera Real
            if (valoresDelPalo.containsAll(valoresEscaleraReal)) {
                // Si cumple, filtrar las cartas necesarias y retornarlas
                return cartasDelPalo.stream()
                        .filter(c -> valoresEscaleraReal.contains(c.getValor()))
                        .sorted(Comparator.comparingInt(Carta::getValor))
                        .collect(Collectors.toCollection(ArrayList::new));
            }
        }

        // Si no se encuentra Escalera Real, devolver una lista vacía
        return new ArrayList<>();
    }

}