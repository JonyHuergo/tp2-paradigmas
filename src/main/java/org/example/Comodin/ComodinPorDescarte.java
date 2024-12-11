package org.example.Comodin;

import org.example.Jugada;
import org.example.Jugador;


public class ComodinPorDescarte extends Comodin{

    public ComodinPorDescarte(String nombre, String descripcion, String activacion, int puntos, float multiplicador) {
        super(nombre, descripcion, new ActivacionDescartes(), puntos, multiplicador);
    }
    public ComodinPorDescarte(int puntajeAdicional, float multiplicador, String activacion) {
        super(puntajeAdicional, multiplicador, new ActivacionDescartes());
    }

    @Override
    public void usar(Jugador jugador) {
        if (activacion.revisarCondicion(jugador)){
            if (this.multiplicador > 1){
                float puntosAMultiplicador = jugador.calcularPuntosPorDescarte(this.multiplicador);
                jugador.multiplicarMult(puntosAMultiplicador);
            }
            if (this.puntajeAdicional > 1){
                int puntosASumar = jugador.calcularPuntosPorDescarte(this.puntajeAdicional);
                puntajeAdicional += puntosASumar;
                jugador.actualizarPuntajeBase(puntosASumar);
            }
        }
    }

    @Override
    public void usar(Jugada jugada) {
        if (activacion.revisarCondicion(jugada)){
            if (this.multiplicador > 1){
                float puntosAMultiplicador = jugada.calcularPuntosPorDescarte(this.multiplicador);
                jugada.actualizarMult(puntosAMultiplicador);

            }
            if (this.puntajeAdicional > 1){
                int puntosASumar = jugada.calcularPuntosPorDescarte(this.puntajeAdicional);
                puntajeAdicional += puntosASumar;
                jugada.actualizarPuntajeBase(puntosASumar);
            }
        }
    }
}