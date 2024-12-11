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
        String[] palos = {"corazones", "diamantes", "trebol", "picas"};
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

    public Carta buscarCarta(String palo, String valor){
        int valorCarta = convertirNumero(valor);
        for (Carta carta : cartas) {
            if (carta.paloEsIgual(palo) && carta.valorEsIgual(valorCarta)) {
                return carta;
            }
        }
        System.out.println("No se encontro el carta " + palo + ".");
        return null;
    }

    private int convertirNumero(String numero) {
        switch (numero) {
            case "J": return 11;
            case "Q": return 12;
            case "K": return 13;
            case "A": return 14;
            default: return Integer.parseInt(numero);
        }
    }
}
