package org.example;

public class Ronda {
    private int numero;
    private int manos;
    private int descartes;
    private int puntajeASuperar;
    private Tienda tienda;

    public Ronda(int numero, int manos, int descartes, int puntajeASuperar, Tienda tienda) {
        this.numero = numero;
        this.manos = manos;
        this.descartes = descartes;
        this.puntajeASuperar = puntajeASuperar;
        this.tienda = tienda;
    }

    public int getPuntajeASuperar(){
        return puntajeASuperar;
    }

    public Tienda obtenerTienda(){
        return tienda;
    }

    public int getDescartes(){
        return descartes;
    }

    public int getCantidadDeManos(){
        return manos;
    }

    public void setAtributosJugador(Jugador jugador){
        jugador.setCantidadDeDescartes(descartes);
        jugador.setCantidadDeManos(manos);
    }
}