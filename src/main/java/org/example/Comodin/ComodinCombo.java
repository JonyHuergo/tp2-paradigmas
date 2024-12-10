package org.example.Comodin;

import org.example.Jugada;
import org.example.Jugador;

import java.util.ArrayList;
import java.util.List;

public class ComodinCombo extends Comodin{
    private final ArrayList<Comodin> comodines;

    public ComodinCombo(String nombre, String descripcion, List<Comodin> comodines) {
//        super(0, 1, new ActivacionSiempre());
        super(nombre, descripcion, new ActivacionSiempre(), 0, 1);
        this.comodines = new ArrayList<>(comodines);
    }

    public ComodinCombo(ArrayList<Comodin> comodines) {
        super(0, 1, new ActivacionSiempre());
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
