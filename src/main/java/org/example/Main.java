package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            Juego juego = objectMapper.readValue(new File("src/main/resources/balatro.json"), Juego.class);
            System.out.println(juego);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}