package org.example;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.Tarot.Tarot;

public class TarotElMago extends Tarot {
/*
    public TarotElMago() {
        super();
    }*/

    @JsonCreator
    public TarotElMago(
            @JsonProperty("nombre") String nombre,
            @JsonProperty("descripcion") String descripcion,
            @JsonProperty("efecto") Efecto efecto,
            @JsonProperty("sobre") String sobre,
            @JsonProperty("ejemplar") String ejemplar) {
        super(nombre, descripcion, efecto, sobre, ejemplar);
    }
    /*
    public TarotElMago(String nombre, String descripcion, Efecto efecto, String sobre, String ejemplar) {
        super(nombre, descripcion, efecto, sobre, ejemplar);
    }*/

    @Override
    public void aplicarEfecto(Carta carta) {
    }

}

