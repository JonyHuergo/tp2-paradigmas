package org.example.Evaluadores;
import org.example.Carta;
import java.util.ArrayList;
import org.example.Manos.Mano;

public interface EvaluadorMano {
    Mano evaluar(ArrayList<Carta> cartas); // Devuelve el tipo de mano o null si no aplica
    ArrayList<Carta> cartasDeLaMano(ArrayList<Carta> cartas);
}
