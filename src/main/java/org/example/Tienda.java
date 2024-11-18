package org.example;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Tienda {
    private List<Comodin> comodines;
    private List<Tarot> tarots;
    private Carta carta;

    @JsonCreator
    public Tienda(@JsonProperty("comodines") List<Comodin> comodines, @JsonProperty("tarots") List<Tarot> tarots, @JsonProperty("carta") Carta carta){
        this.comodines = comodines;
        this.tarots = tarots;
        this.carta = carta;
    }
}
