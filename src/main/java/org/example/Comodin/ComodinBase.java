package org.example.Comodin;

import org.example.Jugada;
import org.example.Jugador;

public class ComodinBase extends Comodin {
    public ComodinBase(int puntajeAdicional, int multiplicadorAdicional, int multiplicadorAumento, Activacion activacion) {
        super(puntajeAdicional, multiplicadorAdicional, multiplicadorAumento, activacion);
    }

    @Override
    public void usar(Jugador jugador){
        if (activacion.revisarCondicion(jugador)){
            jugador.actualizarPuntajeBase(puntajeAdicional);
            jugador.actualizarMult(multiplicadorAdicional);
            jugador.multiplicarMult(multiplicadorAumento);
        }
    }

    @Override
    public void usar(Jugada jugada){
        if (activacion.revisarCondicion(jugada)){
            jugada.actualizarPuntajeBase(puntajeAdicional);
            jugada.actualizarMult(multiplicadorAdicional);
            jugada.multiplicarMult(multiplicadorAumento);
        }
    }
}
