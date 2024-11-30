package org.example.Comodin;

import org.example.Jugada;
import org.example.Jugador;

import java.util.List;

public abstract class Comodin {
    protected String nombre;
    protected String descripcion;
    protected Activacion activacion;
    protected int puntajeAdicional;
    protected float multiplicador;

    public Comodin(String nombre, String descripcion, Activacion activacion, int puntajeAdicional, float multiplicador) {
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

    public abstract void usar(Jugador jugador);

    public abstract void usar(Jugada jugada);
}
