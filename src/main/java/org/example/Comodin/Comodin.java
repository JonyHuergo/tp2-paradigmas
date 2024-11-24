package org.example.Comodin;

import org.example.Jugada;
import org.example.Jugador;

public abstract class Comodin {
    protected String nombre;
    protected String descripcion;
    protected Activacion activacion;
    protected int puntajeAdicional;
    protected float multiplicadorAdicional;
    protected float multiplicadorAumento;

    public Comodin(int puntajeAdicional, float multiplicadorAdicional, float multiplicadorAumento, Activacion activacion) {
        this.puntajeAdicional = puntajeAdicional;
        this.multiplicadorAdicional = multiplicadorAdicional;
        this.multiplicadorAumento = multiplicadorAumento;
        this.activacion = activacion;
    }

    public abstract void usar(Jugador jugador);

    public abstract void usar(Jugada jugada);
}
