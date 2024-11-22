package org.example.Tarot;

import org.example.Carta;

//Cambia multiplicador de la carta a un x6
public class TarotMultiplicadorX6 extends Tarot {
    @Override
    public void aplicarEfecto(Carta carta) {
        carta.modificarMultiplicador(6);
    };
}
