package org.example.Manos;

public class Escalera extends Mano{
    @Override
    public String getNombre() {
        return "Escalera";
    }

    @Override
    public int getPuntajeBase(){
        return 30;
    }

    @Override
    public int getMultiplicadorBase(){
        return 4;
    }
}