package org.example.Handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import org.example.Carta;
import org.example.Controladores.FlujoJuegoController;
import org.example.Mazo;

public class CartaButtonHandler implements EventHandler<ActionEvent> {
    private final Carta carta;
    private final Button cartaButton;
    private final FlujoJuegoController controller;
    private final Mazo mazo;

    public CartaButtonHandler(Carta carta, Button cartaButton, FlujoJuegoController controller, Mazo mazo) {
        this.carta = carta;
        this.cartaButton = cartaButton;
        this.controller = controller;
        this.mazo = mazo;
    }

    @Override
    public void handle(ActionEvent event) {
        ActionHandler.actionSound();
        controller.seleccionarCarta(carta, cartaButton, mazo);
    }
}
