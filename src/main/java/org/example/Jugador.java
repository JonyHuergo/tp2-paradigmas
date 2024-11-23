package org.example;

import org.example.Comodin.Comodin;

import java.util.ArrayList;

public class Jugador {
    private Mazo mazo;
    private String nombre;
    private ArrayList<Carta> mano = new ArrayList<>();  // cambiar debido al problema con new
    private ArrayList<Carta> manoElegida = new ArrayList<>(); // cambiar debido al problema con new
    private ManoPoker manoPoker;
    private ArrayList<Comodin> comodines;
    private int puntaje;

    public Jugador(){
        mazo = new Mazo();
        puntaje = 0;
    }

    public Jugador(Mazo mazo){
        this.mazo = mazo;
        puntaje = 0;
    }

    public Jugador(Mazo mazo, ArrayList<Carta> mano, ArrayList<Carta> manoElegida){
        this.mazo = mazo;
        this.mano = mano;
        this.manoElegida = manoElegida;
        puntaje = 0;
    }

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

    public void actualizarMult(int mult){
        manoPoker.actualizarMultiplicadorBase(mult);
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

        public void elegirCartas(ArrayList<Integer> posicionesCartas){ // Esto es raro
        for (int i = 0; i < posicionesCartas.size(); i++) {
            manoElegida.add(mano.get(posicionesCartas.get(i)));
        }
        manoPoker = new ManoPoker(manoElegida);
    }


    public void jugar(ManoPoker manoJugada){
        /*
        puntaje = puntaje.sumarCon(manoJugada.evaluar());
        multiplicador = manoJugada.getMultiplicadorBase();
        puntaje = puntaje.sumarCon(manoJugada.calcularConModificadores());
        multiplicador =

        Ronda.pasarTurno();
        */
    }
}
