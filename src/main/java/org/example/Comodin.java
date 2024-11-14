package org.example;

public class Comodin {
    private Puntaje puntajeAdicional;
    private Puntaje multiplicadorAdicional;

    public Comodin(int puntajeAdicional, int multiplicadorAdicional) {
        this.puntajeAdicional = new Puntaje(puntajeAdicional);
        this.multiplicadorAdicional = new Puntaje(multiplicadorAdicional);
    }

    public Puntaje aplicarModificacionPuntaje(Puntaje puntaje){
        return puntaje.sumarCon(puntajeAdicional);
    }

    public Puntaje aplicarModificacionMultiplicador(Puntaje puntaje){
        return puntaje.multiplicarCon(multiplicadorAdicional);
    }

}
