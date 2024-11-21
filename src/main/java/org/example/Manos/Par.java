package org.example.Manos;

public class Par extends Mano{
    @Override
    public String getNombre() {
        return "Par";
    }

    @Override
    public int getPuntajeBase(){
        return 10;
    }

    @Override
    public int getMultiplicadorBase(){
        return 2;
    }
}
