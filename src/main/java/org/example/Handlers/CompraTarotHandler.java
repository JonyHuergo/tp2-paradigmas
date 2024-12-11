package org.example.Handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import org.example.*;
import org.example.Controladores.PantallaJuegoController;
import org.example.Tarot.Tarot;
import org.example.Tarot.TarotSobreCarta;

import java.util.List;

public class CompraTarotHandler implements EventHandler<ActionEvent> {

    private final Tarot tarot;
    private final Jugador jugador;
    private PantallaJuegoController pantallaJuegoController;
    private Mazo mazo;
    private List<Ronda> rondas;
    private int numeroRonda;


    public CompraTarotHandler(Tarot tarot, Jugador jugador, PantallaJuegoController pantallaJuegoController, Mazo mazo, List<Ronda> rondas, int numeroRonda) {
        this.tarot = tarot;
        this.jugador = jugador;
        this.pantallaJuegoController = pantallaJuegoController;
        this.mazo = mazo;
        this.rondas = rondas;
        this.numeroRonda = numeroRonda;

    }

    private Carta seleccionarCarta() {
        // Crear un cuadro de diálogo en JavaFX
        javafx.stage.Stage stage = new javafx.stage.Stage();
        javafx.scene.layout.VBox layout = new javafx.scene.layout.VBox(10);
        layout.setPadding(new javafx.geometry.Insets(10));

        javafx.scene.control.Label label = new javafx.scene.control.Label("Seleccione una carta para aplicar el tarot:");
        layout.getChildren().add(label);

        // Crear botones para cada carta en la mano
        javafx.scene.control.Button botonCancelar = new javafx.scene.control.Button("Cancelar");
        botonCancelar.setOnAction(e -> stage.close());

        Carta[] cartaSeleccionada = {null};
        for (Carta carta : jugador.getCartasDisponibles()) {
            javafx.scene.control.Button botonCarta = new javafx.scene.control.Button(carta.toString());
            botonCarta.setOnAction(e -> {
                cartaSeleccionada[0] = carta;
                stage.close();
            });
            layout.getChildren().add(botonCarta);
        }
        layout.getChildren().add(botonCancelar);

        stage.setScene(new javafx.scene.Scene(layout));
        stage.showAndWait();

        return cartaSeleccionada[0];
    }

    @Override
    public void handle(ActionEvent event) {
        System.out.println("Tarot card purchased: ");
        ActionHandler.actionSound();
        jugador.agregarTarot(tarot);
        System.out.println(jugador.getCantidadDeTarots());

        if (tarot.esSobreCarta()) {
            // Mostrar diálogo para seleccionar la carta
            Carta cartaSeleccionada = seleccionarCarta();
            if (cartaSeleccionada != null) {
                ((TarotSobreCarta) tarot).usarSobre(cartaSeleccionada);
                System.out.println("Tarot aplicado a la carta: " + cartaSeleccionada);
            } else {
                System.out.println("No se seleccionó una carta.");
            }
        } else {
            // Aplicar efecto directamente a la mano
            jugador.agregarTarot(tarot);
        }

        pantallaJuegoController.iniciarPantallaJuego(mazo, rondas, numeroRonda, jugador);
    }



}
