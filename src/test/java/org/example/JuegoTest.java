package org.example;

import org.example.Comodin.*;
import org.example.Tarot.Tarot;
import org.example.Tarot.TarotSobreMano;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class JuegoTest {

    @Test
    public void testJuegoConLectorMockeado() throws IOException {
        LectorArchivosJson lectorMock = mock(LectorArchivosJson.class);

        List<Ronda> rondasMockeadas = new ArrayList<>();

        List<Comodin> comodines = new ArrayList<>();
        comodines.add(new ComodinPorManoJugada("Comodin Afortunado", "+15 al multiplicador si la mano jugada contiene una escalera de color", "escalera de color", 1, 15));
        comodines.add(new ComodinPorDescarte(50, 1, "siempre"));

        List<Tarot> tarots = new ArrayList<>();
        tarots.add(new TarotSobreMano("Fuerza", "Mejora la mano poker", "mano", "poker", 30, 3));
        tarots.add(new TarotSobreMano("Fuerza", "Mejora la mano poker", "mano", "poker", 30, 3));

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

        rondasMockeadas.add(rondaMock1);
        rondasMockeadas.add(rondaMock2);
        rondasMockeadas.add(rondaMock3);

        when(lectorMock.leerBalatro()).thenReturn(rondasMockeadas);

        // Mockear mazos de ronda
        ArrayList<Carta> cartasMazoRonda1 = new ArrayList<>();
        cartasMazoRonda1.add(new Carta("diamantes", 2));
        cartasMazoRonda1.add(new Carta("diamantes", 3));
        cartasMazoRonda1.add(new Carta("diamantes", 4));
        cartasMazoRonda1.add(new Carta("diamantes", 5));
        cartasMazoRonda1.add(new Carta("diamantes", 6));

        ArrayList<Carta> cartasMazoRonda2 = new ArrayList<>();
        cartasMazoRonda2.add(new Carta("trebol", 4));
        cartasMazoRonda2.add(new Carta("picas", 4));
        cartasMazoRonda2.add(new Carta("diamantes", 4));
        cartasMazoRonda2.add(new Carta("corazones", 4));

        ArrayList<Carta> cartasMazoRonda3 = new ArrayList<>();
        cartasMazoRonda3.add(new Carta("trebol", 2));

        Mazo mazoRonda1 = Mockito.mock(Mazo.class);
        when(mazoRonda1.repartirCartas(5)).thenReturn(cartasMazoRonda1);
        Mazo mazoRonda2 = Mockito.mock(Mazo.class);
        when(mazoRonda2.repartirCartas(4)).thenReturn(cartasMazoRonda2);
        Mazo mazoRonda3 = Mockito.mock(Mazo.class);
        when(mazoRonda3.repartirCartas(1)).thenReturn(cartasMazoRonda3);

        // Crear el juego y configurar el lector mockeado
        Juego juego = new Juego();
        juego.leerArchivo(lectorMock);
        List<Ronda> rondas = lectorMock.leerBalatro();

        Jugador jugador = juego.getJugador();

        // Ronda 1
        int numeroRonda = 0;
        Ronda ronda = rondas.get(numeroRonda);
        ronda.setAtributosJugador(jugador);

        // Tienda Ronda 1
        Tienda tienda = ronda.obtenerTienda();
        List<Comodin> comodinesTienda = tienda.obtenerComodines();
        jugador.agregarComodin(comodinesTienda.get(0));

        // Juego Ronda 1: Pasa la primera ronda con puntaje (100 + 20) * (8 + 15) = 2760 * 2
        float puntaje = 0;
        while (puntaje < ronda.getPuntajeASuperar() && !jugador.perdio()) {
           for (Carta cartaElegida : mazoRonda1.repartirCartas(5)) {
               jugador.agregarCarta(cartaElegida);
           }
           puntaje = jugador.jugar(numeroRonda);
        }
        System.out.println(puntaje);

        // Ronda 2: Pasa la segunda ronda con puntaje (60+(4x4)+30)*(7+3) = 1060 * 4
        numeroRonda = 1;
        ronda = rondas.get(numeroRonda);
        //ronda.setAtributosJugador(jugador);
        jugador.setCantidadDeManos(ronda.getCantidadDeManos());
        jugador.setCantidadDeDescartes(ronda.getDescartes());

        // Tienda Ronda 2
        tienda = ronda.obtenerTienda();
        List<Tarot> tarotsTienda = tienda.obtenerTarots();
        jugador.agregarTarot(tarotsTienda.get(0));//"FUERZA"

        // Juego Ronda 2: Pasa la segunda ronda con puntaje
        puntaje = 0;
        while (puntaje < ronda.getPuntajeASuperar() && !jugador.perdio()) {
            for (Carta cartaElegida : mazoRonda2.repartirCartas(4)) {
                jugador.agregarCarta(cartaElegida);
            }
            puntaje = jugador.jugar(numeroRonda);
        }

        System.out.print("  ");
        System.out.print(puntaje);

        // Ronda 3: Pierde esta ultima ronda
        numeroRonda = 2;
        ronda = rondas.get(numeroRonda);
        //ronda.setAtributosJugador(jugador);
        jugador.setCantidadDeManos(ronda.getCantidadDeManos());
        jugador.setCantidadDeDescartes(ronda.getDescartes());

        // Tienda Ronda 3
        tienda = ronda.obtenerTienda();
        Carta cartaTienda = tienda.obtenerCarta();
        jugador.agregarCartaMazo(cartaTienda);

        // Juego Ronda 3
        puntaje = 0;
        while (puntaje < ronda.getPuntajeASuperar() && !jugador.perdio()) {
            for (Carta cartaElegida : mazoRonda3.repartirCartas(1)) { //(5+2+30)*(1+3)=148   es el tarot que esta bug
                jugador.agregarCarta(cartaElegida);
            }
            puntaje = jugador.jugar(numeroRonda);
        }
        assertTrue(jugador.perdio());
    }
}
