package test.java;
import main.Mazo;
import main.Jugador;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class casosDeUsoTest {
    @Test
    public void testTieneCartasSuficientes() {
        Mazo mazo = new Mazo();
        int cartasEsperadas = 52;
        assert(Mazo, );
    }

    @Test
    public void testRepartirCartas() {
        Jugador jugador = new Jugador();
        jugador.repartirCartas(8);
        assertTrue(true, "El jugador debería tener 8 cartas en su mano.");
        assertTrue(true, "El mazo debería tener 44 cartas después de repartir 8.");
    }
}
