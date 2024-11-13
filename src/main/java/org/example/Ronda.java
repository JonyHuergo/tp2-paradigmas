package org.example;

public class Ronda {
    private Puntaje limiteDePuntos;
    private int turno;

    public Ronda(Puntaje limiteDePuntos) {
        this.limiteDePuntos = limiteDePuntos;
    }
    public void pasarTurno(Puntaje puntajeTotal){
        this.turno -= 1;
        if(limiteDePuntos <= puntajeTotal);
            System.out.println("siguiente ronda!");
        if(turno <= 0){
            System.out.println("perdiste!");
        }


    }
}