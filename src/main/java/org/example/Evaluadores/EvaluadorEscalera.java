package org.example.Evaluadores;

import org.example.Carta;
import org.example.Manos.Escalera;
import org.example.Manos.Mano;

import java.util.ArrayList;
import java.util.Comparator;

public class EvaluadorEscalera extends EvaluadorAbstracto {
    @Override
    protected Mano evaluarMano(ArrayList<Carta> cartas) {
        cartas.sort(Comparator.comparingInt(Carta::getValor));
        Carta cartaAnterior = null;
        for (Carta carta : cartas) {
            if (cartaAnterior != null && carta.getValor() != cartaAnterior.getValor() + 1) {
                return null;  // Retorna null si las cartas no son consecutivas
            }
            cartaAnterior = carta;  // Actualiza la carta anterior para la siguiente iteración
        }
        return new Escalera();
    }
}