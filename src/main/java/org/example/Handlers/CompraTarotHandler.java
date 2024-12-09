package org.example.Handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import org.example.Controladores.PantallaJuegoController;
import org.example.Mazo;
import org.example.Tarot.Tarot;
import org.example.Jugador;

public class CompraTarotHandler implements EventHandler<ActionEvent> {

    private final Tarot tarot;
    private final Jugador jugador;
    private PantallaJuegoController pantallaJuegoController;
    private Mazo mazo;
    private int puntajeASuperar;


    public CompraTarotHandler(Tarot tarot, Jugador jugador, PantallaJuegoController pantallaJuegoController, Mazo mazo, int puntajeASuperar) {
        this.tarot = tarot;
        this.jugador = jugador;
        this.pantallaJuegoController = pantallaJuegoController;
        this.mazo = mazo;
        this.puntajeASuperar = puntajeASuperar;

    }

    @Override
    public void handle(ActionEvent event) {
        System.out.println("Tarot card purchased: ");
        ActionHandler.actionSound();
        jugador.agregarTarot(tarot);
        System.out.println(jugador.getCantidadDeTarots());
        pantallaJuegoController.iniciarPantallaJuego(mazo, puntajeASuperar, jugador);

    }
}
