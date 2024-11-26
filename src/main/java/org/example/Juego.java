package org.example;

import java.util.List;

public class Juego {
    private Jugador jugador;
    private List<Ronda> ronda;
    private int cantidadRondas;
    private boolean juegoGanado = false;

    public void jugar(){
        /*
        while(ronda.puntajeSuperoElLimite(jugador.jugar()) && cantidadRondas > 0){



            cantidadRondas--;
        }

        if (cantidadRondas == 0) {
            juegoGanado = true;
        }
        juegoGanado = false;
        */
    }
    public void PartidaPerdida(){

    }

    public void hacerJugada(){
        jugador.crearJugada();
    }
}
