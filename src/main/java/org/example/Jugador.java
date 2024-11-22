package org.example;

import java.util.ArrayList;

public class Jugador {
    private Mazo mazo;
    private String nombre;
    private ArrayList<Carta> mano = new ArrayList<>();  // cambiar debido al problema con new
    private ArrayList<Carta> manoElegida = new ArrayList<>(); // cambiar debido al problema con new
    private ManoPoker manoPoker;
    private int puntaje;

    public Jugador(){
        mazo = new Mazo();
        puntaje = 0;
    }

    public Jugador(Mazo mazo){
        this.mazo = mazo;
        puntaje = 0;
    }

    public Jugador(Mazo mazo, ArrayList<Carta> mano, ArrayList<Carta> manoElegida){
        this.mazo = mazo;
        this.mano = mano;
        this.manoElegida = manoElegida;
        puntaje = 0;
    }

    public void repartirCartas(int cantidad) {

        mano = mazo.repartir(cantidad);
    }

    public int cantidadDeCartasEnMano(){
        return mano.size();
    }

    public void elegirCartas(ArrayList<Integer> posicionesCartas){ // Esto es raro
        for (int i = 0; i < posicionesCartas.size(); i++) {
            manoElegida.add(mano.get(posicionesCartas.get(i)));
        }
        manoPoker = new ManoPoker(manoElegida);
    }

    public void jugar(ManoPoker manoJugada){// nose si recibe la manoPoker por parametro despues vemos
        /*
        puntaje = puntaje.sumarCon(manoJugada.evaluar());
        multiplicador = manoJugada.getMultiplicadorBase();
        puntaje = puntaje.sumarCon(manoJugada.calcularConModificadores());
        multiplicador = 

        Ronda.pasarTurno();
        */

    }
}
