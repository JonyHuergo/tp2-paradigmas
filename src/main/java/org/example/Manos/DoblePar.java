package org.example.Manos;

public class DoblePar extends Mano{
    @Override
    public String getNombre() {
        return "Doble Par";
    }

    @Override
    public int getPuntajeBase(){
        return 20;
    }

    @Override
    public int getMultiplicadorBase(){
        return 2;
    }
}
