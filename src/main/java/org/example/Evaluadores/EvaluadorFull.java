package org.example.Evaluadores;

import org.example.Carta;
import org.example.Manos.Full;
import org.example.Manos.Mano;

import java.util.ArrayList;
import java.util.List;
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

    @Override
    protected ArrayList<Carta> calcularCartasRelevantes(ArrayList<Carta> cartas) {
        Map<Integer, List<Carta>> grupos = cartas.stream()
                .collect(Collectors.groupingBy(Carta::getValor));

        List<Carta> trio = grupos.values().stream()
                .filter(grupo -> grupo.size() >= 3) // Buscar el trío
                .flatMap(grupo -> grupo.stream().limit(3)) // Tomar tres cartas
                .collect(Collectors.toList());

        List<Carta> par = grupos.values().stream()
                .filter(grupo -> grupo.size() >= 2 && !trio.containsAll(grupo)) // Buscar el par distinto al trío
                .flatMap(grupo -> grupo.stream().limit(2)) // Tomar dos cartas
                .collect(Collectors.toList());

        if (trio.size() == 3 && par.size() == 2) {
            ArrayList<Carta> resultado = new ArrayList<>();
            resultado.addAll(trio);
            resultado.addAll(par);
            return resultado;
        }

        return new ArrayList<>();
    }
}