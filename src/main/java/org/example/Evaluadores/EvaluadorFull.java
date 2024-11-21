package org.example.Evaluadores;

import org.example.Carta;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

public class EvaluadorFull extends EvaluadorAbstracto {

    @Override
    public String evaluarMano(ArrayList<Carta> cartas) {
        Map<Integer, Long> conteo = cartas.stream()
                .collect(Collectors.groupingBy(Carta::getValor, Collectors.counting()));
        return (conteo.values().contains(3L) && conteo.values().contains(2L))
                ? "Full"
                : null;
    }
}