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
            if (this.puntajeAdicional > 1) {
            jugador.actualizarPuntajeBase(puntajeAdicional);}
            if (this.multiplicador > 1){
            jugador.actualizarMult(multiplicador);}
        }
    }

    @Override
    public void usar(Jugada jugada){
        if (activacion.revisarCondicion(jugada)){
            if (this.puntajeAdicional > 1) {
                jugada.actualizarPuntajeBase(puntajeAdicional);
            }if (this.multiplicador > 1){
            jugada.actualizarMult(multiplicador);}
        }
    }
}
