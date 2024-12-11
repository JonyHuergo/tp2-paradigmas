package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Mazo {
    private ArrayList<Carta> cartas = new ArrayList<>();

    public Mazo() {
        cartas = new ArrayList<>();
        inicializarMazo();
    }

    public Mazo(ArrayList<Carta> cartas) {
        this.cartas = cartas;
    }

    public int cantidadDeCartas(){
        return cartas.size();
    }

    private void inicializarMazo() {
        String[] palos = {"corazones", "diamantes", "tréboles", "picas"};
        for (String palo : palos) {
            for (int i = 2; i <= 14; i++) {
                cartas.add(new Carta(palo, i));
            }
        }
        Collections.shuffle(cartas);
    }

    public ArrayList<Carta> repartirCartas(int cantidad) {
        ArrayList<Carta> cartasRepartidas = new ArrayList<>();


        if (cantidad > cartas.size()) {
            System.out.println("No hay suficientes cartas en el mazo.");
            return cartas;
        }

        Collections.shuffle(cartas);

        for (int i = 0; i < cantidad; i++) {
            cartasRepartidas.add(cartas.remove(0));
        }

        return cartasRepartidas;
    }

    public void repartir(ArrayList<Carta> cartasDisponibles, int limiteCartas) {
        int cartasARepartir = limiteCartas - cartasDisponibles.size();

        Random random = new Random();

        for (int i = 0; i < cartasARepartir && !cartas.isEmpty(); i++) {
            int pos = random.nextInt(cartas.size());
            cartasDisponibles.add(cartas.remove(pos));
        }
    }

    public void agregarCarta(Carta carta) {
        cartas.add(carta);
    }


    public Mazo clonar(){
        Mazo mazoCopia = new Mazo();
        for (Carta carta : cartas) {
            mazoCopia.agregarCarta(carta.clonar());
        }
        return mazoCopia;
    }
}
