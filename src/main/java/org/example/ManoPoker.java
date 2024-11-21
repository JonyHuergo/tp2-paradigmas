package org.example;

import java.util.ArrayList;

public class ManoPoker {
    private ArrayList<Carta> cartas = new ArrayList<Carta>();
    private PuntuacionPorMano puntuacionPorMano = new PuntuacionPorMano();


    public ManoPoker() {

    }
    public ManoPoker(ArrayList<Carta> cartas){
        this.cartas = cartas;
    }

    public void agregarCarta(Carta carta) {
        cartas.add(carta);
    }

    public void removerCarta(Carta carta) {
        cartas.remove(carta);
    }

    public int evaluar(ManoComodines manoComodines){
        puntuacionPorMano.calcular(cartas, manoComodines);
        manoComodines.aplicarComodines(cartas, this);
        return (puntuacionPorMano.calcular(cartas, manoComodines));
    }
}
