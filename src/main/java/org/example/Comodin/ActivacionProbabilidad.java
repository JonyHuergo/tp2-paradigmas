package org.example.Comodin;
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
        int numero = random.nextInt(probabilidad);

        return numero == 0;
    }
}
