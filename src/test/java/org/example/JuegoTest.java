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
        List<Ronda> rondasMockeadas = new ArrayList<>();

        // Crear datos de prueba
        List<Comodin> comodines = new ArrayList<>();
        comodines.add(new ComodinPorManoJugada("Comodin Astuto", "descripcion", "par", 50, 1));
        comodines.add(new ComodinPorDescarte(50, 1, "par"));
        // Crear datos de prueba

        List<Tarot> tarots = new ArrayList<>();
        tarots.add(new TarotSobreMano("Fuerza", "Mejora la mano poker", "mano", "poker", 3, 30));
        tarots.add(new TarotSobreMano("Fuerza", "Mejora la mano poker", "mano", "poker", 3, 30));

        Carta carta = new Carta("corazones", 10);

        // Mockear tiendas
        Tienda tiendaMock1 = mock(Tienda.class);
        Tienda tiendaMock2 = mock(Tienda.class);
        Tienda tiendaMock3 = mock(Tienda.class);

        when(tiendaMock1.obtenerComodines()).thenReturn(comodines);
        when(tiendaMock1.obtenerTarots()).thenReturn(tarots);
        when(tiendaMock1.obtenerCarta()).thenReturn(carta);

        when(tiendaMock2.obtenerComodines()).thenReturn(comodines);
        when(tiendaMock2.obtenerTarots()).thenReturn(tarots);
        when(tiendaMock2.obtenerCarta()).thenReturn(carta);

        when(tiendaMock3.obtenerComodines()).thenReturn(comodines);
        when(tiendaMock3.obtenerTarots()).thenReturn(tarots);
        when(tiendaMock3.obtenerCarta()).thenReturn(carta);

        // Mockear rondas
        Ronda rondaMock1 = mock(Ronda.class);
        Ronda rondaMock2 = mock(Ronda.class);
        Ronda rondaMock3 = mock(Ronda.class);

        when(rondaMock1.getPuntajeASuperar()).thenReturn(3000);
        when(rondaMock1.getDescartes()).thenReturn(3);
        when(rondaMock1.getCantidadDeManos()).thenReturn(3);
        when(rondaMock1.obtenerTienda()).thenReturn(tiendaMock1);

        when(rondaMock2.getPuntajeASuperar()).thenReturn(3600);
        when(rondaMock2.getDescartes()).thenReturn(4);
        when(rondaMock2.getCantidadDeManos()).thenReturn(4);
        when(rondaMock2.obtenerTienda()).thenReturn(tiendaMock2);

        when(rondaMock3.getPuntajeASuperar()).thenReturn(4320);
        when(rondaMock3.getDescartes()).thenReturn(4);
        when(rondaMock3.getCantidadDeManos()).thenReturn(4);
        when(rondaMock3.obtenerTienda()).thenReturn(tiendaMock3);

        // Agregar rondas a la lista
        rondasMockeadas.add(rondaMock1);
        rondasMockeadas.add(rondaMock2);
        rondasMockeadas.add(rondaMock3);

        // Configurar el lector para devolver las rondas mockeadas
        when(lectorMock.leerBalatro()).thenReturn(rondasMockeadas);


        // Configurar el lector para devolver un mazo realista
        ArrayList<Carta> mazoMockeado = new ArrayList<>();
        mazoMockeado.add(new Carta("diamantes", 2));
        mazoMockeado.add(new Carta("diamantes", 3));
        mazoMockeado.add(new Carta("diamantes", 4));
        mazoMockeado.add(new Carta("diamantes", 5));
        mazoMockeado.add(new Carta("diamantes", 6));

        // Mockear rondas
        when(lectorMock.leerMazo()).thenReturn(mazoMockeado);

        // Crear el juego y configurar el lector mockeado
        Juego juego = new Juego();

        juego.leerArchivo(lectorMock);
        List<Ronda> rondas = lectorMock.leerBalatro();

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
        ronda = rondas.get(numeroRonda);

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
