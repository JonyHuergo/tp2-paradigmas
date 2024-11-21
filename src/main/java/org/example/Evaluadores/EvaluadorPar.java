package org.example.Evaluadores;

import org.example.Carta;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

public class EvaluadorPar extends EvaluadorAbstracto {
    @Override
    protected String evaluarMano(ArrayList<Carta> cartas) {
        Map<Integer, Long> frecuencia = cartas.stream()
                .collect(Collectors.groupingBy(Carta::getValor, Collectors.counting()));
        if (frecuencia.containsValue(2L)) {
            return "Par";
        }
        return null;
    }
}