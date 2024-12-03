package org.example.Handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import org.example.Carta;
import org.example.Controladores.FlujoJuegoController;

public class CartaButtonHandler implements EventHandler<ActionEvent> {
    private final Carta carta;
    private final Button cartaButton;
    private final FlujoJuegoController controller;

    public CartaButtonHandler(Carta carta, Button cartaButton, FlujoJuegoController controller) {
        this.carta = carta;
        this.cartaButton = cartaButton;
        this.controller = controller;
    }

    @Override
    public void handle(ActionEvent event) {
        ActionHandler.actionSound();
        controller.seleccionarCarta(carta, cartaButton);
    }
}
