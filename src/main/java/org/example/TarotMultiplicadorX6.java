package org.example;

import org.example.Tarot.Tarot;

//Cambia multiplicador de la carta a un x6
public class TarotMultiplicadorX6 extends Tarot {

    public TarotMultiplicadorX6(String nombre, String descripcion, Efecto efecto, String sobre, String ejemplar) {
        super(nombre, descripcion, efecto, sobre, ejemplar);
    }

    @Override
    public void aplicarEfecto(Carta carta) {
        carta.modificarMultiplicador(6);
    };
}
