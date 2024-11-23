package org.example.Comodin;

import org.example.Jugador;

public class ActivacionSiempre implements Activacion {


    @Override
    public boolean revisarCondicion(Jugador jugador) {
        return true;
    }
}
