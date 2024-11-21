package org.example;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Ronda {
    private int numero;
    private int manos;
    private int descartes;
    private int limiteDePuntos;//cambiar a puntaje?
    private Tienda tienda;

    @JsonCreator
    public Ronda(@JsonProperty("nro") int numero, @JsonProperty("manos") int manos, @JsonProperty("descartes") int descartes, @JsonProperty("puntajeASuperar") int limiteDePuntos, @JsonProperty("tienda") Tienda tienda) {
        this.numero = numero;
        this.manos = manos;
        this.descartes = descartes;
        this.limiteDePuntos = limiteDePuntos;
        this.tienda = tienda;
    }
    public Ronda(int limiteDePuntos) {
        this.limiteDePuntos = limiteDePuntos;
    }

    public boolean esGanada(Jugador jugador){



        return true;
    }


/*
    public void pasarTurno(Puntaje puntajeTotal){
        this.turno -= 1;
        if(limiteDePuntos.esMenorA(puntajeTotal));
            System.out.println("siguiente ronda!");
        if(turno <= 0){
            System.out.println("perdiste!");
        }*/

}