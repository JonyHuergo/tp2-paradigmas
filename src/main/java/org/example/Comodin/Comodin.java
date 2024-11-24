package org.example.Comodin;

import org.example.Jugador;

public abstract class Comodin {
    protected String nombre;
    protected String descripcion;
    protected Activacion activacion;
    protected int puntajeAdicional;
    protected int multiplicadorAdicional;
    protected int multiplicadorAumento;

    public Comodin(int puntajeAdicional, int multiplicadorAdicional, int multiplicadorAumento, Activacion activacion) {
        this.puntajeAdicional = puntajeAdicional;
        this.multiplicadorAdicional = multiplicadorAdicional;
        this.multiplicadorAumento = multiplicadorAumento;
        this.activacion = activacion;
    }

    public abstract void usar(Jugador jugador);
}
