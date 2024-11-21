package org.example.Evaluadores;

import org.example.Carta;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

public class EvaluadorDoblePar extends EvaluadorAbstracto {

    @Override
    public String evaluarMano(ArrayList<Carta> cartas) {
        Map<Integer, Long> conteo = cartas.stream()
                .collect(Collectors.groupingBy(Carta::getValor, Collectors.counting()));

        long pares = conteo.values().stream().filter(c -> c == 2).count();
        return (pares == 2) ? "Doble Par" : null;
    }
}
