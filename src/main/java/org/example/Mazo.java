package org.example;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Mazo {
    private ArrayList<Carta> cartas = new ArrayList<>();


    /* public ArrayList<Carta> getCartas(){
        return cartas;
    } */

    // Se va a necesitar en un futuro (el juego siempre muestra cuantas cartas tiene el mazo ej: 44/52)
    public int cantidadDeCartas(){ 
        return cartas.size();
    }

    @JsonCreator
    public Mazo() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Mazo mazo = objectMapper.readValue(new File("src/main/resources/mazo.json"), Mazo.class);
            System.out.println(mazo);
        } catch (IOException e) {
            // Maneja las excepciones
            e.printStackTrace();
        }
    }



    public ArrayList<Carta> repartir(int cantidad) {
        ArrayList<Carta> mano = new ArrayList<>();
        for (int i = 0; i < cantidad && !cartas.isEmpty(); i++) {
            mano.add(cartas.remove(0));
        }
        return mano;
    }
}
