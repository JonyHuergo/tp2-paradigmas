

package org.example;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import org.example.Controladores.PantallaJuegoController;
import org.example.Handlers.CompraCartaHandler;

import java.util.Objects;

public class Carta extends Comprable{
    private String nombre;
    private String palo;
    private ValorCarta valor;
    private int puntaje;
    private String multiplicador;

    public enum ValorCarta {
        NUMERO_2(2, "2"),
        NUMERO_3(3, "3"),
        NUMERO_4(4, "4"),
        NUMERO_5(5, "5"),
        NUMERO_6(6, "6"),
        NUMERO_7(7, "7"),
        NUMERO_8(8, "8"),
        NUMERO_9(9, "9"),
        NUMERO_10(10, "10"),
        JOTA(11, "Jota"),
        REINA(12, "Reina"),
        REY(13, "Rey"),
        AS(14, "As");

        private final int valor;
        private final String nombre;


        ValorCarta(int valor, String nombre) {
            this.valor = valor;
            this.nombre = nombre;
        }

        public int getValor() {
            return valor;
        }



        public String getNombre() {
            return nombre;
        }

        public static ValorCarta fromString(String nombre) {
            for (ValorCarta v : ValorCarta.values()) {
                if (v.getNombre().equalsIgnoreCase(nombre)) {
                    return v;
                }
            }

            throw new IllegalArgumentException("Valor no reconocido: " + nombre);
        }
    }

    public Carta(String nombre, String palo, String valor, int puntaje, String multiplicador) {
        this.nombre = nombre;
        this.palo = palo;
        this.valor = ValorCarta.fromString(valor);
        this.puntaje = puntaje;
        this.multiplicador = multiplicador;
    }

    public Carta(String palo, int valor) {
        this.palo = palo;
        this.valor = buscarValorCarta(valor); // Metodo dedicado a buscar el enum
        this.multiplicador = "0";
        this.puntaje = valor;
    }

    @Override
    public EventHandler<ActionEvent> crearHandler(PantallaJuegoController pantallaJuegoController, Mazo mazo, Jugador jugador, int puntajeASuperar) {
        return new CompraCartaHandler(this, mazo);
    }

    private ValorCarta buscarValorCarta(int valor) {
        for (ValorCarta v : ValorCarta.values()) {
            if (v.getValor() == valor) {
                return v;
            }
        }
        throw new IllegalArgumentException("Valor no válido para una carta: " + valor);
    }

    // Method to get the card route (palo + valor)
    @Override
    public String getRuta() {
        return "/cartas/" + palo + "_" + valor.getNombre() + ".png";
    }

    public int getValor() {
        return this.valor.getValor();
    }

    public String getPalo() {
        return this.palo;
    }

    public int getPuntaje() { return this.puntaje;}

    public String getMultiplicador() {
        return this.multiplicador;
    }

    public boolean cartaTieneMismoValor(Carta carta) {
        return carta.valorEsIgual(this.valor.getValor());
    }

    private boolean valorEsIgual(int valorCarta) {
        return valorCarta == this.valor.getValor();
    }

    public boolean paloEsIgual(String paloCarta) {
        return Objects.equals(paloCarta, this.palo);
    }

    public boolean paloEsIgual(Carta carta) {
        return carta.paloEsIgual(this.palo);
    }

    public boolean nombreEsIgual(Carta carta) {return carta.nombreEsIgual(this.nombre);}

    public boolean nombreEsIgual(String nombreCarta) {return Objects.equals(nombreCarta, this.nombre);}

    public boolean esInmediatamenteSuperior(Carta carta) {
        return carta.valorEsIgual(this.valor.getValor() + 1);
    }

    public boolean esInmediatamenteInferior(Carta carta) {
        return carta.valorEsIgual(this.valor.getValor() - 1);
    }

    public void modificarMultiplicador(float multiplicador) {
        String multiplicadorString = Float.toString(multiplicador);
        this.multiplicador = multiplicadorString;
    }

    public void agregarPuntos(int puntos) {
        this.puntaje += puntos;
    }

    public int actualizarPuntajeTotal(int puntajeTotal) {
        return puntajeTotal + this.puntaje;
    }

    public float actualizarMultiplicadorTotal(float multiplicadorTotal) {
        float suma = Float.parseFloat(this.multiplicador) + multiplicadorTotal;
        return suma;
    }

    public Carta clonar(){
        return new Carta(nombre, palo, Integer.toString(valor.getValor()), puntaje, multiplicador);
    }

}
