package org.example;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class Efecto {
    private Puntaje puntos;
    private Puntaje multiplicador;

    @JsonCreator
    public Efecto(@JsonProperty("puntos") int puntos, @JsonProperty("multiplicador") int multiplicador) {
        this.puntos = new Puntaje(puntos);
        this.multiplicador = new Puntaje(multiplicador);
    }

    public Puntaje getPuntos() {
        return this.puntos;
    }

    public Puntaje getMultiplicador() {
        return this.multiplicador;
    }
}
