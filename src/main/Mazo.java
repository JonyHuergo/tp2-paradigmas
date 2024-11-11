package main;

import java.util.ArrayList;
import java.util.Collections;

public class Mazo {
    private ArrayList<Carta> cartas = new ArrayList<>();

    public Mazo() {
        cartas = new ArrayList<>();
        inicializarMazo();
    }

    public ArrayList<Carta> getCartas(){
        return cartas;
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
}
