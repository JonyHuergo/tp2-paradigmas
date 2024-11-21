package org.example;

import java.util.ArrayList;

public class ManoPoker {
    private ArrayList<Carta> cartas = new ArrayList<Carta>();
    private PuntuacionPorMano puntuacionPorMano = new PuntuacionPorMano();
    private AnalizadorMano analizadorMano;

    public ManoPoker() {
        this.analizadorMano = new AnalizadorMano();

    }
    public ManoPoker(ArrayList<Carta> cartas){
        this.cartas = cartas;
        this.analizadorMano = new AnalizadorMano();

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

    public String obtenerTipoDeMano() {
        return analizadorMano.analizarMano(cartas);
    }
}
