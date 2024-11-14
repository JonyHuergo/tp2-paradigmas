package org.example;

import java.util.Objects;

public class Carta {
    private String palo;
    private int valor;
    private Puntaje puntaje;
    private int multiplicador;

    public Carta(String palo, int valor) {
        this.palo = palo;
        this.valor = valor;
        this.multiplicador = 0;
        puntaje = new Puntaje(valor);
    }

    public int getValor(){
        return this.valor;
    }

    public String getPalo(){
        return this.palo;
    }

    public boolean cartaTieneMismoValor(Carta carta){
        return carta.valorEsIgual(this.valor);
    }

    private boolean valorEsIgual(int valorCarta){
        return valorCarta == valor;
    }

    public boolean paloEsIgual(String paloCarta){
        return Objects.equals(paloCarta, palo);
    }

    public boolean paloEsIgual(Carta carta){
        return carta.paloEsIgual(this.palo);
        //return this.paloEsIgual(carta.getPalo());
    }

    public boolean esInmediatamenteSuperior(Carta carta){
        return (carta.valorEsIgual(this.valor + 1));
    }

    public boolean esInmediatamenteInferior(Carta carta){
        return (carta.valorEsIgual(this.valor - 1));
    }

    public void modificarMultiplicador(int multiplicador){
        this.multiplicador = multiplicador;
    }

    public void agregarPuntos(Puntaje puntos) {
        puntaje = this.puntaje.sumarCon(puntos);
    }

    public int actualizarPuntajeTotal(int puntajeTotal) {
        return puntajeTotal + puntaje.getValor();
    }

    public int actualizarMultiplicadorTotal(int multiplicadorTotal) {
        return multiplicadorTotal + multiplicador;
    }
}
