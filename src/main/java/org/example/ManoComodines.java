package org.example;

import java.util.ArrayList;

public class ManoComodines {
    private ArrayList<Comodin> comodines = new ArrayList<>();

    public ManoComodines() {

    }

    public ManoComodines(ArrayList<Comodin> comodines) {
        this.comodines = comodines;
    }

    public void aplicarComodines(ManoPoker manoPoker){
        for (Comodin comodin : comodines) {
            comodin.aplicarEfecto(manoPoker);
        }
    }
}
