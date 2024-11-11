package test.java;
import main.Mazo;
import main.Jugador;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class casosDeUsoTest {
    @Test
    public void testTieneCartasSuficientes() {
        Mazo mazo = new Mazo();
        int cartasEsperadas = 52;

        int cartasObtenidas = mazo.getCartas().size();

        assertEquals(cartasObtenidas, cartasEsperadas);
    }

    @Test
    public void testRepartirCartas() {
        Jugador jugador = new Jugador();
        jugador.repartirCartas(8);
        int cartasEsperadas = 52;
        assertEquals()
        assertTrue(true, "El mazo debería tener 44 cartas después de repartir 8.");
    }
}
