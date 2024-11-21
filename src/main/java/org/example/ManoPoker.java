package org.example;

import org.example.Manos.Mano;

import java.util.ArrayList;

public class ManoPoker {
    private ArrayList<Carta> cartas = new ArrayList<Carta>();
    private PuntuacionPorMano puntuacionPorMano = new PuntuacionPorMano();
    private AnalizadorMano analizadorMano;
    private Mano tipoDeMano;

    public ManoPoker() {
        this.analizadorMano = new AnalizadorMano();

    }
    public ManoPoker(ArrayList<Carta> cartas){
        this.cartas = cartas;
        this.tipoDeMano = calcularMano();

    }

    public void agregarCarta(Carta carta) {
        cartas.add(carta);
    }

    public void removerCarta(Carta carta) {
        cartas.remove(carta);
    }

    public Puntaje evaluar(){
        return (puntuacionPorMano.calcular(cartas));
    }

    public String obtenerNombreTipoDeMano() {
        return analizadorMano.analizarMano(cartas).getNombre();
    }

    public Mano calcularMano(){
        return analizadorMano.analizarMano(cartas);
    }
}
