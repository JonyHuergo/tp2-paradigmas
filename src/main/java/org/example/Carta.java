package org.example;

import java.util.Objects;

public class Carta {
    private String palo;
    private int valor;
    private int puntaje;
    private int multiplicador;

    public Carta(String palo, int valor) {
        this.palo = palo;
        this.valor = valor;
        this.multiplicador = 0;
        this.puntaje = valor;
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

    public void agregarPuntos(int puntos) {
        puntaje = this.puntaje + puntos;
    }

    public int actualizarPuntajeTotal(int puntajeTotal) {
        return puntajeTotal + this.puntaje;
    }

    public int actualizarMultiplicadorTotal(int multiplicadorTotal) {
        return multiplicadorTotal + multiplicador;
    }
}
