package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CasosDeUsoTest {
    @Test
    public void test01TieneCartasSuficientes() {
        Mazo mazo = new Mazo();
        int cartasEsperadas = 52;

        int cartasObtenidas = mazo.cantidadDeCartas();

        assertEquals(cartasObtenidas, cartasEsperadas);
    }

    @Test
    public void test02RepartirCartas() {
        Jugador jugador = new Jugador();
        int cartasEsperadasJugador = 8;

        int cartasObtenidasJugador = jugador.repartirCartas(8);

        assertEquals(cartasEsperadasJugador, cartasObtenidasJugador, "El jugador debería tener 8 cartas en su mano después de repartir.");
    }

    @Test
    public void test04EvaluarMano(){
        int puntajeEsperado = 920; // (100 por mano (escalera de color) + 15 por las cartas) * 8 por mano
        ManoPoker manoJugada = new ManoPoker();
        ManoComodines manoComodines = new ManoComodines(); // mano comodines vacia? como probamos el metodo sin comodines?

        for (int i = 1; i <= 5; i++) {
            Carta carta = new Carta("diamantes", i);
            manoJugada.agregarCarta(carta);
        }

        int puntajeObtenido = manoJugada.evaluar(manoComodines);
        assertEquals(puntajeEsperado, puntajeObtenido);
    }

    @Test
    public void test03JugarMano(){
        //work in progress
    }

    @Test
    public void test05EvaluarManoConComodin(){
        ManoPoker manoJugada = new ManoPoker();

        for (int i = 1; i <= 5; i++) {
            Carta c1 = new Carta("diamantes", i);
            manoJugada.agregarCarta(c1);
        }
        ArrayList<Comodin> comodines = new ArrayList<Comodin>();
        comodines.add(new Comodin(10,2));
        comodines.add(new Comodin(10,5));
        ManoComodines mano1Comodines = new ManoComodines(comodines);

        ArrayList<Comodin> comodines2 = new ArrayList<Comodin>();
        comodines2.add(new Comodin(10,5));
        comodines2.add(new Comodin(10,2));
        ManoComodines mano2Comodines = new ManoComodines(comodines2);

        int puntajeObtenido1 = mano1Comodines.aplicarComodines(manoJugada);
        int puntajeObtenido2 = mano2Comodines.aplicarComodines(manoJugada);

        assertFalse(puntajeObtenido1, puntajeObtenido2);
    }

    @Test
    public void test06TarotAgregar10PuntosModificaPuntosCorrectamente() {
        int puntajeEsperado = 19; // (5 por mano (carta alta) + 4 por la carta + 10 por tarot) * 1 por mano
        Carta carta = new Carta("diamantes", 4);
        TarotAgregar10Puntos tarot = new TarotAgregar10Puntos("Agregador10pts", "Agrega10pts", new Efecto(10, 1), "cualquiera", "cualquiera");
        ManoPoker manoJugada = new ManoPoker();
        ManoComodines manoComodines = new ManoComodines(); // mano comodines vacia? como probamos el metodo sin comodines?


        tarot.aplicarEfecto(carta);
        manoJugada.agregarCarta(carta);
        int puntajeObtenido = manoJugada.evaluar(manoComodines);

        assertEquals(puntajeEsperado, puntajeObtenido, "El puntaje obtenido debería ser 19.");
    }

    @Test
    public void test07TarotMultiplicadorX6ModificaPuntosCorrectamente(){
        int puntajeEsperado = 63; // (5 por mano (carta alta) + 4 por la carta) * (1 por mano (carta alta) + 6 por tarot)
        Carta carta = new Carta("diamantes", 4);
        TarotMultiplicadorX6 tarot = new TarotMultiplicadorX6("MultiplicadorX6", "multiplicador añade 6pts", new Efecto(1, 6), "cualquiera", "cualquiera");
        ManoPoker manoJugada = new ManoPoker();
        ManoComodines manoComodines = new ManoComodines(); // mano comodines vacia? como probamos el metodo sin comodines?


        tarot.aplicarEfecto(carta);
        manoJugada.agregarCarta(carta);
        int puntajeObtenido = manoJugada.evaluar(manoComodines);

        assertEquals(puntajeEsperado, puntajeObtenido, "El puntaje obtenido debería ser 63.");
    }

    /* Verificar que al tener un comodín que sume 8 al multiplicador se aplique correctamente
    Verificar que el jugador recibe un aumento correspondiente si tiene el comodín que aumenta el multiplicador por 3 si juega una escalera
    Verificar que el jugador si posee un comodin que suma 10 puntos por descarte, al descartar sume la cantidad correcta.
    Verificar que si el jugador posee un comodin que tiene chance 1 sobre 1000 de romperse se rompa correctamente.
    El jugador activa un comodín con una combinación de efectos  bonus de mano jugada + puntaje aumentado + activación aleatoria
    Verificar la lectura y posterior conversión a unidades del modelo de dominio del JSON
    Planteo inicial de interfaz gráfica (mockups/dibujos), pantalla donde se muestra una ronda
    */

    //Ni mire lo que sigue, pero lo dejo
   /* @Test
    public void test08ComodinSuma8AlMultiplicador() {
        Puntaje puntajeEsperado = new Puntaje(72); // (5 por mano (carta alta) + 4 por la carta) * (1 por mano (carta alta) + 8 por comodín)
        Carta carta = new Carta("diamantes", 4);
        ComodinMultiplicador comodin = new ComodinMultiplicador("ComodinSuma8", "multiplicador añade 8pts", new Efecto(1, 8), "cualquiera", "cualquiera");
        ManoPoker manoJugada = new ManoPoker();

        comodin.aplicarEfecto(carta);
        manoJugada.agregarCarta(carta);
        Puntaje puntajeObtenido = manoJugada.evaluar();

        assertEquals(puntajeEsperado, puntajeObtenido, "El puntaje obtenido debería ser 72.");
    }

    @Test
    public void test09ComodinMultiplicadorPor3ConEscalera() {
        Puntaje puntajeEsperado = new Puntaje(45); // (5 por mano (escalera) * 3 por comodín)
        ManoPoker manoJugada = new ManoPoker();
        ComodinMultiplicador comodin = new ComodinMultiplicador("ComodinMultiplicadorX3", "multiplicador por 3 si escalera", new Efecto(3, 1), "escalera", "cualquiera");

        manoJugada.jugarEscalera();
        comodin.aplicarEfecto(manoJugada);
        Puntaje puntajeObtenido = manoJugada.evaluar();

        assertEquals(puntajeEsperado, puntajeObtenido, "El puntaje obtenido debería ser 45.");
    }

    @Test
    public void test10ComodinSuma10PuntosPorDescarte() {
        Puntaje puntajeEsperado = new Puntaje(10); // 10 puntos por descarte
        ComodinPuntos comodin = new ComodinPuntos("ComodinSuma10", "suma 10 puntos por descarte", new Efecto(10, 0), "descarte", "cualquiera");
        ManoPoker manoJugada = new ManoPoker();

        manoJugada.descartar();
        comodin.aplicarEfecto(manoJugada);
        Puntaje puntajeObtenido = manoJugada.evaluar();

        assertEquals(puntajeEsperado, puntajeObtenido, "El puntaje obtenido debería ser 10.");
    }

    @Test
    public void test11ComodinChanceDeRomperse() {
        ComodinRomperse comodin = new ComodinRomperse("ComodinRomperse", "chance 1 sobre 1000 de romperse", new Efecto(0, 0), 0.001);
        boolean seRompio = comodin.intentarRomperse();

        assertTrue(seRompio, "El comodín debería haberse roto.");
    }

    @Test
    public void test12ComodinConCombinacionDeEfectos() {
        Puntaje puntajeEsperado = new Puntaje(20); // 5 por mano + 10 por puntaje aumentado + 5 por activación aleatoria
        ComodinCombinado comodin = new ComodinCombinado("ComodinCombinado", "bonus de mano jugada + puntaje aumentado + activación aleatoria", new Efecto(5, 10, 5), "cualquiera", "cualquiera");
        ManoPoker manoJugada = new ManoPoker();

        comodin.aplicarEfecto(manoJugada);
        Puntaje puntajeObtenido = manoJugada.evaluar();

        assertEquals(puntajeEsperado, puntajeObtenido, "El puntaje obtenido debería ser 20.");
    }

    @Test
    public void test13LecturaYConversionDeJSON() {
        String json = "{\"efecto\":{\"puntos\":10,\"multiplicador\":2}}";
        Efecto efecto = Efecto.fromJson(json);

        assertNotNull(efecto, "El efecto no debería ser nulo.");
        assertEquals(10, efecto.getPuntos(), "Los puntos deberían ser 10.");
        assertEquals(2, efecto.getMultiplicador(), "El multiplicador debería ser 2.");
    }*/
}
