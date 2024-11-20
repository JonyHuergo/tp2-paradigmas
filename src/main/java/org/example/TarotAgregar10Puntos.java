package org.example;

import org.example.Tarot.Tarot;

public class TarotAgregar10Puntos  extends Tarot {

    public TarotAgregar10Puntos(String nombre, String descripcion, Efecto efecto, String sobre, String ejemplar) {
        super(nombre, descripcion, efecto, sobre, ejemplar);
    }

    @Override
    public void aplicarEfecto(Carta carta) {
        carta.agregarPuntos(new Puntaje(10));
    };
}
