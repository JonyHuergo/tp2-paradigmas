package org.example.Manos;

public class Full extends Mano{
    @Override
    public String getNombre() {
        return "Full";
    }

    @Override
    public int getPuntajeBase(){
        return 40;
    }

    @Override
    public int getMultiplicadorBase(){
        return 4;
    }
}