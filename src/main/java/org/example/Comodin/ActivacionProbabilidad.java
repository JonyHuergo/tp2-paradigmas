package org.example.Comodin;
import org.example.Jugada;
import org.example.Jugador;

import java.util.Random;

public class ActivacionProbabilidad implements Activacion {
    private int probabilidad;
    Random random = new Random();

    public ActivacionProbabilidad(int probabilidad) {
        this.probabilidad = probabilidad;
    }

    @Override
    public boolean revisarCondicion(Jugador jugador) {
        int aux = random.nextInt(probabilidad);

        return aux == 0;
    }

    @Override
    public boolean revisarCondicion(Jugada jugada){
        int aux = random.nextInt(probabilidad);

        return aux == 0;
    }
}
