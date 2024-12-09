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

    public ManoPoker(ArrayList<Carta> cartas) {
        this.cartas = cartas;
        this.analizadorMano = new AnalizadorMano(); // fijarse el new
    }

    public void agregarCarta(Carta carta) {
        cartas.add(carta);
    }

    public void removerCarta(Carta carta) {
        cartas.remove(carta);
    }

    public float evaluar() {
        tipoDeMano = calcularMano();
        return (tipoDeMano.calcular(cartas));
    }

    public void definirTipodeMano() {
        tipoDeMano = calcularMano();
    }

    public void sumarValorCartas() {
        tipoDeMano.sumarValorCartas(cartas);
    }

    public float hacerCalculo() {
        return tipoDeMano.hacerCalculo();
    }


    public boolean manoNombreEsIgual(String nombre) {
        return (tipoDeMano.nombreEsIgual(nombre));
    }


    public Mano calcularMano() {
        return analizadorMano.analizarMano(cartas);
    }

    public void actualizarPuntajeBase(int puntaje) {
        tipoDeMano.actualizarPuntajeBase(puntaje);
    }

    public void actualizarMultiplicadorBase(float mult) {
        tipoDeMano.actualizarMultiplicadorBase(mult);
    }

    public void multiplicarMultiplicadorBase(float mult) {
        tipoDeMano.multiplicarMultiplicadorBase(mult);
    }

    public void mejorarCarta(Carta carta, int puntos, float multiplicador) {
        for (Carta c : cartas) {
            if (c.nombreEsIgual(carta)) {
                if (puntos > 1){
                    c.agregarPuntos(puntos);
                }
                if(multiplicador > 1){
                    c.modificarMultiplicador(multiplicador);
                }
            }
        }
    }

    public ManoPoker clonar(){
        ArrayList<Carta> clon = new ArrayList<>();
        for (Carta carta : cartas){
            clon.add(carta.clonar());
        }
        return new ManoPoker(clon);
//       return new ManoPoker((ArrayList)cartas.clone());
    }

    public void descartarCartas(ArrayList<Carta> cartasDescartadas) {
        for (Carta carta : cartasDescartadas) {
            removerCarta(carta);
        }
    }

    public boolean tieneCarta(Carta carta) {
        return cartas.contains(carta);
    }

    public int getCantidadDeCartas(){
        return cartas.size();
    }

    public ArrayList<Carta> getCartas(){
        return cartas;
    }

}
