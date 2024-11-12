package org.example;

import java.util.ArrayList;

public class ManoPoker {
    private ArrayList<Carta> cartas = new ArrayList<>();
    private PuntuacionPorMano puntuacionPorMano = new PuntuacionPorMano();

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
