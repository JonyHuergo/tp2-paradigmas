package org.example.Manos;

public class Poker extends Mano{
    @Override
    public String getNombre() {
        return "Poker";
    }

    @Override
    public int getPuntajeBase(){
        return 60;
    }

    @Override
    public int getMultiplicadorBase(){
        return 7;
    }
}
