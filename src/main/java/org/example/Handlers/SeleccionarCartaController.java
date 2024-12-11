package org.example.Controladores;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import org.example.Carta;
import org.example.Mazo;

public class SeleccionarCartaController {

    @FXML
    private ComboBox<String> numeroDropdown;
    @FXML
    private ComboBox<String> paloDropdown;
    @FXML
    private Button confirmarButton;

    private Carta cartaSeleccionada;

    private Mazo mazo;

    public SeleccionarCartaController(Mazo mazo) {
        this.mazo = mazo;
    }

    public void initialize() {
        // Poblamos el dropdown de números
        numeroDropdown.setItems(FXCollections.observableArrayList("2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"));

        // Poblamos el dropdown de palos
        paloDropdown.setItems(FXCollections.observableArrayList("Corazones", "Trebol", "Diamantes", "Picas"));

        // Configuramos el botón para confirmar la selección
        confirmarButton.setOnAction(event -> {
            String numeroSeleccionado = numeroDropdown.getValue();
            String paloSeleccionado = paloDropdown.getValue();

            if (numeroSeleccionado != null && paloSeleccionado != null) {
                // Crear la carta seleccionada
                cartaSeleccionada = mazo.buscarCarta(paloSeleccionado, numeroSeleccionado);
                // Cerrar la ventana
                Stage stage = (Stage) confirmarButton.getScene().getWindow();
                stage.close();
            }
        });
    }



    public Carta getCartaSeleccionada() {
        return cartaSeleccionada;
    }

//    public Carta getCartaSeleccionada() {
//        String palo = cbPalos.getValue().toLowerCase();
//        palo = palo.replace("é", "e"); // Normalizar el palo seleccionado.
//
//        String numero = cbNumeros.getValue();
//        int valor;
//
//        if (numero.equalsIgnoreCase("AS")) {
//            valor = 14; // Valor del As.
//        } else {
//            valor = Integer.parseInt(numero); // Convertir a entero.
//        }
//
//        // Buscar la carta en el mazo.
//        for (Carta carta : mazo.getCartas()) {
//            if (carta.getPalo().equals(palo) && carta.getNumero() == valor) {
//                return carta;
//            }
//        }
//
//        // Si no se encuentra la carta, lanzar una excepción o manejar el caso.
//        throw new IllegalArgumentException("La carta seleccionada no está en el mazo.");
//    }

}
