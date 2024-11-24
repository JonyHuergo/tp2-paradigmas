package org.example.Comodin;

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
}