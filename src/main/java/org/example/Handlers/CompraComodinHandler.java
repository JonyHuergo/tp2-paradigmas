package org.example.Handlers;

import org.example.Comodin.Comodin;
import org.example.Controladores.PantallaJuegoController;
import org.example.Jugador;
import org.example.Mazo;

public class CompraComodinHandler extends CompraHandler {

    private final Comodin comodin;
    private final Jugador jugador;

    public CompraComodinHandler(PantallaJuegoController pantallaJuegoController, Mazo mazo, int puntajeASuperar, Comodin comodin, Jugador jugador) {
        super(pantallaJuegoController, mazo, puntajeASuperar);
        this.comodin = comodin;
        this.jugador = jugador;
    }

    @Override
    protected void executeCompra() {
        System.out.println("hola");
        jugador.agregarComodin(comodin);
    }

    @Override
    protected void avanzar() {
        super.avanzar();
    }
}
