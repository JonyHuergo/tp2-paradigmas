package org.example.Comodin;

import org.example.Jugada;
import org.example.Jugador;

public class ComodinBase extends Comodin {


    public ComodinBase(String nombre, String descripcion, String activacion, int puntajeAdicional, float multiplicador){
        super(nombre, descripcion, new ActivacionSiempre(), puntajeAdicional, multiplicador);
    }
    public ComodinBase(int puntajeAdicional, float multiplicador, String activacion) {
        super(puntajeAdicional, multiplicador, new ActivacionSiempre());
    }
    public ComodinBase(int puntajeAdicional, float multiplicador, Activacion activacion) {
        super(puntajeAdicional, multiplicador, activacion);
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
