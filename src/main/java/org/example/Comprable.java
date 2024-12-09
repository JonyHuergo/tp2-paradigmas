package org.example;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import org.example.Controladores.PantallaJuegoController;

import java.util.List;

public abstract class Comprable {

    public abstract EventHandler<ActionEvent> crearHandler(PantallaJuegoController pantallaJuegoController, Mazo mazo, Jugador jugador, List<Ronda> rondas, int numeroRonda);

    public abstract String getRuta();

    public abstract String getDescripcion();
}