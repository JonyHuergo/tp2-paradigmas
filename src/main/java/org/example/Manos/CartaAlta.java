package org.example.Manos;

public class CartaAlta extends Mano{
    @Override
    public String getNombre() {
        return "Carta Alta";
    }

    @Override
    public int getPuntajeBase(){
        return 5;
    }

    @Override
    public int getMultiplicadorBase(){
        return 1;
    }
}