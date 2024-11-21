package org.example.Manos;

public class EscaleraReal extends Mano{
    @Override
    public String getNombre() {
        return "Escalera Real";
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
