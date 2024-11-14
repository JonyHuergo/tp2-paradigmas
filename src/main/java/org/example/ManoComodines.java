package org.example;

import java.util.ArrayList;

public class ManoComodines {
    private ArrayList<Comodin> comodines = new ArrayList<Comodin>();

    public ManoComodines(ArrayList<Comodin> comodines) {
        this.comodines = comodines;
    }

    public Puntaje aplicarComodines(ManoPoker manoPoker){
        Puntaje puntajeBase = manoPoker.evaluar();
        for (Comodin comodin : comodines) {
            puntajeBase = comodin.aplicarModificacionPuntaje(puntajeBase);
            puntajeBase = comodin.aplicarModificacionMultiplicador(puntajeBase);
        }
        return puntajeBase;
    }
}
