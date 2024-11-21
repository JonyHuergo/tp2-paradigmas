package org.example.Evaluadores;

import org.example.Carta;
import org.example.Manos.Mano;
import org.example.Manos.Poker;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

public class EvaluadorPoker extends EvaluadorAbstracto{

    @Override
    public Mano evaluarMano(ArrayList<Carta> cartas) {
        if (tieneRepetidos(cartas, 4)) {
            return new Poker();
        }
        return null;
    }
    private boolean tieneRepetidos(ArrayList<Carta> cartas, int cantidad) {
        Map<Integer, Long> conteo = cartas.stream()
                .collect(Collectors.groupingBy(Carta::getValor, Collectors.counting()));
            return conteo.values().contains((long) cantidad);
        }


}