package org.example.Comodin;

import org.example.Jugada;
import org.example.Jugador;

public class ComodinPorManoJugada extends Comodin {
    public ComodinPorManoJugada(String nombre, String descripcion, String activacion, int puntajeAdicional, float multiplicador) {
        super(puntajeAdicional, multiplicador, new ActivacionTipoDeMano(activacion));
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    public ComodinPorManoJugada(String nombre, String descripcion, Activacion activacion, int puntajeAdicional, float multiplicador) {
        super(nombre, descripcion, activacion, puntajeAdicional, multiplicador);
    }
    @Override
    public void usar(Jugador jugador){
        if (activacion.revisarCondicion(jugador)){
            jugador.actualizarPuntajeBase(puntajeAdicional);
            jugador.actualizarMult(multiplicador);

        }
    }

    @Override
    public void usar(Jugada jugada){
        if (activacion.revisarCondicion(jugada)){
            jugada.actualizarPuntajeBase(puntajeAdicional);
            jugada.actualizarMult(multiplicador);
        }
    }
}
