package org.example;

import org.example.Comodin.Comodin;
import org.example.Comodin.ComodinPorDescarte;
import org.example.Tarot.Tarot;
import org.example.Tarot.TarotSobreMano;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LectorArchivosJsonTest {

    @Test
    public void testJuegoConLectorMockeado() throws IOException {
        // Crear mock del lector
        LectorArchivosJson lectorMock = mock(LectorArchivosJson.class);

        // Crear datos mockeados
        // Mockear cartas del mazo
        ArrayList<Carta> mazoMockeado = new ArrayList<>();
        mazoMockeado.add(new Carta("corazones", 10));
        mazoMockeado.add(new Carta("trebol", 4));
        mazoMockeado.add(new Carta("picas", 8));
        mazoMockeado.add(new Carta("diamantes", 6));
        mazoMockeado.add(new Carta("corazones", 2));

        // Mockear rondas
        List<Ronda> rondasMockeadas = new ArrayList<>();

        // Mockear Comodines y Tarots de la tienda
        List<Comodin> comodines = new ArrayList<>();
        comodines.add(new ComodinPorDescarte(50, 1, "par"));
        comodines.add(new ComodinPorDescarte(50, 1, "par"));

        List<Tarot> tarots = new ArrayList<>();
        tarots.add(new TarotSobreMano("Fuerza", "Mejora la mano poker", "mano", "poker", 3, 30));
        tarots.add(new TarotSobreMano("Fuerza", "Mejora la mano poker", "mano", "poker", 3, 30));

        Carta carta = new Carta("corazones", 10);

        when(lectorMock.crearTienda(comodines, tarots,carta)).thenReturn(new Tienda(comodines, tarots, carta));



        // Agregar rondas con tienda mockeada
        rondasMockeadas.add(new Ronda(1, 3, 3, 3000, new Tienda(comodines, tarots, carta)));
        rondasMockeadas.add(new Ronda(2, 3, 3, 3600, new Tienda(comodines, tarots, carta)));

        // Configurar comportamiento del mock
        when(lectorMock.leerBalatro()).thenReturn(rondasMockeadas);
        when(lectorMock.leerMazo()).thenReturn(mazoMockeado);

        // Crear el juego
        Juego juego = new Juego();

        // Usar el mock
        juego.leerArchivo(lectorMock);

        // Verificar que el juego se configuró correctamente
        Jugador jugador = juego.getJugador();
        assertNotNull(jugador, "El jugador debe estar inicializado.");
        assertEquals(5, jugador.getMazo().cantidadDeCartas(), "El mazo debe contener 5 cartas.");

        // Verificar rondas
        assertEquals(2, rondasMockeadas.size(), "Debe haber dos rondas configuradas.");

        // Simular la primera ronda
        Ronda primeraRonda = rondasMockeadas.get(0);
        jugador.setCantidadDeDescartes(primeraRonda.getDescartes());
        jugador.setCantidadDeManos(primeraRonda.getCantidadDeManos());

        assertEquals(3000, primeraRonda.getPuntajeASuperar(), "El puntaje a superar en la primera ronda debe ser 3000.");
    }
}
