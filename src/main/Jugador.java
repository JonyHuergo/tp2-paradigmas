package main;

import java.util.ArrayList;

public class Jugador {
    private Mazo mazo;
    private String nombre;
    private ArrayList<Carta> mano = new ArrayList<>();

    public Jugador(){
        mazo = new Mazo();
    }

    public void repartirCartas(int cantidad) {

        mano = mazo.repartir(cantidad);
    }
}
