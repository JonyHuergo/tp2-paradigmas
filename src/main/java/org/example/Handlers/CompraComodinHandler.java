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

import java.util.List;

public class CompraComodinHandler implements EventHandler<ActionEvent> {

    private final Comodin comodin;
    private final Jugador jugador;
    private PantallaJuegoController pantallaJuegoController;
    private Mazo mazo;
    private List<Ronda> rondas;
    private int numeroRonda;

    public CompraComodinHandler(PantallaJuegoController pantallaJuegoController, Mazo mazo, List<Ronda> rondas, int numeroRonda, Comodin comodin, Jugador jugador) {
        this.comodin = comodin;
        this.jugador = jugador;
        this.pantallaJuegoController = pantallaJuegoController;
        this.mazo = mazo;
        this.rondas = rondas;
        this.numeroRonda = numeroRonda;
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
        pantallaJuegoController.iniciarPantallaJuego(mazo, rondas, numeroRonda, jugador);
    }
}
