package org.example.Manos;

public class Color extends Mano{
    @Override
    public String getNombre() {
        return "Color";
    }

    @Override
    public int getPuntajeBase(){
        return 35;
    }

    @Override
    public int getMultiplicadorBase(){
        return 4;
    }
}