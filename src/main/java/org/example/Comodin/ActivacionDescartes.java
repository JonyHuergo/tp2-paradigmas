package org.example.Comodin;

import org.example.Jugador;

public class ActivacionDescartes implements Activacion{

    @Override
    public boolean revisarCondicion(Jugador jugador) {
      return(jugador.tieneDescartes());
    }

}
