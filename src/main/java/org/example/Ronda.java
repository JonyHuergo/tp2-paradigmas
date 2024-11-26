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

    public void pasarTurno(int puntajeTotal){
        this.manos -= 1;
        if(puntajeASuperar <= puntajeTotal);
            System.out.println("siguiente ronda!");
        if(manos <= 0){
            System.out.println("perdiste!");
        }
    }

    public Tienda obtenerTienda(){
        return tienda;
    }
}