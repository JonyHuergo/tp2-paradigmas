package org.example;

import java.util.ArrayList;

public class Juego {
    private Jugador jugador;
    private ArrayList<Ronda> rondas = new ArrayList<Ronda>();
    private int numeroRonda = 0;
    private int cantidadRondas = 8;
    private boolean juegoGanado = false;

    public Juego(Puntaje[] limitesDePuntosPorRonda, Puntaje[][] limitesDePuntosPorTurno) {
        for(int i = 0; i < cantidadRondas; i++) {
            rondas.add(new Ronda(limitesDePuntosPorRonda[i], limitesDePuntosPorTurno[i]));
        }
    }

    public void jugar(){

        while(rondas.get(numeroRonda).esGanada(jugador) && numeroRonda <= cantidadRondas){
            numeroRonda++;
        }

        if (numeroRonda == 8) {
            juegoGanado = true;
        }
        juegoGanado = false;
    }

    public void PartidaPerdida(){

    }
}
