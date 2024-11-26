package org.example.Comodin;

import org.example.Jugada;
import org.example.Jugador;

import java.util.List;

public abstract class Comodin {
    protected String nombre;
    protected String descripcion;
    protected Activacion activacion;
    protected int puntajeAdicional;
    protected float multiplicadorAdicional;
    protected float multiplicadorAumento;

    public Comodin(String nombre, String descripcion, String activacion, int puntajeAdicional, float multiplicadorAdicional) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.activacion = new ActivacionDescartes();//provisional
        this.puntajeAdicional = puntajeAdicional;
        this.multiplicadorAdicional = multiplicadorAdicional;
        this.multiplicadorAumento = multiplicadorAumento;
    }
    public Comodin(String nombre, String descripcion, List<Comodin> subComodines) {
        // Implementación
    }
    public Comodin(int puntajeAdicional, float multiplicadorAdicional, float multiplicadorAumento, Activacion activacion) {
        this.puntajeAdicional = puntajeAdicional;
        this.multiplicadorAdicional = multiplicadorAdicional;
        this.multiplicadorAumento = multiplicadorAumento;
        this.activacion = activacion;
    }

    public abstract void usar(Jugador jugador);

    public abstract void usar(Jugada jugada);
}
