package org.example;

import org.example.Comodin.Comodin;
import org.example.Manos.Mano;

import java.util.ArrayList;

public class ManoPoker {
    private ArrayList<Carta> cartas = new ArrayList<Carta>();
    private AnalizadorMano analizadorMano;
    private Mano tipoDeMano;

    public ManoPoker() {
        this.analizadorMano = new AnalizadorMano();

    }
    public ManoPoker(ArrayList<Carta> cartas){
        this.cartas = cartas;
        this.analizadorMano = new AnalizadorMano(); // fijarse el new
    }

    public void agregarCarta(Carta carta) {
        cartas.add(carta);
    }

    public void removerCarta(Carta carta) {
        cartas.remove(carta);
    }

    public int evaluar(){
        tipoDeMano = calcularMano();
        return(tipoDeMano.calcular(cartas));
    }

    public void definirTipodeMano(){
        tipoDeMano = calcularMano();
    }

    public void sumarValorCartas(){
        tipoDeMano.sumarValorCartas(cartas);
    }

    public int hacerCalculo(){
        return tipoDeMano.hacerCalculo();
    }

//    public String obtenerNombreTipoDeMano() {
////        return analizadorMano.analizarMano(cartas).getNombre(); // no esta bien el getter ess (getter eradicator)
////        return analizadorMano.calcularNombre(cartas);
//    }

    public boolean manoNombreEsIgual(String nombre){
        return(tipoDeMano.nombreEsIgual(nombre));
    }


    public Mano calcularMano(){
        return analizadorMano.analizarMano(cartas);
    }

    public void actualizarPuntajeBase(int puntaje) {
        tipoDeMano.actualizarPuntajeBase(puntaje);
    }

    public void actualizarMultiplicadorBase(int mult) {
        tipoDeMano.actualizarMultiplicadorBase(mult);
    }

    public void multiplicarMultiplicadorBase(int mult) {
        tipoDeMano.multiplicarMultiplicadorBase(mult);
    }
}
