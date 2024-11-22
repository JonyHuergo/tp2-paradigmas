package org.example;

import java.util.ArrayList;
import java.util.Collections;

public class Mazo {
    private ArrayList<Carta> cartas = new ArrayList<>();

    public Mazo() {
        cartas = new ArrayList<>();
        inicializarMazo();
    }

    /* public ArrayList<Carta> getCartas(){
        return cartas;
    } */

    // Se va a necesitar en un futuro (el juego siempre muestra cuantas cartas tiene el mazo ej: 44/52)
    public int cantidadDeCartas(){
        return cartas.size();
    }

    private void inicializarMazo() {
        String[] palos = {"corazones", "diamantes", "tréboles", "picas"};
        for (String palo : palos) {
            for (int i = 1; i <= 13; i++) {
                cartas.add(new Carta(palo, i));
            }
        }
        Collections.shuffle(cartas);
    }

    public ArrayList<Carta> repartir(int cantidad) {
        ArrayList<Carta> mano = new ArrayList<>();
        for (int i = 0; i < cantidad && !cartas.isEmpty(); i++) {
            mano.add(cartas.remove(0));
        }
        return mano;
    }
}
