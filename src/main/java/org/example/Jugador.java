package org.example;

import java.util.ArrayList;

public class Jugador {
    private Mazo mazo;
    private String nombre;
    private ArrayList<Carta> mano = new ArrayList<>();
    private ArrayList<Carta> manoElegida = new ArrayList<>();
    private ManoPoker manoPoker;
    private Puntaje puntaje;
    private Puntaje multiplicador;

    public Jugador(){
        mazo = new Mazo();
        puntaje = new Puntaje(0);
    }

    public void repartirCartas(int cantidad) {

        mano = mazo.repartir(cantidad);
    }

    public ArrayList<Carta> getCartasEnMano() { // Se va a necesitar en un futuro
        return mano;
    }

    public void elegirCartas(ArrayList<Integer> posicionesCartas){
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
