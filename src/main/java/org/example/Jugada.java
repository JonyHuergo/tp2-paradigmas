package org.example;

import org.example.Comodin.Comodin;
import org.example.Tarot.Tarot;

import java.util.ArrayList;

public class Jugada {
    private ManoPoker manoPoker;
    private ArrayList<Comodin> comodines;
    private int descartes;
    private int numeroRonda;
    private ArrayList<Tarot> tarotsUsados;


    public Jugada(ManoPoker manoPoker, ArrayList<Comodin> comodines, int descartes){
        this.manoPoker = manoPoker.clonar();
        this.comodines = comodines;
        this.descartes = descartes;
        this.tarotsUsados = new ArrayList<>();
    }

    public Jugada(ManoPoker manoPoker, ArrayList<Comodin> comodines, int descartes, ArrayList<Tarot> tarotsUsados){
        this.manoPoker = manoPoker.clonar();
        this.comodines = comodines;
        this.descartes = descartes;
        this.tarotsUsados = tarotsUsados;
    }

    public Jugada(ManoPoker manoPoker, ArrayList<Comodin> comodines, int descartes, ArrayList<Tarot> tarotsUsados, int numeroRonda){
        this.manoPoker = manoPoker.clonar();
        this.comodines = comodines;
        this.descartes = descartes;
        this.tarotsUsados = tarotsUsados;
        this.numeroRonda = numeroRonda;
    }

    public void actualizarPuntajeBase(int puntajeBase){
        manoPoker.actualizarPuntajeBase(puntajeBase);
    }

    public void actualizarMult(float mult){
        manoPoker.actualizarMultiplicadorBase(mult);
    }

    public void multiplicarMult(float mult){
        manoPoker.multiplicarMultiplicadorBase(mult);
    }

    public boolean tieneDescartes(){return descartes > 0;}

    public boolean tieneManoDeTipo(String manoEsperada){
        return(manoPoker.manoNombreEsIgual(manoEsperada));
    }

    public int calcularPuntosPorDescarte(int puntosPorDescarte){
        return(descartes * puntosPorDescarte);
    }

    public float calcularPuntosPorDescarte(float puntosPorDescarte){
        return(descartes * puntosPorDescarte);
    }

    public float evaluarJugada(){
        manoPoker.definirTipodeMano();
        manoPoker.calcularCartasRelevantes();

        for (Tarot tarot: tarotsUsados){
            tarot.aplicarEfecto(this.manoPoker);
        }
        for (Comodin comodin : comodines) {
            comodin.usar(this);
        }
        manoPoker.sumarValorCartas();
        return(manoPoker.hacerCalculo());
    }

    public boolean tieneNumeroRonda(int numeroOtraRonda) {
        return (numeroRonda == numeroOtraRonda);
    }
}