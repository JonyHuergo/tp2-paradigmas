package org.example.Manos;

import org.example.Carta;
import org.example.Comodin.Comodin;

import java.util.ArrayList;
import java.util.Objects;

public abstract class Mano {
    private String nombre;
    private int puntajeBase;
    private float multiplicadorBase;

    public Mano(String nombre, int puntajeBase, int multiplicadorBase) {
        this.nombre = nombre;
        this.puntajeBase = puntajeBase;
        this.multiplicadorBase = multiplicadorBase;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean nombreEsIgual(String nombre){
        return(Objects.equals(this.nombre, nombre));
    }

    public void actualizarPuntajeBase(int puntaje) {
        this.puntajeBase += puntaje;
    }

    public float getMultiplicadorBase() {
        return multiplicadorBase;
    }

    public void actualizarMultiplicadorBase(float mult) {
        this.multiplicadorBase += mult;
    }

    public void multiplicarMultiplicadorBase(float mult) {
        this.multiplicadorBase = multiplicadorBase * mult;
    }

    public float calcular(ArrayList<Carta> cartas){
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

    public float hacerCalculo(){
        return (puntajeBase * multiplicadorBase);
    }

    public int getPuntajeBase(){
        return puntajeBase;
    }

}
