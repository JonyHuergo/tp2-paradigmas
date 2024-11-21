package org.example;

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
//        this.tipoDeMano = calcularMano();
        this.analizadorMano = new AnalizadorMano();

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
//        return (puntuacionPorMano.calcular(cartas, tipoDeMano));
    }

    public String obtenerNombreTipoDeMano() {
        return analizadorMano.analizarMano(cartas).getNombre();
    }

    public Mano calcularMano(){
        return analizadorMano.analizarMano(cartas);
    }
}
