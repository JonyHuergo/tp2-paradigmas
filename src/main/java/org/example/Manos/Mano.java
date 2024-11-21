package org.example.Manos;

import org.example.Carta;
import org.example.Puntaje;

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
    };
    public  int getPuntajeBase() {
        return puntajeBase;
    }
    public int getMultiplicadorBase() {
        return multiplicadorBase;
    };

    public Puntaje calcular(ArrayList<Carta> cartas){
        for (Carta carta : cartas) {
            puntajeBase = carta.actualizarPuntajeTotal(puntajeBase);
            multiplicadorBase = carta.actualizarMultiplicadorTotal(multiplicadorBase);
        }
        return new Puntaje(puntajeBase * multiplicadorBase);
    }
}
