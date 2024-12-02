package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Mazo {
    private ArrayList<Carta> cartas = new ArrayList<>();

    public Mazo() {
        cartas = new ArrayList<>();
        LectorArchivosJson lector = new LectorArchivosJson();
        this.cartas = lector.leerMazo();
    }

    public Mazo(ArrayList<Carta> cartas) {
        this.cartas = cartas;
    }

    // Se va a necesitar en un futuro (el juego siempre muestra cuantas cartas tiene el mazo ej: 44/52)
    public int cantidadDeCartas(){
        return cartas.size();
    }

//    public ArrayList<Carta> repartir(int cantidad) {
//        ArrayList<Carta> mano = new ArrayList<>();
//        for (int i = 0; i < cantidad && !cartas.isEmpty(); i++) {
//            mano.add(cartas.remove(0));
//        }
//        return mano;
//    }

    public ArrayList<Carta> repartirCartas(int cantidad) {
        ArrayList<Carta> cartasRepartidas = new ArrayList<>();

        // Verificar si hay suficientes cartas en el mazo
        if (cantidad > cartas.size()) {
            System.out.println("No hay suficientes cartas en el mazo.");
            return cartas;  // Devolver todas las cartas si no hay suficientes
        }

        // Barajar las cartas antes de repartir
        Collections.shuffle(cartas);

        // Repartir las cartas solicitadas
        for (int i = 0; i < cantidad; i++) {
            cartasRepartidas.add(cartas.remove(0)); // Eliminar la carta del mazo
        }

        return cartasRepartidas;  // Devolver las cartas repartidas
    }

    public void repartir(ArrayList<Carta> cartasDisponibles, int limiteCartas) {
        int cartasARepartir = limiteCartas - cartasDisponibles.size();

        Random random = new Random();
//        int pos = random.nextInt(cartas.size() - 1);

        for (int i = 0; i < cartasARepartir && !cartas.isEmpty(); i++) {
            int pos = random.nextInt(cartas.size());
            cartasDisponibles.add(cartas.remove(pos));
        }

    }
}
