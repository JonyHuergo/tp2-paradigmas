package org.example;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class EvaluadorTest {

    @Test
    public void testEvaluadorEscaleraReal() {
        String valorEsperado = "Escalera Real";
        ArrayList<Carta> cartas = new ArrayList<>();
        cartas.add(new Carta("Corazones",14 )); // As
        cartas.add(new Carta("Corazones", 13)); // Rey
        cartas.add(new Carta("Corazones", 12)); // Reina
        cartas.add(new Carta("Corazones", 11)); // Jota
        cartas.add(new Carta("Corazones", 10)); // Diez

        // Crear una mano de póker
        ManoPoker mano = new ManoPoker(cartas);
        String valorObtenido = mano.obtenerNombreTipoDeMano();
        assertEquals(valorEsperado, valorObtenido);
    }

    @Test
    public void testEvaluadorPar() {
        // Valor esperado
        String valorEsperado = "Par";

        // Crear las cartas para el test
        ArrayList<Carta> cartas = new ArrayList<>();
        cartas.add(new Carta("Corazones", 5)); // 5 de Corazones
        cartas.add(new Carta("Espadas", 5));   // 5 de Espadas
        cartas.add(new Carta("Diamantes", 8)); // 8 de Diamantes
        cartas.add(new Carta("Tréboles", 11)); // J de Tréboles
        cartas.add(new Carta("Corazones", 13)); // K de Corazones

        // Crear una mano de póker
        ManoPoker mano = new ManoPoker(cartas);

        // Obtener el tipo de mano
        String valorObtenido = mano.obtenerNombreTipoDeMano();

        // Comprobar que el tipo de mano es "Par"
        assertEquals(valorEsperado, valorObtenido);
    }

    @Test
    public void testEvaluadorEscalera() {
        // Valor esperado
        String valorEsperado = "Escalera";

        // Crear las cartas para el test
        ArrayList<Carta> cartas = new ArrayList<>();
        cartas.add(new Carta("Corazones", 6));  // 6 de Corazones
        cartas.add(new Carta("Espadas", 7));    // 7 de Espadas
        cartas.add(new Carta("Diamantes", 8));  // 8 de Diamantes
        cartas.add(new Carta("Tréboles", 9));   // 9 de Tréboles
        cartas.add(new Carta("Corazones", 10)); // 10 de Corazones

        // Crear una mano de póker
        ManoPoker mano = new ManoPoker(cartas);

        // Obtener el tipo de mano
        String valorObtenido = mano.obtenerNombreTipoDeMano();

        // Comprobar que el tipo de mano es "Escalera"
        assertEquals(valorEsperado, valorObtenido);
    }

    @Test
    public void testEvaluadorCartaAlta() {
        // Valor esperado
        String valorEsperado = "Carta Alta";

        // Crear las cartas para el test
        ArrayList<Carta> cartas = new ArrayList<>();
        cartas.add(new Carta("Corazones", 1));  // 6 de Corazones
        cartas.add(new Carta("Espadas", 9));    // 7 de Espadas
        cartas.add(new Carta("Diamantes", 11));  // 8 de Diamantes
        cartas.add(new Carta("Tréboles", 3));   // 9 de Tréboles
        cartas.add(new Carta("Corazones", 2)); // 10 de Corazones

        // Crear una mano de póker
        ManoPoker mano = new ManoPoker(cartas);

        // Obtener el tipo de mano
        String valorObtenido = mano.obtenerNombreTipoDeMano();

        // Comprobar que el tipo de mano es "Escalera"
        assertEquals(valorEsperado, valorObtenido);
    }
    @Test
    public void testEvaluadorTrio() {
        String valorEsperado = "Trio";

        // Crear un conjunto de cartas para un Trío (por ejemplo, 3 cartas de valor 7 y 2 cartas de otros valores)
        ArrayList<Carta> cartas = new ArrayList<>();
        cartas.add(new Carta("Corazones", 7)); // 7
        cartas.add(new Carta("Diamantes", 7)); // 7
        cartas.add(new Carta("Tréboles", 7)); // 7
        cartas.add(new Carta("Picas", 10)); // 10
        cartas.add(new Carta("Corazones", 4)); // 4

        // Crear la mano de póker con las cartas
        ManoPoker mano = new ManoPoker(cartas);

        // Obtener el tipo de mano y verificar que sea Trío
        String valorObtenido = mano.obtenerNombreTipoDeMano();

        // Verificar si la mano ha sido clasificada correctamente como Trío
        assertEquals(valorEsperado, valorObtenido);
    }

    @Test
    public void testEvaluadorColor() {
        String valorEsperado = "Color";

        // Crear un conjunto de cartas para una mano de Color (por ejemplo, 5 cartas del mismo palo)
        ArrayList<Carta> cartas = new ArrayList<>();
        cartas.add(new Carta("Corazones", 14)); // As
        cartas.add(new Carta("Corazones", 12)); // Reina
        cartas.add(new Carta("Corazones", 10)); // Diez
        cartas.add(new Carta("Corazones", 8));  // Ocho
        cartas.add(new Carta("Corazones", 6));  // Seis

        // Crear la mano de póker con las cartas
        ManoPoker mano = new ManoPoker(cartas);

        // Obtener el tipo de mano y verificar que sea Color
        String valorObtenido = mano.obtenerNombreTipoDeMano();

        // Verificar si la mano ha sido clasificada correctamente como Color
        assertEquals(valorEsperado, valorObtenido);
    }

    @Test
    public void testEvaluadorDoblePar() {
        String valorEsperado = "Doble Par";

        // Crear un conjunto de cartas para un Doble Par (por ejemplo, dos 9s, dos 5s y una carta de otro valor)
        ArrayList<Carta> cartas = new ArrayList<>();
        cartas.add(new Carta("Corazones", 9)); // 9
        cartas.add(new Carta("Diamantes", 9)); // 9
        cartas.add(new Carta("Tréboles", 5)); // 5
        cartas.add(new Carta("Picas", 5)); // 5
        cartas.add(new Carta("Corazones", 2)); // 2

        // Crear la mano de póker con las cartas
        ManoPoker mano = new ManoPoker(cartas);

        // Obtener el tipo de mano y verificar que sea Doble Par
        String valorObtenido = mano.obtenerNombreTipoDeMano();

        // Verificar si la mano ha sido clasificada correctamente como Doble Par
        assertEquals(valorEsperado, valorObtenido);
    }

    @Test
    public void testEvaluadorFull() {
        String valorEsperado = "Full";

        // Crear un conjunto de cartas para una mano de Full (por ejemplo, un trío de 7 y un par de 10)
        ArrayList<Carta> cartas = new ArrayList<>();
        cartas.add(new Carta("Corazones", 7));  // 7
        cartas.add(new Carta("Diamantes", 7));  // 7
        cartas.add(new Carta("Trébol", 7));     // 7
        cartas.add(new Carta("Corazones", 10)); // 10
        cartas.add(new Carta("Diamantes", 10)); // 10

        // Crear la mano de póker con las cartas
        ManoPoker mano = new ManoPoker(cartas);

        // Obtener el tipo de mano y verificar que sea Full
        String valorObtenido = mano.obtenerNombreTipoDeMano();

        // Verificar si la mano ha sido clasificada correctamente como Full
        assertEquals(valorEsperado, valorObtenido);
    }

    @Test
    public void testEvaluadorPoker() {
        String valorEsperado = "Poker";

        // Crear un conjunto de cartas para una mano de Poker (por ejemplo, cuatro reyes)
        ArrayList<Carta> cartas = new ArrayList<>();
        cartas.add(new Carta("Corazones", 13));  // Rey
        cartas.add(new Carta("Diamantes", 13));  // Rey
        cartas.add(new Carta("Trébol", 13));     // Rey
        cartas.add(new Carta("Espadas", 13));    // Rey
        cartas.add(new Carta("Corazones", 5));   // Carta adicional (no afecta el Poker)

        // Crear la mano de póker con las cartas
        ManoPoker mano = new ManoPoker(cartas);

        // Obtener el tipo de mano y verificar que sea Poker
        String valorObtenido = mano.obtenerNombreTipoDeMano();

        // Verificar si la mano ha sido clasificada correctamente como Poker
        assertEquals(valorEsperado, valorObtenido);
    }

    @Test
    public void testEvaluadorEscaleraColor() {
        String valorEsperado = "Escalera Color";

        // Crear un conjunto de cartas para una mano de Escalera Color (por ejemplo, 5, 6, 7, 8, 9 de corazones)
        ArrayList<Carta> cartas = new ArrayList<>();
        cartas.add(new Carta("Corazones", 5));  // 5
        cartas.add(new Carta("Corazones", 6));  // 6
        cartas.add(new Carta("Corazones", 7));  // 7
        cartas.add(new Carta("Corazones", 8));  // 8
        cartas.add(new Carta("Corazones", 9));  // 9

        // Crear la mano de póker con las cartas
        ManoPoker mano = new ManoPoker(cartas);

        // Obtener el tipo de mano y verificar que sea Escalera Color
        String valorObtenido = mano.obtenerNombreTipoDeMano();

        // Verificar si la mano ha sido clasificada correctamente como Escalera Color
        assertEquals(valorEsperado, valorObtenido);
    }

}