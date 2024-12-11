package org.example;

import org.example.Comodin.*;
import org.example.Tarot.Tarot;
import org.example.Tarot.TarotSobreMano;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class JuegoTest {

    @Test
    public void testJuegoConLectorMockeado() throws IOException {
        // Crear mock del lector
        LectorArchivosJson lectorMock = mock(LectorArchivosJson.class);

        // Crear datos mockeados
        // Mockear cartas del mazo
        ArrayList<Carta> mazoMockeado = new ArrayList<>();
        mazoMockeado.add(new Carta("diamantes", 2));
        mazoMockeado.add(new Carta("diamantes", 3));
        mazoMockeado.add(new Carta("diamantes", 4));
        mazoMockeado.add(new Carta("diamantes", 5));
        mazoMockeado.add(new Carta("diamantes", 6));

        // Mockear rondas
        List<Ronda> rondasMockeadas = new ArrayList<>();

        // Mockear Comodines y Tarots de la tienda
        List<Comodin> comodines = new ArrayList<>();
        comodines.add(new ComodinPorManoJugada("Comodin Astuto", "descripcion", "par", 50, 1));
        comodines.add(new ComodinPorDescarte(50, 1, "par"));

        List<Tarot> tarots = new ArrayList<>();
        tarots.add(new TarotSobreMano("Fuerza", "Mejora la mano poker", "mano", "poker", 3, 30));
        tarots.add(new TarotSobreMano("Fuerza", "Mejora la mano poker", "mano", "poker", 3, 30));

        Carta carta = new Carta("corazones", 10);

        when(lectorMock.crearTienda(comodines, tarots,carta)).thenReturn(new Tienda(comodines, tarots, carta));

        Ronda ronda1Mock = mock(Ronda.class);
        when(ronda1Mock.getPuntajeASuperar()).thenReturn(3000);
        when(ronda1Mock.getDescartes()).thenReturn(3);
        when(ronda1Mock.getCantidadDeManos()).thenReturn(3);
        when(ronda1Mock.obtenerTienda()).thenReturn(new Tienda(comodines, tarots, carta));

        Ronda ronda2Mock = mock(Ronda.class);
        when(ronda2Mock.getPuntajeASuperar()).thenReturn(3600);
        when(ronda2Mock.getDescartes()).thenReturn(4);
        when(ronda2Mock.getCantidadDeManos()).thenReturn(4);
        when(ronda2Mock.obtenerTienda()).thenReturn(new Tienda(comodines, tarots, carta));

        Ronda ronda3Mock = mock(Ronda.class);
        when(ronda3Mock.getPuntajeASuperar()).thenReturn(4320);
        when(ronda3Mock.getDescartes()).thenReturn(4);
        when(ronda3Mock.getCantidadDeManos()).thenReturn(4);
        when(ronda3Mock.obtenerTienda()).thenReturn(new Tienda(comodines, tarots, carta));


        // Agregar rondas con tienda mockeada
        rondasMockeadas.add(ronda1Mock);
        rondasMockeadas.add(ronda2Mock);
        rondasMockeadas.add(ronda3Mock);

        // Configurar comportamiento del mock
        when(lectorMock.leerBalatro()).thenReturn(rondasMockeadas);
        when(lectorMock.leerMazo()).thenReturn(mazoMockeado);
        List<Ronda> rondas = lectorMock.leerBalatro();
        // Crear el juego
        Juego juego = new Juego();

        // Usar el mock
        juego.leerArchivo(lectorMock);

        // Verificar que el juego se configuró correctamente
        Jugador jugador = juego.getJugador();

        int numeroRonda = 0;
        Ronda ronda = rondas.get(numeroRonda);

        int descartes = ronda.getDescartes();
        jugador.setCantidadDeDescartes(descartes);

        int manos = ronda.getCantidadDeManos();
        jugador.setCantidadDeManos(manos);

        // Tienda Ronda 1
        Tienda tienda = ronda.obtenerTienda();
        List<Comodin> comodinesTienda = tienda.obtenerComodines();
        jugador.agregarComodin(comodinesTienda.get(0));

        // Juego Ronda 1
        float puntaje = 0;
        while (puntaje < ronda.getPuntajeASuperar() && manos > 0) {
            manos -= 1;
            for (Carta cartaElegida : mazoMockeado) {
                jugador.agregarCarta(cartaElegida);
            }
            puntaje = jugador.jugar(numeroRonda);
            assertFalse(jugador.perdio());
        }

        // Ronda 2
        numeroRonda = 1;
        ronda = rondasMockeadas.get(numeroRonda);

        descartes = ronda.getDescartes();
        jugador.setCantidadDeDescartes(descartes);

        manos = ronda.getCantidadDeManos();
        jugador.setCantidadDeManos(manos);

        // Tienda Ronda 2
        tienda = ronda.obtenerTienda();
        List<Tarot> tarotsTienda = tienda.obtenerTarots();
        jugador.agregarTarot(tarotsTienda.get(0));//"FUERZA"

        ArrayList<Carta> mazoMockeado2 = new ArrayList<>();
        mazoMockeado2.add(new Carta("trebol", 4));
        mazoMockeado2.add(new Carta("picas", 4));
        mazoMockeado2.add(new Carta("diamantes", 4));
        mazoMockeado2.add(new Carta("corazones", 4));

        // Juego Ronda 2
        puntaje = 0;
        while (puntaje < ronda.getPuntajeASuperar() && manos > 0) {//(60+(4x4)+30)*((7+3)+(15x3))=5830, deberia dar 7420 pero jugador tiene los descartes hardcodeados

            for (Carta cartaElegida : mazoMockeado2) {
                jugador.agregarCarta(cartaElegida);
            }
            puntaje = jugador.jugar(numeroRonda);
            manos -= 1;
        }
        assertTrue(puntaje > ronda.getPuntajeASuperar());

        //Ronda 3
        ArrayList<Carta> mazoMockeado3 = new ArrayList<>();
        mazoMockeado3.add(new Carta("trebol", 2));


        numeroRonda = 2;
        ronda = rondas.get(numeroRonda);

        descartes = ronda.getDescartes();
        jugador.setCantidadDeDescartes(descartes);

        manos = ronda.getCantidadDeManos();
        jugador.setCantidadDeManos(manos);

        // Tienda Ronda 3
        tienda = ronda.obtenerTienda();
        Carta cartaTienda = tienda.obtenerCarta();
        jugador.agregarCartaMazo(cartaTienda);

        // Juego Ronda 3
        puntaje = 0;
        while (puntaje < ronda.getPuntajeASuperar() && manos > 0) {
            for (Carta cartaElegida : mazoMockeado3) {
                jugador.agregarCarta(cartaElegida);
            }
            puntaje = jugador.jugar(numeroRonda);
            manos -= 1;
        }

        assertTrue(puntaje < ronda.getPuntajeASuperar());







    }


}
