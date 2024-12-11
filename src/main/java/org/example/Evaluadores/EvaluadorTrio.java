package org.example.Evaluadores;

import org.example.Carta;
import org.example.Manos.Mano;
import org.example.Manos.Trio;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EvaluadorTrio extends EvaluadorAbstracto {

    @Override
    public Mano evaluarMano(ArrayList<Carta> cartas) {
        if (tieneRepetidos(cartas, 3)) {
            return new Trio();
        }
        return null;
    }

    private boolean tieneRepetidos(ArrayList<Carta> cartas, int cantidad) {
        Map<Integer, Long> conteo = cartas.stream()
                .collect(Collectors.groupingBy(Carta::getValor, Collectors.counting()));
        return conteo.values().contains((long) cantidad);
    }

    @Override
    protected ArrayList<Carta> calcularCartasRelevantes(ArrayList<Carta> cartas) {
        Map<Integer, List<Carta>> grupos = cartas.stream()
                .collect(Collectors.groupingBy(Carta::getValor));

        List<Carta> trio = grupos.values().stream()
                .filter(grupo -> grupo.size() >= 3)
                .flatMap(grupo -> grupo.stream().limit(3))
                .collect(Collectors.toList());

        List<Carta> par = grupos.values().stream()
                .filter(grupo -> grupo.size() >= 2 && !trio.containsAll(grupo))
                .flatMap(grupo -> grupo.stream().limit(2))
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