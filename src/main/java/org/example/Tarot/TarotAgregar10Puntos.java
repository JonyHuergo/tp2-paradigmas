package org.example.Tarot;

import org.example.Carta;

public class TarotAgregar10Puntos  extends Tarot {
    @Override
    public void aplicarEfecto(Carta carta) {
        carta.agregarPuntos(10);
    };
}
