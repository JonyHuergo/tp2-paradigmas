package org.example;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.Tarot.Tarot;

import java.util.List;

public class Tienda {
    //private List<Comodin> comodinesTotales;
    //private List<Tarot> tarotsTotales;
    private Carta carta;
    private List<Comodin> comodines;
    private List<Tarot> tarots;


    @JsonCreator
    public Tienda(@JsonProperty("comodines") List<Comodin> comodines, @JsonProperty("tarots") List<Tarot> tarots, @JsonProperty("carta") Carta carta){

        this.carta = carta;
        this.comodines = comodines;
        this.tarots = tarots;
/*
        ObjectMapper comodinesMapper = new ObjectMapper();
        try {
            this.comodinesTotales = comodinesMapper.readValue(
                    new File("src/main/resources/comodines.json"),
                    new TypeReference<List<Comodin>>() {});
        } catch (IOException e) {
            //e.printStackTrace();
        }
        ObjectMapper tarotsMapper = new ObjectMapper();
        try {
            this.tarotsTotales = tarotsMapper.readValue(
                    new File("src/main/resources/tarots.json"),
                    new TypeReference<List<Tarot>>() {});
        } catch (IOException e) {
            //e.printStackTrace();
        }*/

    }
}
