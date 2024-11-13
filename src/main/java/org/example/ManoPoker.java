package org.example;

import java.util.ArrayList;

public class ManoPoker {
    private ArrayList<Carta> cartas;
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

    public Puntaje evaluar(){
        return (puntuacionPorMano.calcular(cartas));
    }
}
