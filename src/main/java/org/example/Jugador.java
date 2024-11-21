package org.example;

import java.util.ArrayList;

public class Jugador {
    private Mazo mazo;
    private String nombre;
    private ArrayList<Carta> mano = new ArrayList<>();
    private ArrayList<Carta> manoElegida = new ArrayList<>();
    private ManoPoker manoPoker;
    private int puntaje;
    private int multiplicador;

    public Jugador(){
        mazo = new Mazo();
        puntaje = 0;
    }

    public int repartirCartas(int cantidad) {
        mano = mazo.repartir(cantidad);
        return mano.size();
    }

//    public ArrayList<Carta> getCartasEnMano() {
//        return mano;
//    }


    public void elegirCartas(ArrayList<Integer> posicionesCartas){
        for (int i = 0; i < posicionesCartas.size(); i++) {
            manoElegida.add(mano.get(posicionesCartas.get(i)));
        }
        manoPoker = new ManoPoker(manoElegida);
    }

    public void jugar(ManoPoker manoJugada){// nose si recibe la manoPoker por parametro despues vemos

//        puntaje = puntaje.sumarCon(manoJugada.evaluar());
//        multiplicador = manoJugada.getMultiplicadorBase();
//        puntaje = puntaje.sumarCon(manoJugada.calcularConModificadores());
//        multiplicador =
//
//        Ronda.pasarTurno();


    }
}
