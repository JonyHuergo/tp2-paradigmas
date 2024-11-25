package org.example.Comodin;

import org.example.Jugada;
import org.example.Jugador;

public class ComodinPorDescarte extends Comodin{
    private int puntosPorDescarte = 10;

    public ComodinPorDescarte(int puntajeAdicional, int multiplicadorAdicional, int multiplicadorAumento, Activacion activacion) {
        super(puntajeAdicional, multiplicadorAdicional, multiplicadorAumento, activacion);
    }

    @Override
    public void usar(Jugador jugador) {
        if (activacion.revisarCondicion(jugador)){
            int puntosASumar = jugador.calcularPuntosPorDescarte(puntosPorDescarte);
            puntajeAdicional += puntosASumar;

            jugador.actualizarPuntajeBase(puntajeAdicional);
            jugador.actualizarMult(multiplicadorAdicional);
            jugador.multiplicarMult(multiplicadorAumento);
        }
    }

    @Override
    public void usar(Jugada jugada) {
        if (activacion.revisarCondicion(jugada)){
            int puntosASumar = jugada.calcularPuntosPorDescarte(puntosPorDescarte);
            puntajeAdicional += puntosASumar;

            jugada.actualizarPuntajeBase(puntajeAdicional);
            jugada.actualizarMult(multiplicadorAdicional);
            jugada.multiplicarMult(multiplicadorAumento);
        }
    }
}