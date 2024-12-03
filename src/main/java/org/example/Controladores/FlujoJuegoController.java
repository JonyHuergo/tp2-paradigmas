package org.example.Controladores;

import javafx.scene.control.Label;
import javafx.scene.control.Button;
import org.example.AnalizadorMano;
import org.example.Carta;
import org.example.Manos.Mano;

import java.util.ArrayList;

public class FlujoJuegoController {
    private final ArrayList<Carta> cartasSeleccionadas = new ArrayList<>();
    private Label manoLabel;  // Label to update hand name

    public FlujoJuegoController(Label manoLabel) {
        this.manoLabel = manoLabel;
    }

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

        // Si hay cartas seleccionadas, analizar la mano
        if (!cartasSeleccionadas.isEmpty()) {
            AnalizadorMano analizador = new AnalizadorMano();
            Mano mano = analizador.analizarMano(cartasSeleccionadas);
            String nombreMano = mano.getNombre();

            // Actualizar el nombre de la mano en el label
            manoLabel.setText("Mano: " + nombreMano);
        } else {
            manoLabel.setText("Mano: Ninguna");
        }
    }

    public ArrayList<Carta> getCartasSeleccionadas() {
        return cartasSeleccionadas;
    }

    public Label getManoLabel() {
        return manoLabel;
    }
}
