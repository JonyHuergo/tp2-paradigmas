package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Juego {
    private Jugador jugador;
    private List<Ronda> rondas;
    private boolean rondaGanada = false;

    public Juego(Jugador jugador) throws IOException {
        this.jugador = jugador;
        LectorArchivosJson lector = new LectorArchivosJson();
        this.rondas = lector.leerBalatro();
    }
    public void jugar(){
        for (Ronda ronda : rondas) {
            //ronda.iniciarTienda(); falta implemetar
            while (!rondaGanada) {
                jugador.repartirCartas();
                Scanner scanner = new Scanner(System.in);

                for (int i = 0; i < 5; i++){
                    ArrayList<Carta> cartasDisponibles = jugador.getCartasDisponibles();
                    for (int j = 0; cartasDisponibles.size() > j; j++) {
                        System.out.println("carta:" + j + " valor: " + cartasDisponibles.get(j).getValor() + " palo:" + cartasDisponibles.get(j).getPalo());
                    }
                    int cartaElegida = scanner.nextInt();
                    if (cartaElegida == 9){ break; }//ingresar 9 para dejar de elegir cartas
                    jugador.elegirCarta(cartaElegida);
                }
                jugador.crearJugada();
                float puntajeTotal = jugador.evaluarJugadas();
                System.out.println();
                System.out.println("puntaje actual: " + jugador.evaluarJugadaActual());
                System.out.println("puntaje total: " + puntajeTotal);
                System.out.println("puntaje a superar: " + ronda.getPuntajeASuperar());
                if (ronda.puntajeSuperoElLimite(puntajeTotal)){
                    rondaGanada = true;
                }
                ronda.pasarTurno();
            }
            System.out.println("SIGUIENTE RONDA");
        }
        System.out.println("GANASTE");
    }
}
