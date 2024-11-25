package org.example;

public class Ronda {
    private int limiteDePuntos;
    private int turno;

    public Ronda(int limiteDePuntos) {
        this.limiteDePuntos = limiteDePuntos;
    }
    public void pasarTurno(int puntajeTotal){
        this.turno -= 1;
        if(limiteDePuntos <= puntajeTotal);
            System.out.println("siguiente ronda!");
        if(turno <= 0){
            System.out.println("perdiste!");
        }


    }
}