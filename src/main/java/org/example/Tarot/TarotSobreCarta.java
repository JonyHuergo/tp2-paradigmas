package org.example.Tarot;

import org.example.Carta;
import org.example.ManoPoker;

public class TarotSobreCarta extends Tarot {
    private Carta carta;
    public TarotSobreCarta(String nombre, String descripcion, String sobre, String ejemplar, int puntos, float multiplicador){
        super(nombre, descripcion, sobre, ejemplar, puntos, multiplicador);
    }
    @Override
    public void aplicarEfecto(ManoPoker manoPoker) {manoPoker.mejorarCarta(this.carta, this.puntos, this.multiplicador);
        manoPoker.mejorarCarta(this.carta, this.puntos, this.multiplicador);
    }
    public void usarSobre(Carta carta){
        this.carta = carta;
    }

}