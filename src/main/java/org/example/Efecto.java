package org.example;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class Efecto {
    private int puntos;
    private int multiplicador;

    @JsonCreator
    public Efecto(@JsonProperty("puntos") int puntos, @JsonProperty("multiplicador") int multiplicador) {
        this.puntos = puntos;
        this.multiplicador = multiplicador;
    }

    public int getPuntos() {
        return this.puntos;
    }

    public int getMultiplicador() {
        return this.multiplicador;
    }
}
