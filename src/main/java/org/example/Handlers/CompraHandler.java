package org.example.Handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import org.example.Controladores.PantallaJuegoController;
import org.example.Mazo;

public abstract class CompraHandler implements EventHandler<ActionEvent> {

    protected PantallaJuegoController pantallaJuegoController;
    protected Mazo mazo;
    protected int puntajeASuperar;

    public CompraHandler(PantallaJuegoController pantallaJuegoController, Mazo mazo, int puntajeASuperar) {
        this.pantallaJuegoController = pantallaJuegoController;
        this.mazo = mazo;
        this.puntajeASuperar = puntajeASuperar;
    }

    @Override
    public void handle(ActionEvent event) {
        executeCompra();
        avanzar();
    }

    protected abstract void executeCompra();

    protected void avanzar() {
        pantallaJuegoController.iniciarPantallaJuego(mazo, puntajeASuperar);
    }
}
