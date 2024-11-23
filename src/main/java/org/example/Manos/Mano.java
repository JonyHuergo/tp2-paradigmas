package org.example.Manos;

import org.example.Carta;
import org.example.Comodin.Comodin;

import java.util.ArrayList;

public abstract class Mano {
    private String nombre;
    private int puntajeBase;
    private int multiplicadorBase;

    public Mano(String nombre, int puntajeBase, int multiplicadorBase) {
        this.nombre = nombre;
        this.puntajeBase = puntajeBase;
        this.multiplicadorBase = multiplicadorBase;
    }

    public String getNombre(){
        return nombre;
    }
    public  int getPuntajeBase() {
        return puntajeBase;
    }

    public void actualizarPuntajeBase(int puntaje) {
        this.puntajeBase += puntaje;
    }

    public int getMultiplicadorBase() {
        return multiplicadorBase;
    }

    public void actualizarMultiplicadorBase(int mult) {
        this.multiplicadorBase += mult;
    }

    public void multiplicarMultiplicadorBase(int mult) {
        this.multiplicadorBase = multiplicadorBase * mult;
    }

    public int calcular(ArrayList<Carta> cartas){
        for (Carta carta : cartas) {
            puntajeBase = carta.actualizarPuntajeTotal(puntajeBase);
            multiplicadorBase = carta.actualizarMultiplicadorTotal(multiplicadorBase);
        }
        return (puntajeBase * multiplicadorBase);
    }

    public void sumarValorCartas(ArrayList<Carta> cartas){
        for (Carta carta : cartas) {
            puntajeBase = carta.actualizarPuntajeTotal(puntajeBase);
            multiplicadorBase = carta.actualizarMultiplicadorTotal(multiplicadorBase);
        }
    }

//    public void sumarValorComodines(ArrayList<Comodin> comodin){
//        for (Carta carta : cartas) {
//            puntajeBase = carta.actualizarPuntajeTotal(puntajeBase);
//            multiplicadorBase = carta.actualizarMultiplicadorTotal(multiplicadorBase);
//        }
//    }

    public int hacerCalculo(){
        return (puntajeBase * multiplicadorBase);
    }

//    public int calcular(ArrayList<Carta> cartas, Comodin comodin){
//        for (Carta carta : cartas) {
//            puntajeBase = carta.actualizarPuntajeTotal(puntajeBase);
//            multiplicadorBase = carta.actualizarMultiplicadorTotal(multiplicadorBase);
//        }
//
//        if (comodin.cumpleCondicion(nombre)) {
//            puntajeBase = comodin.actualizarPuntajeTotal(puntajeBase);
//            multiplicadorBase = comodin.actualizarMultiplicadorTotal(multiplicadorBase);
//            multiplicadorBase = comodin.aumentaMultiplicadorTotal(multiplicadorBase);
//        }
//
//        return (puntajeBase * multiplicadorBase);
//    }
}
