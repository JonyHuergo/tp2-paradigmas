package org.example.Evaluadores;

import org.example.Carta;
import org.example.Manos.EscaleraColor;
import org.example.Manos.Mano;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EvaluadorEscaleraColor extends EvaluadorAbstracto {

    @Override
    public Mano evaluarMano(ArrayList<Carta> cartas) {
        if (cartas.size() == 5 && esEscalera(cartas) && esColor(cartas)) {
            return new EscaleraColor();
        }
        return null;
    }

    private boolean esEscalera(ArrayList<Carta> cartas) {
        cartas.sort(Comparator.comparingInt(Carta::getValor));
        for (int i = 0; i < cartas.size() - 1; i++) {
            if (cartas.get(i).getValor() + 1 != cartas.get(i + 1).getValor()) {
                return false;
            }
        }
        return true;
    }

    private boolean esColor(ArrayList<Carta> cartas) {
        String palo = cartas.get(0).getPalo();
        for (Carta carta : cartas) {
            if (!carta.getPalo().equals(palo)) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected ArrayList<Carta> calcularCartasRelevantes(ArrayList<Carta> cartas) {
        // Agrupar las cartas por palo
        Map<String, List<Carta>> cartasPorPalo = cartas.stream()
                .collect(Collectors.groupingBy(Carta::getPalo));

        // Iterar sobre cada grupo de palo y verificar si existe una escalera
        for (List<Carta> cartasDelPalo : cartasPorPalo.values()) {
            if (cartasDelPalo.size() >= 5) {
                // Ordenar las cartas por valor dentro del mismo palo
                List<Carta> cartasOrdenadas = cartasDelPalo.stream()
                        .sorted(Comparator.comparingInt(Carta::getValor))
                        .collect(Collectors.toList());

                // Buscar una secuencia de 5 cartas consecutivas
                ArrayList<Carta> escaleraColor = new ArrayList<>();
                escaleraColor.add(cartasOrdenadas.get(0));

                for (int i = 1; i < cartasOrdenadas.size(); i++) {
                    Carta actual = cartasOrdenadas.get(i);
                    Carta anterior = escaleraColor.get(escaleraColor.size() - 1);

                    if (actual.getValor() == anterior.getValor() + 1) {
                        escaleraColor.add(actual);
                        if (escaleraColor.size() == 5) {
                            return escaleraColor;
                        }
                    } else if (actual.getValor() != anterior.getValor()) {
                        // Reiniciar si no es consecutiva
                        escaleraColor.clear();
                        escaleraColor.add(actual);
                    }
                }
            }
        }

        // Si no se encuentra escalera de color, devolver una lista vacía
        return new ArrayList<>();
    }

}
