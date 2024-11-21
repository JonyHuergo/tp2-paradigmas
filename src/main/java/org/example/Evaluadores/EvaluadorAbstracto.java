package org.example.Evaluadores;

import org.example.Carta;

import java.util.ArrayList;

public abstract class EvaluadorAbstracto implements EvaluadorMano {
    private EvaluadorMano siguiente;

    public void setSiguiente(EvaluadorMano siguiente) {
        this.siguiente = siguiente;
    }

    @Override
    public String evaluar(ArrayList<Carta> cartas) {
        String resultado = evaluarMano(cartas);
        if (resultado != null) {
            return resultado; // Si encuentra el tipo de mano, lo devuelve
        }
        if (siguiente != null) {
            return siguiente.evaluar(cartas); // Pasa al siguiente en la cadena
        }
        return "Carta Alta"; // Caso base si nadie encuentra un tipo de mano
    }

    protected abstract String evaluarMano(ArrayList<Carta> cartas);
}