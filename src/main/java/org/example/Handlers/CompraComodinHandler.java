package org.example.Handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.Comodin.Comodin;
import org.example.Controladores.PantallaJuegoController;
import org.example.Jugador;
import org.example.Mazo;
import org.example.Pantallas.JuegoScreen;
import org.example.Pantallas.TiendaScreen;
import org.example.Ronda;

public class CompraComodinHandler implements EventHandler<ActionEvent> {

    private final Comodin comodin;
    private final Jugador jugador;
    private PantallaJuegoController pantallaJuegoController;
    private Mazo mazo;
    private Ronda ronda;

    public CompraComodinHandler(PantallaJuegoController pantallaJuegoController, Mazo mazo, Ronda ronda, Comodin comodin, Jugador jugador) {
        this.comodin = comodin;
        this.jugador = jugador;
        this.pantallaJuegoController = pantallaJuegoController;
        this.mazo = mazo;
        this.ronda = ronda;
    }

    protected void executeCompra() {
        System.out.println("hola");
        jugador.agregarComodin(comodin);
    }

//    protected void avanzar() {
//        super.avanzar();
//    }

    @Override
    public void handle(ActionEvent event) {

        jugador.agregarComodin(comodin);
        ActionHandler.actionSound();
        System.out.println(jugador.getCantDeComodines());
//        pantallaJuegoController.iniciarPantallaJuego(mazo, ronda, jugador);
    }
}
