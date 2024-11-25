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

    public boolean nombreEsIgual(String nombre){
        return(this.nombre == nombre);
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

    public int hacerCalculo(){
        return (puntajeBase * multiplicadorBase);
    }

}
