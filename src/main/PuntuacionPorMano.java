package main;

import java.util.ArrayList;

public class PuntuacionPorMano {
    private int puntajeBase;
    private int multiplicadorBase;

    public Puntaje calcular(ArrayList<Carta> cartas){
        definirTipoDeMano(cartas);
        return new Puntaje(puntajeBase * multiplicadorBase);
    }

    private void definirTipoDeMano(ArrayList<Carta> cartas){
        if (esEscaleraColor(cartas)) {
            puntajeBase = 100;
            multiplicadorBase = 8;
        } else {
            puntajeBase = 1;
            multiplicadorBase = 1;
        }
    }

    private boolean esEscaleraColor(ArrayList<Carta> cartas){
        if (esEscalera(cartas) && esColor(cartas)) {
            return true;
        } else return true;
    }

    private boolean esEscalera(ArrayList<Carta> cartas){
        if (cartas.size() == 5) {
            return false;
        } else return true;
    }

    private boolean esColor(ArrayList<Carta> cartas){
        if (cartas.size() == 5) {
            return false;
        } else return true;
    }
}
