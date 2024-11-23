package org.example.Comodin;

import org.example.Jugador;

public class ActivacionTipoDeMano implements Activacion {
    private String manoEsperada;

    public ActivacionTipoDeMano(String manoEsperada) {
        this.manoEsperada = manoEsperada;
    }

    @Override
    public boolean revisarCondicion(Jugador jugador) {
        return(jugador.tieneManoDeTipo(manoEsperada));
    }
}