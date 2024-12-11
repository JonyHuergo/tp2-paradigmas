package org.example.Comodin;

import org.example.Jugada;
import org.example.Jugador;

public class ComodinPorProbabilidad extends Comodin {
    ComodinPorProbabilidad(String nombre, String descripcion, String activacion, int puntajeAdicional, float multiplicador) {
        super(nombre, descripcion, new ActivacionProbabilidad(activacion), puntajeAdicional, multiplicador);
    }

    @Override
    public void usar(Jugador jugador){
        if (activacion.revisarCondicion(jugador)){
            jugador.actualizarPuntajeBase(puntajeAdicional);
            jugador.multiplicarMult(multiplicador);

        }
    }

    @Override
    public void usar(Jugada jugada){
        if (activacion.revisarCondicion(jugada)){
            jugada.actualizarPuntajeBase(puntajeAdicional);
            jugada.multiplicarMult(multiplicador);
        }
    }

}
