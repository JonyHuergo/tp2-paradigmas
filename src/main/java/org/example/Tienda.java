package org.example;

import org.example.Comodin.Comodin;
import org.example.Tarot.Tarot;
import java.util.List;

public class Tienda {
    List<Comodin> comodines;
    List<Tarot> tarots;
    Carta carta;

    public Tienda(List<Comodin> comodines, List<Tarot> tarots, Carta carta) {
        this.comodines = comodines;
        this.tarots = tarots;
        this.carta = carta;
    }

    public List<Comodin> obtenerComodines(){
        return comodines;
    }
    public List<Tarot> obtenerTarots(){
        return tarots;
    }
    public Carta obtenerCarta(){
        return carta;
    }
}
