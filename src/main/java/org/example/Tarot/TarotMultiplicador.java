package org.example.Tarot;

import org.example.Carta;
import org.example.ManoPoker;

//Cambia multiplicador de la carta a un x6
public class TarotMultiplicador extends Tarot {

    public TarotMultiplicador(String nombre, String descripcion, String sobre, String ejemplar, int puntos, float multiplicador){
        super(nombre, descripcion, sobre, ejemplar, puntos, multiplicador);
    }

    public TarotMultiplicador(float multiplicador) {
        super(multiplicador);
    }

    @Override
    public void aplicarEfecto(ManoPoker manoPoker) {

    }

    //@Override
    public void aplicarEfecto(Carta carta) {
        carta.modificarMultiplicador(this.multiplicador);
    };
}
