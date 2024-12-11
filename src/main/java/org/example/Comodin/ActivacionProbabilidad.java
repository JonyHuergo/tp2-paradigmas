package org.example.Comodin;
import org.example.Jugada;
import org.example.Jugador;

import java.util.Random;

public class ActivacionProbabilidad implements Activacion {
    private int probabilidad;
    Random random = new Random();

    public ActivacionProbabilidad(String probabilidad) {
        this.probabilidad = determinarProbabilidad(probabilidad);
    }

    private int determinarProbabilidad( String activacion){
        activacion = activacion.replaceAll("[{}]", "");
        String[] partes = activacion.split(":");
        String parteProbabilidad = partes[1].trim();
        int probabilidad = Integer.parseInt(parteProbabilidad);
        return probabilidad;
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
