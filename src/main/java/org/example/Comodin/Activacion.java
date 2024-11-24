package org.example.Comodin;

import org.example.Jugada;
import org.example.Jugador;

public interface Activacion {

    public boolean revisarCondicion(Jugador jugador);

    public boolean revisarCondicion(Jugada jugada);
}
