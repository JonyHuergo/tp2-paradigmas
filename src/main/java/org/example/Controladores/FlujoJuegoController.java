package org.example.Controladores;

import javafx.scene.control.Button;
import org.example.Carta;

import java.util.ArrayList;
import java.util.List;

public class FlujoJuegoController {
    private final List<Carta> cartasSeleccionadas = new ArrayList<>();

    public void seleccionarCarta(Carta carta, Button cartaButton) {
        if (cartasSeleccionadas.contains(carta)) {
            // Deseleccionar carta
            cartasSeleccionadas.remove(carta);
            cartaButton.setStyle("-fx-background-color: transparent; -fx-padding: -5;");
        } else if (cartasSeleccionadas.size() < 5) {
            // Seleccionar carta
            cartasSeleccionadas.add(carta);
            cartaButton.setStyle("-fx-background-color: transparent;-fx-padding: -5;-fx-translate-y: -10");
        }
    }

    public List<Carta> getCartasSeleccionadas() {
        return cartasSeleccionadas;
    }
}
