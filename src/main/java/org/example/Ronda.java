package org.example;

public class Ronda {
    private Puntaje limiteDePuntos;
    private Puntaje[] limitesDePuntoPorTurno;
    private int cantidadDeTurnos = 5;
    private int turno;

    public Ronda(Puntaje limiteDePuntos, Puntaje[] limitesDePuntosPorTurno) {
        this.limiteDePuntos = limiteDePuntos;
        this.limitesDePuntoPorTurno = limitesDePuntosPorTurno;
    }

    public boolean esGanada(Jugador jugador){



        ;
    }



    public void pasarTurno(Puntaje puntajeTotal){
        this.turno -= 1;
        if(limiteDePuntos.esMenorA(puntajeTotal));
            System.out.println("siguiente ronda!");
        if(turno <= 0){
            System.out.println("perdiste!");
        }


    }
}