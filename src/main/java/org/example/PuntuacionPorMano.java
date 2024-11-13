package org.example;

import java.util.ArrayList;

public class PuntuacionPorMano {
    private int puntajeBase;
    private int multiplicadorBase;

    public Puntaje calcular(ArrayList<Carta> cartas){
        definirTipoDeMano(cartas);
        for (Carta carta : cartas) {
            puntajeBase = carta.actualizarPuntajeTotal(puntajeBase);
            multiplicadorBase = carta.actualizarMultiplicadorTotal(multiplicadorBase);
        }
        return new Puntaje(puntajeBase * multiplicadorBase);
    }

    private void definirTipoDeMano(ArrayList<Carta> cartas){

        if (esEscaleraReal(cartas)) {
            puntajeBase = 100;
            multiplicadorBase = 8;
        } else if (esEscaleraColor(cartas)) { // Al 12/11 igual que escalera real en el informe
            puntajeBase = 100;
            multiplicadorBase = 8;
        } else if (esPoker(cartas)) {
            puntajeBase = 60;
            multiplicadorBase = 7;
        } else if (esFull(cartas)) {
            puntajeBase = 40;
            multiplicadorBase = 4;
        } else if (esColor(cartas)) {
            puntajeBase = 35;
            multiplicadorBase = 4;
        } else if (esEscalera(cartas)) {
            puntajeBase = 30;
            multiplicadorBase = 4;
        } else if (esTrio(cartas)) {
            puntajeBase = 30;
            multiplicadorBase = 3;
        } else if (esDoblePar(cartas)) {
            puntajeBase = 20;
            multiplicadorBase = 2;
        } else if (esPar(cartas)) {
            puntajeBase = 10;
            multiplicadorBase = 2;
        } else { // Carta alta
            puntajeBase = 5;
            multiplicadorBase = 1;
        }
    }

    private boolean esEscaleraReal(ArrayList<Carta> cartas){ // Revisar
        return esColor(cartas) && esEscalera(cartas);
    }

    private boolean esEscaleraColor(ArrayList<Carta> cartas){
        if (esEscalera(cartas) && esColor(cartas)) {
            return true;
        } else return true;
    }

    private boolean esPoker(ArrayList<Carta> cartas){
        if (cartas.size() == 4) {
            for (int i = 0; i < cartas.size() - 1; i++) {
                if(!cartas.get(i).cartaTieneMismoValor(cartas.get(i + 1))){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private boolean esFull(ArrayList<Carta> cartas){
        return false;
    }

    private boolean esColor(ArrayList<Carta> cartas){
        if (cartas.size() == 5) {
            for (int i = 0; i < cartas.size() - 1; i++) {
                if (!cartas.get(i).paloEsIgual(cartas.get(0))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private boolean esEscalera(ArrayList<Carta> cartas){
        if (cartas.size() == 5) {
            for (int i = 0; i < cartas.size() - 1; i++) {
                if (cartas.get(i).esInmediatamenteInferior(cartas.get(i + 1))){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private boolean esTrio(ArrayList<Carta> cartas){
        if (cartas.size() == 3) {
            for (int i = 0; i < cartas.size() - 1; i++) {
                if (!cartas.get(i).cartaTieneMismoValor(cartas.get(i + 1))){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private boolean esDoblePar(ArrayList<Carta> cartas){
        return false;
    }

    private boolean esPar(ArrayList<Carta> cartas){
        if(cartas.size() == 2){
            for (int i = 0; i < cartas.size() - 1; i++) {
                if (!cartas.get(i).cartaTieneMismoValor(cartas.get(i + 1))){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
