package org.example.Tarot;

import org.example.Carta;

public class TarotSobreCarta extends Tarot{

    public TarotSobreCarta(String nombre, String descripcion, String sobre, String ejemplar, int puntos, float multiplicador){
        super(nombre, descripcion, sobre, ejemplar, puntos, multiplicador);
    }
    public TarotSobreCarta(int puntos){
        super(puntos);
    }
    @Override
    public void aplicarEfecto(Carta carta) {
        carta.agregarPuntos(this.puntos);
        carta.modificarMultiplicador(this.multiplicador);
    };

}
