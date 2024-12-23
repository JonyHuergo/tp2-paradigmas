package org.example.Comodin;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import org.example.*;
import org.example.Controladores.PantallaJuegoController;
import org.example.Handlers.CompraComodinHandler;

import java.util.List;

public abstract class Comodin extends Comprable {
    protected String nombre;
    protected String descripcion;
    protected Activacion activacion;
    protected int puntajeAdicional;
    protected float multiplicador;

    public Comodin(String nombre, String descripcion, Activacion activacion, int puntajeAdicional,
            float multiplicador) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.activacion = activacion;
        this.puntajeAdicional = puntajeAdicional;
        this.multiplicador = multiplicador;
    }

    public Comodin(int puntajeAdicional, float multiplicador, Activacion activacion) {
        this.puntajeAdicional = puntajeAdicional;
        this.multiplicador = multiplicador;
        this.activacion = activacion;
    }

    public Comodin(String nombre, String descripcion, List<Comodin> subComodines) {
        // Implementación
        this.multiplicador = multiplicador;
    }

    @Override
    public EventHandler<ActionEvent> crearHandler(PantallaJuegoController pantallaJuegoController, Mazo mazo,
            Jugador jugador, List<Ronda> rondas, int numeroRonda) {
        return new CompraComodinHandler(pantallaJuegoController, mazo, rondas, numeroRonda, this, jugador);
    }

    public abstract void usar(Jugador jugador);

    public abstract void usar(Jugada jugada);

    @Override
    public String getRuta() {
        return "/Comodines/" + nombre + ".png";
    }

    @Override
    public String getDescripcion() {
        return descripcion;
    }
}