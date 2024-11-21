package org.example;

public class TarotAgregar10Puntos  extends Tarot {
    @Override
    public void aplicarEfecto(Carta carta) {
        carta.agregarPuntos(10);
    };
}
