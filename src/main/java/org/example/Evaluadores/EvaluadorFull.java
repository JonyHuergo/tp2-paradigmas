package org.example.Evaluadores;

import org.example.Carta;
import org.example.Manos.Full;
import org.example.Manos.Mano;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

public class EvaluadorFull extends EvaluadorAbstracto {

    @Override
    public Mano evaluarMano(ArrayList<Carta> cartas) {
        Map<Integer, Long> conteo = cartas.stream()
                .collect(Collectors.groupingBy(Carta::getValor, Collectors.counting()));
        return (conteo.values().contains(3L) && conteo.values().contains(2L))
                ? new Full()
                : null;
    }
}