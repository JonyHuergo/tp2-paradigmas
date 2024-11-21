package org.example.Evaluadores;

import org.example.Carta;
import org.example.Manos.Mano;
import org.example.Manos.Par;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

public class EvaluadorPar extends EvaluadorAbstracto {
    @Override
    protected Mano evaluarMano(ArrayList<Carta> cartas) {
        Map<Integer, Long> frecuencia = cartas.stream()
                .collect(Collectors.groupingBy(Carta::getValor, Collectors.counting()));
        if (frecuencia.containsValue(2L)) {
            return new Par();
        }
        return null;
    }
}