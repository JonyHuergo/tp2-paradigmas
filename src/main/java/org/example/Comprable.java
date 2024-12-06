package org.example;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import org.example.Controladores.PantallaJuegoController;

public abstract class Comprable {

    public abstract EventHandler<ActionEvent> crearHandler(PantallaJuegoController pantallaJuegoController, Mazo mazo, Jugador jugador, int puntajeASuperar);

    public abstract String getRuta();
}