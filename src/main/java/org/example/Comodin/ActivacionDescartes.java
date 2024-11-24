package org.example.Comodin;

import org.example.Jugada;
import org.example.Jugador;

public class ActivacionDescartes implements Activacion{

    @Override
    public boolean revisarCondicion(Jugador jugador) {
      return(jugador.tieneDescartes());
    }

    @Override
    public boolean revisarCondicion(Jugada jugada){
        return(jugada.tieneDescartes());
    }

}