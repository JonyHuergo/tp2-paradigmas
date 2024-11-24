package org.example;

import org.example.Comodin.Comodin;

import java.util.ArrayList;

public class Jugada { // esta clase deberia remplazar al acumulador de puntos en jugador, se guardan las jugadas y cada una hace el calculo
    private ManoPoker manoPoker;
    private ArrayList<Comodin> comodines;
    private int puntaje;
    private int descartes;
    private int numeroRonda;

    public Jugada(ManoPoker manoPoker, ArrayList<Comodin> comodines, int descartes){
        this.manoPoker = manoPoker;
        this.comodines = comodines;
        this.descartes = descartes;
    }
}
