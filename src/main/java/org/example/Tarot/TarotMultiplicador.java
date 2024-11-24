package org.example.Tarot;

import org.example.Carta;

//Cambia multiplicador de la carta a un x6
public class TarotMultiplicador extends Tarot {
    private int multiplicador;

    public TarotMultiplicador(int multiplicador) {
        this.multiplicador = multiplicador;
    }

    @Override
    public void aplicarEfecto(Carta carta) {
        carta.modificarMultiplicador(multiplicador);
    };
}
