package org.example;

import org.example.Evaluadores.*;

import java.util.ArrayList;

public class AnalizadorMano {
    private EvaluadorMano evaluadorInicial;

    public AnalizadorMano() {

        EvaluadorEscaleraReal escaleraReal = new EvaluadorEscaleraReal();
        EvaluadorEscaleraColor escaleraColor = new EvaluadorEscaleraColor();
        EvaluadorPoker poker = new EvaluadorPoker();
        EvaluadorFull full = new EvaluadorFull();
        EvaluadorColor color = new EvaluadorColor();
        EvaluadorEscalera escalera = new EvaluadorEscalera();
        EvaluadorTrio trio = new EvaluadorTrio();
        EvaluadorDoblePar doblePar = new EvaluadorDoblePar();
        EvaluadorPar par = new EvaluadorPar();

        // Configuración de la cadena
        escaleraReal.setSiguiente(escaleraColor);
        escaleraColor.setSiguiente(poker);
        poker.setSiguiente(full);
        full.setSiguiente(color);
        color.setSiguiente(escalera);
        escalera.setSiguiente(trio);
        trio.setSiguiente(doblePar);
        doblePar.setSiguiente(par);
        this.evaluadorInicial = escaleraReal;
    }

    public String analizarMano(ArrayList<Carta> cartas) {
        return evaluadorInicial.evaluar(cartas);
    }
}