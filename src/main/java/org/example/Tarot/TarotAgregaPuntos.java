package org.example.Tarot;

import org.example.Carta;

public class TarotAgregaPuntos  extends Tarot {
    private int puntos;

    public TarotAgregaPuntos(String nombre, String descripcion, String sobre, String ejemplar, int puntos, float multiplicador){
        super(nombre, descripcion, sobre, ejemplar, puntos, multiplicador);
    }

    public TarotAgregaPuntos(int puntos){
        super(puntos);
    }
    @Override
    public void aplicarEfecto(Carta carta) {
        carta.agregarPuntos(10);
    };
}
