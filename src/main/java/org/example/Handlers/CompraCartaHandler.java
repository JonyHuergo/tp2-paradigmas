package org.example.Handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import org.example.Carta;
import org.example.Mazo;

public class CompraCartaHandler implements EventHandler<ActionEvent> {

    private final Carta carta;
    private final Mazo mazo;


    public CompraCartaHandler(Carta carta, Mazo mazo) {
        this.carta = carta;
        this.mazo = mazo;
    }

    @Override
    public void handle(ActionEvent event) {
        System.out.println("Card purchased: " + carta.getPalo());
        mazo.agregarCarta(carta);

    }
}
