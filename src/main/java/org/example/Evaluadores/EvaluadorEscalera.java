package org.example.Evaluadores;

import org.example.Carta;
import org.example.Manos.Escalera;
import org.example.Manos.Mano;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EvaluadorEscalera extends EvaluadorAbstracto {
    @Override
    protected Mano evaluarMano(ArrayList<Carta> cartas) {
        if (cartas.size() == 5) {
            cartas.sort(Comparator.comparingInt(Carta::getValor));
            Carta cartaAnterior = null;
            for (Carta carta : cartas) {
                if (cartaAnterior != null && carta.getValor() != cartaAnterior.getValor() + 1) {
                    return null;  // Retorna null si las cartas no son consecutivas
                }
                cartaAnterior = carta;  // Actualiza la carta anterior para la siguiente iteración
            }
            return new Escalera();
        } else {
            return null;
        }
    }
    @Override
    protected ArrayList<Carta> calcularCartasRelevantes(ArrayList<Carta> cartas) {
        // Ordenar las cartas por valor, eliminando duplicados
        List<Integer> valoresOrdenados = cartas.stream()
                .map(Carta::getValor)
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        // Verificar si hay una secuencia consecutiva de 5 valores
        for (int i = 0; i <= valoresOrdenados.size() - 5; i++) {
            // Extraer un sublista de 5 valores consecutivos
            List<Integer> posibleEscalera = valoresOrdenados.subList(i, i + 5);

            // Verificar si los valores son consecutivos
            if (esConsecutiva(posibleEscalera)) {
                // Filtrar las cartas que corresponden a los valores de la escalera
                return cartas.stream()
                        .filter(c -> posibleEscalera.contains(c.getValor()))
                        .sorted(Comparator.comparingInt(Carta::getValor))
                        .collect(Collectors.toCollection(ArrayList::new));
            }
        }

        // Si no hay escalera, devolver una lista vacía
        return new ArrayList<>();
    }

    // Método auxiliar para verificar si los valores son consecutivos
    private boolean esConsecutiva(List<Integer> valores) {
        for (int i = 0; i < valores.size() - 1; i++) {
            if (valores.get(i) + 1 != valores.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

}