package org.example.Handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import org.example.Carta;
import org.example.Controladores.PantallaJuegoController;
import org.example.Jugador;
import org.example.Mazo;
import org.example.Ronda;

public class CompraCartaHandler implements EventHandler<ActionEvent> {

    private final Carta carta;
    private final Mazo mazo;
    private final Jugador jugador;
    private PantallaJuegoController pantallaJuegoController;
    private Ronda ronda;


    public CompraCartaHandler(Carta carta, Mazo mazo, PantallaJuegoController pantallaJuegoController, Ronda ronda, Jugador jugador) {
        this.carta = carta;
        this.mazo = mazo;
        this.jugador = jugador;
        this.pantallaJuegoController = pantallaJuegoController;
        this.ronda =ronda;

    }

    @Override
    public void handle(ActionEvent event) {
        System.out.println("Card purchased: " + carta.getPalo());
        ActionHandler.actionSound();
        mazo.agregarCarta(carta);
        System.out.println(mazo.cantidadDeCartas());
        pantallaJuegoController.iniciarPantallaJuego(mazo, ronda, jugador);

    }
}
