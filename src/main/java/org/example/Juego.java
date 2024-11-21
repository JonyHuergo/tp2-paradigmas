package org.example;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true) // Ignora las propiedades no mapeadas
public class Juego {
    private ArrayList<Ronda> rondas = new ArrayList<Ronda>();
    private int numeroRonda = 0;
    private int cantidadRondas = 8;
    private boolean juegoGanado = false;
    private Jugador jugador;

    @JsonCreator
    public Juego(@JsonProperty("rondas") ArrayList<Ronda> rondas){
        this.rondas = rondas;
    }

    public void jugar(){

        while(rondas.get(numeroRonda).esGanada(jugador) && numeroRonda <= cantidadRondas){
            numeroRonda++;
        }

        if (numeroRonda == 8) {
            juegoGanado = true;
        }
        juegoGanado = false;
    }

    public void PartidaPerdida(){

    }
}
