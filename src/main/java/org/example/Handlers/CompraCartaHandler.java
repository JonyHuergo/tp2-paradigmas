package org.example.Handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import org.example.Carta;
import org.example.Controladores.PantallaJuegoController;
import org.example.Jugador;
import org.example.Mazo;
import org.example.Ronda;

import java.util.List;

public class CompraCartaHandler implements EventHandler<ActionEvent> {

    private final Carta carta;
    private final Mazo mazo;
    private final Jugador jugador;
    private PantallaJuegoController pantallaJuegoController;
    private List<Ronda> rondas;
    private int numeroRonda;


    public CompraCartaHandler(Carta carta, Mazo mazo, PantallaJuegoController pantallaJuegoController, List<Ronda> rondas, int numeroRonda, Jugador jugador) {
        this.carta = carta;
        this.mazo = mazo;
        this.jugador = jugador;
        this.pantallaJuegoController = pantallaJuegoController;
        this.rondas = rondas;
        this.numeroRonda = numeroRonda;

    }

    @Override
    public void handle(ActionEvent event) {
        System.out.println("Card purchased: " + carta.getPalo());
        ActionHandler.actionSound();
        jugador.agregarCartaMazo(carta);
        pantallaJuegoController.iniciarPantallaJuego(mazo, rondas, numeroRonda, jugador);

    }
}
