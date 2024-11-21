package org.example.Manos;

public class Trio extends Mano{
    @Override
    public String getNombre() {
        return "Trio";
    }

    @Override
    public int getPuntajeBase(){
        return 30;
    }

    @Override
    public int getMultiplicadorBase(){
        return 3;
    }
}
