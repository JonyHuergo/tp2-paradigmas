package org.example.Handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import org.example.Tarot.Tarot;
import org.example.Jugador;

public class CompraTarotHandler implements EventHandler<ActionEvent> {

    private final Tarot tarot;
    private final Jugador jugador;

    public CompraTarotHandler(Tarot tarot, Jugador jugador) {
        this.tarot = tarot;
        this.jugador = jugador;
    }

    @Override
    public void handle(ActionEvent event) {
        System.out.println("Tarot card purchased: ");
        jugador.agregarTarot(tarot);
    }
}
