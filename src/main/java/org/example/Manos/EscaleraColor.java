package org.example.Manos;

public class EscaleraColor extends Mano{
    @Override
    public String getNombre() {
        return "Escalera Color";
    }

    @Override
    public int getPuntajeBase(){
        return 100;
    }

    @Override
    public int getMultiplicadorBase(){
        return 8;
    }
}