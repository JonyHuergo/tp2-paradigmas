package org.example.Handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import org.example.Carta;
import org.example.Controladores.PantallaJuegoController;
import org.example.Jugador;
import org.example.Mazo;

public class CompraCartaHandler implements EventHandler<ActionEvent> {

    private final Carta carta;
    private final Mazo mazo;
    private final Jugador jugador;
    private PantallaJuegoController pantallaJuegoController;
    private int puntajeASuperar;


    public CompraCartaHandler(Carta carta, Mazo mazo, PantallaJuegoController pantallaJuegoController, int puntajeASuperar , Jugador jugador) {
        this.carta = carta;
        this.mazo = mazo;
        this.jugador = jugador;
        this.pantallaJuegoController = pantallaJuegoController;
        this.puntajeASuperar = puntajeASuperar;

    }

    @Override
    public void handle(ActionEvent event) {
        System.out.println("Card purchased: " + carta.getPalo());
        mazo.agregarCarta(carta);
        System.out.println(mazo.cantidadDeCartas());
        pantallaJuegoController.iniciarPantallaJuego(mazo, puntajeASuperar);

    }
}
