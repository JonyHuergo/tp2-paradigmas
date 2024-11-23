package org.example.Comodin;

import org.example.Jugador;

public class Comodin {
    private String nombre;
    private String descripcion;
    private Activacion activacion;
    private int puntajeAdicional;
    private int multiplicadorAdicional;
    private int multiplicadorAumento;

    public Comodin(int puntajeAdicional, int multiplicadorAdicional, int multiplicadorAumento) {
        this.puntajeAdicional = puntajeAdicional;
        this.multiplicadorAdicional = multiplicadorAdicional;
        this.multiplicadorAumento = multiplicadorAumento;
        this.activacion = new ActivacionSiempre();
    }

    public void usar(Jugador jugador){
        if (activacion.revisarCondicion(jugador)){
            jugador.actualizarMult(multiplicadorAdicional);
        }
    }

    public int actualizarPuntajeTotal(int puntajeTotal) {
        return puntajeTotal + this.puntajeAdicional;
    }

    public int actualizarMultiplicadorTotal(int multiplicadorTotal) {
        return multiplicadorTotal + multiplicadorAdicional;
    }

    public int aumentaMultiplicadorTotal(int multiplicadorTotal) {
        return multiplicadorTotal * multiplicadorAumento;
    }

}
