package org.example.Comodin;

import org.example.Jugada;
import org.example.Jugador;

import java.util.List;

public class ComodinPorDescarte extends Comodin{
//    private int puntosPorDescarte = 10;

    public ComodinPorDescarte(String nombre, String descripcion, String activacion, int puntos, float multiplicador) {
        super(nombre, descripcion, new ActivacionDescartes(), puntos, multiplicador);
    }
    public ComodinPorDescarte(int puntajeAdicional, float multiplicador, String activacion) {
        super(puntajeAdicional, multiplicador, new ActivacionDescartes());
    }

    @Override
    public void usar(Jugador jugador) {
//        if (activacion.revisarCondicion(jugador)){
//            int puntosASumar = jugador.calcularPuntosPorDescarte(puntosPorDescarte);
//            puntajeAdicional += puntosASumar;
//
//            jugador.actualizarPuntajeBase(puntajeAdicional);
//            jugador.multiplicarMult(multiplicador);
//        }
    }

    @Override
    public void usar(Jugada jugada) {
        if (activacion.revisarCondicion(jugada)){
            if (puntajeAdicional > 1) {
                puntajeAdicional = jugada.calcularPuntosPorDescarte(puntajeAdicional);
                jugada.actualizarPuntajeBase(puntajeAdicional);
            }

            float multplicadorASumar;
            multplicadorASumar = ((float) jugada.calcularMultPorDescarte((int) multiplicador));
            multiplicador = multplicadorASumar;
            jugada.multiplicarMult(multiplicador);
        }
    }
}