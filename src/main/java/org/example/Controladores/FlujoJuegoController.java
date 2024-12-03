package org.example.Controladores;

import javafx.scene.control.Label;
import javafx.scene.control.Button;
import org.example.AnalizadorMano;
import org.example.Carta;
import org.example.Manos.Mano;

import java.util.ArrayList;

public class FlujoJuegoController {

    // for 8 rondas:
    //     muestra tiendaScreen (elije 4 cartas)
    //     muestra JuegoScreen
    //     for 5 turnos:
    //          -reparten aleatoriamente 8 cartas del mazo
    //          -jugarlas tratando de armar una mano de poker.
    //          -aplicar algún modificador usando un tarot.
    //          -descartar las que quiera hasta tres veces, donde debe recibir del mazo las cartas suficientes para completar
    //          -calcular puntaje utilizando los comodines de izquierda a derecha y se suma el puntaje y se avanza al siguiente


    private final ArrayList<Carta> cartasSeleccionadas = new ArrayList<>();
    private Label manoLabel;
    private Label puntajeLabel;
    private Label multiplicadorLabel;// Label to update hand name

    public FlujoJuegoController(Label manoLabel, Label puntajeLabel, Label multiplicadorLabel) {
        this.manoLabel = manoLabel;
        this.puntajeLabel = puntajeLabel;
        this.multiplicadorLabel = multiplicadorLabel;
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

            String valorPuntaje = String.valueOf(mano.getPuntajeBase());
            puntajeLabel.setText(valorPuntaje);

            float multiplicadorBase = mano.getMultiplicadorBase();
            String valorMultiplicador = String.format("%.0f", multiplicadorBase);
            multiplicadorLabel.setText(valorMultiplicador);


            // Actualizar el nombre de la mano en el label
            manoLabel.setText("Mano: " + nombreMano);

        } else {
            manoLabel.setText("Mano: Ninguna");
            puntajeLabel.setText("0");
            multiplicadorLabel.setText("0");
        }
    }

    public ArrayList<Carta> getCartasSeleccionadas() {
        return cartasSeleccionadas;
    }

    public Label getManoLabel() {
        return manoLabel;
    }


    public Label getPuntajeLabel() {
        return puntajeLabel;
    }

    public Label getMultiplicadorLabel() {
        return multiplicadorLabel;
    }
}
