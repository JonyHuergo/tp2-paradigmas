package org.example;

public class TarotAgregar10Puntos  extends Tarot {
    @Override
    public void aplicarEfecto(Carta carta) {
        carta.agregarPuntos(new Puntaje(10));
    };
}
