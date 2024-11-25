package org.example;

import org.example.Comodin.Comodin;
import org.example.Tarot.Tarot;

import java.util.ArrayList;
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
}
