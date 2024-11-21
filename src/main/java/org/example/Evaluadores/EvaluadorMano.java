package org.example.Evaluadores;
import org.example.Carta;
import java.util.ArrayList;

public interface EvaluadorMano {
    String evaluar(ArrayList<Carta> cartas); // Devuelve el tipo de mano o null si no aplica

}
