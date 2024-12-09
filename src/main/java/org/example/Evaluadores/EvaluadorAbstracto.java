package org.example.Evaluadores;

import org.example.Carta;
import org.example.Manos.CartaAlta;
import org.example.Manos.Mano;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public abstract class EvaluadorAbstracto implements EvaluadorMano {
    private EvaluadorMano siguiente;

    public void setSiguiente(EvaluadorMano siguiente) {
        this.siguiente = siguiente;
    }

    @Override
    public Mano evaluar(ArrayList<Carta> cartas) {
        Mano resultado = evaluarMano(cartas);
        if (cartas.size() == 1) {
            return new CartaAlta();
        }
        if (resultado != null) {
            return resultado; // Si encuentra el tipo de mano, lo devuelve
        }
        if (siguiente != null) {
            return siguiente.evaluar(cartas); // Pasa al siguiente en la cadena
        }
        return new CartaAlta(); // Caso base si nadie encuentra un tipo de mano
    }

    @Override
    public ArrayList<Carta> cartasDeLaMano(ArrayList<Carta> cartas) {
        ArrayList<Carta> cartasMano = calcularCartasRelevantes(cartas);
        if (cartasMano != null && !cartasMano.isEmpty()) {
            return cartasMano;
        }
        if (siguiente != null) {
            return siguiente.cartasDeLaMano(cartas);
        }
        return cartas.stream()
                .max(Comparator.comparing(Carta::getValor))
                .map(carta -> new ArrayList<>(Collections.singletonList(carta)))
                .orElse(new ArrayList<>());
    }


    protected abstract ArrayList<Carta> calcularCartasRelevantes(ArrayList<Carta> cartas);

    protected abstract Mano evaluarMano(ArrayList<Carta> cartas);
}