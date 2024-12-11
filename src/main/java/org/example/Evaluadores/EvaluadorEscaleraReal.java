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
        return cartas.stream().allMatch(c -> c.getPalo().equals(cartas.get(0).getPalo()));
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

    private boolean contieneAs(ArrayList<Carta> cartas) {
        return cartas.stream().anyMatch(c -> c.getValor() == 14);
    }

    @Override
    protected ArrayList<Carta> calcularCartasRelevantes(ArrayList<Carta> cartas) {
        Map<String, List<Carta>> cartasPorPalo = cartas.stream()
                .collect(Collectors.groupingBy(Carta::getPalo));

        Set<Integer> valoresEscaleraReal = new HashSet<>(Arrays.asList(10, 11, 12, 13, 14));


        for (List<Carta> cartasDelPalo : cartasPorPalo.values()) {

            Set<Integer> valoresDelPalo = cartasDelPalo.stream()
                    .map(Carta::getValor)
                    .collect(Collectors.toSet());

            if (valoresDelPalo.containsAll(valoresEscaleraReal)) {
                return cartasDelPalo.stream()
                        .filter(c -> valoresEscaleraReal.contains(c.getValor()))
                        .sorted(Comparator.comparingInt(Carta::getValor))
                        .collect(Collectors.toCollection(ArrayList::new));
            }
        }

        return new ArrayList<>();
    }

}