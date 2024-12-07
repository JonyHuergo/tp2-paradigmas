package org.example;

import org.example.Comodin.Comodin;
import org.example.Tarot.Tarot;

import java.util.ArrayList;

public class Jugada { // esta clase deberia remplazar al acumulador de puntos en jugador, se guardan las jugadas y cada una hace el calculo
    private ManoPoker manoPoker;
    private ArrayList<Comodin> comodines;
    private int puntaje;
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

    public float evaluarJugada(){
        manoPoker.definirTipodeMano();
        manoPoker.sumarValorCartas();
        for (Tarot tarot: tarotsUsados){
            tarot.aplicarEfecto(this.manoPoker);
        }
        for (Comodin comodin : comodines) {
            comodin.usar(this);
        }
        return(manoPoker.hacerCalculo());
    }
}
