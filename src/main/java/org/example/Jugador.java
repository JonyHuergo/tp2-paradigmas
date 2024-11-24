package org.example;

import org.example.Comodin.Comodin;

import java.util.ArrayList;

public class Jugador {
    private Mazo mazo;
    private String nombre;
    private ArrayList<Carta> mano = new ArrayList<>();  // cambiar debido al problema con new
//    private ArrayList<Carta> manoElegida = new ArrayList<>(); // cambiar debido al problema con new
    private ManoPoker manoPoker;
    private ArrayList<Comodin> comodines;
    private int puntaje;
    private int descartes = 3;
    private int jugadas = 5;
    private Jugada jugadaActual;
    private ArrayList<Jugada> listadoJugadas = new ArrayList<>();

    public Jugador(){
        mazo = new Mazo();
        puntaje = 0;
    }

    public Jugador(Mazo mazo){
        this.mazo = mazo;
        puntaje = 0;
    }

//    public Jugador(Mazo mazo, ArrayList<Carta> mano, ArrayList<Carta> manoElegida){
//        this.mazo = mazo;
//        this.mano = mano;
////        this.manoElegida = manoElegida;
//        puntaje = 0;
//    }

    public Jugador(Mazo mazo, ArrayList<Carta> mano, ManoPoker manoPoker, ArrayList<Comodin> comodines){
        this.mazo = mazo;
        this.mano = mano;
        this.manoPoker = manoPoker;
        puntaje = 0;
        this.comodines = comodines;
    }

    public void repartirCartas(int cantidad) {
        mano = mazo.repartir(cantidad);
    }

    public int cantidadDeCartasEnMano(){
        return mano.size();
    }

    public void evaluarMano(){
        manoPoker.definirTipodeMano();
        manoPoker.sumarValorCartas();
        for (Comodin comodin : comodines) {
            comodin.usar(this);
        }
        puntaje += manoPoker.hacerCalculo();
    }

    public boolean puntajeEsIgual(int puntaje){
        return(this.puntaje == puntaje);
    }

    public void actualizarPuntajeBase(int puntajeBase){
        manoPoker.actualizarPuntajeBase(puntajeBase);
    }

    public void actualizarMult(int mult){
        manoPoker.actualizarMultiplicadorBase(mult);
    }

    public void multiplicarMult(int mult){
        manoPoker.multiplicarMultiplicadorBase(mult);
    }

    public void setManoPoker(ManoPoker manoPoker) {
        this.manoPoker = manoPoker;
    }

    public void setComodines(ArrayList<Comodin> comodines) {
        this.comodines = comodines;
    }

    public boolean tieneManoDeTipo(String manoEsperada){
        return(manoPoker.manoNombreEsIgual(manoEsperada));
    }

    public boolean tieneDescartes(){
        return(descartes>0);
    }

    public int calcularPuntosPorDescarte(int puntosPorDescarte) {
        return(puntosPorDescarte*descartes);
    }

    public void elegirCartas(ArrayList<Integer> posicionesCartas){ // Esto es raro
//        for (int i = 0; i < posicionesCartas.size(); i++) {
//            manoElegida.add(mano.get(posicionesCartas.get(i)));
//        }
//        manoPoker = new ManoPoker(manoElegida);
    }


    public void jugar(){
        this.jugadaActual = new Jugada(manoPoker, comodines, descartes);
        listadoJugadas.add(jugadaActual);
        jugadas = jugadas - 1;
    }
}
