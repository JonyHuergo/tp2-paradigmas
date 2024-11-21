package org.example.Evaluadores;

import org.example.Carta;
import org.example.Manos.DoblePar;
import org.example.Manos.Mano;

import java.util.ArrayList;
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
}
