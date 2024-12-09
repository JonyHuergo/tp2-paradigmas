package org.example.Evaluadores;

import org.example.Carta;
import org.example.Manos.DoblePar;
import org.example.Manos.Mano;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EvaluadorDoblePar extends EvaluadorAbstracto {

    @Override
    public Mano evaluarMano(ArrayList<Carta> cartas) {
        Map<Integer, Long> conteo = cartas.stream()
                .collect(Collectors.groupingBy(Carta::getValor, Collectors.counting()));

        long pares = conteo.values().stream().filter(c -> c == 2).count();
        return (pares == 2) ? new DoblePar() : null;
    }

    @Override
    protected ArrayList<Carta> calcularCartasRelevantes(ArrayList<Carta> cartas) {
        Map<Integer, List<Carta>> grupos = cartas.stream()
                .collect(Collectors.groupingBy(Carta::getValor));

        List<Carta> pares = grupos.values().stream()
                .filter(grupo -> grupo.size() >= 2) // Grupos con al menos dos cartas
                .flatMap(grupo -> grupo.stream().limit(2)) // Tomar dos cartas de cada grupo
                .collect(Collectors.toList());

        if (pares.size() >= 4) {
            return pares.stream()
                    .sorted(Comparator.comparingInt(Carta::getValor).reversed()) // Ordenar por valor descendente
                    .limit(4) // Tomar las 4 mejores cartas
                    .collect(Collectors.toCollection(ArrayList::new));
        }

        return new ArrayList<>();
    }
}
