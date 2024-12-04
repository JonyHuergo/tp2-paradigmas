
package org.example.Tarot;

import org.example.Carta;
import org.example.ManoPoker;

public class TarotSobreMano extends Tarot {
    public TarotSobreMano(String nombre, String descripcion, String sobre, String ejemplar, int puntos, float multiplicador){
        super(nombre, descripcion, sobre, ejemplar, puntos, multiplicador);
    }

    @Override
    public void aplicarEfecto(ManoPoker manoPoker) {
        if (manoPoker.manoNombreEsIgual(this.sobre)){
            if (this.puntos > 1){
                manoPoker.actualizarPuntajeBase(this.puntos);
            }
            if (this.multiplicador > 1){
                manoPoker.actualizarMultiplicadorBase(this.multiplicador);
            }
        }
    }

}
