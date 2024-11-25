package org.example.Tarot;

import org.example.Carta;

public class TarotAgregaPuntos  extends Tarot {
    private int puntos;

    public TarotAgregaPuntos(int puntos){
        this.puntos = puntos;
    }
    @Override
    public void aplicarEfecto(Carta carta) {
        carta.agregarPuntos(10);
    };
}
