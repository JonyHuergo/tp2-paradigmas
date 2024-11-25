package org.example.Comodin;

import org.example.Jugada;
import org.example.Jugador;

import java.util.ArrayList;

public class ComodinCombo extends Comodin{
    private final ArrayList<Comodin> comodines;

    public ComodinCombo(ArrayList<Comodin> comodines) {
        super(0, 0, 1, new ActivacionSiempre());
        this.comodines = new ArrayList<>(comodines);
    }

    @Override
    public void usar(Jugador jugador) {
        for (Comodin comodin : comodines) {
            comodin.usar(jugador);
        }
    }

    @Override
    public void usar(Jugada jugada) {
        for (Comodin comodin : comodines) {
            comodin.usar(jugada);
        }
    }
}
