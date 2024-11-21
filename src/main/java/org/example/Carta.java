package org.example;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Carta {
    private String nombre;
    private Palo palo;
    private int valor;
    private int puntos;
    private int multiplicador;

    public enum Palo {
        TREBOL, CORAZONES, PICAS, DIAMANTES;

        public static Palo fromString(String palo) {
            switch (palo.toLowerCase()) {
                case "trebol": return TREBOL;
                case "corazones": return CORAZONES;
                case "picas": return PICAS;
                case "diamantes": return DIAMANTES;
                default: throw new IllegalArgumentException("Palo desconocido: " + palo);
            }
        }
    }

    /*public enum Valor {
        AS(1), DOS(2), TRES(3), CUATRO(4), CINCO(5),
        SEIS(6), SIETE(7), OCHO(8), NUEVE(9), DIEZ(10),
        JOTA(11), REINA(12), REY(13);

        private final int value;

        Valor(int value) {
            this.value = value;
        }

        public static Valor fromString(String numero) {
            switch (numero.toLowerCase()) {
                case "as": return AS;
                case "2": return DOS;
                case "3": return TRES;
                case "4": return CUATRO;
                case "5": return CINCO;
                case "6": return SEIS;
                case "7": return SIETE;
                case "8": return OCHO;
                case "9": return NUEVE;
                case "10": return DIEZ;
                case "jota": return JOTA;
                case "reina": return REINA;
                case "rey": return REY;
                default: throw new IllegalArgumentException("Valor desconocido: " + numero);
            }
        }
    }*/

    public Carta(String palo, int valor) {
        this.nombre = valor + " de " + palo;
        this.palo = Palo.fromString(palo);
        this.valor = valor;
        this.multiplicador = 0;
        puntos = valor;
    }

    @JsonCreator
    public Carta(
            @JsonProperty("nombre") String nombre,
            @JsonProperty("palo") String palo,
            @JsonProperty("numero") int valor,
            @JsonProperty("puntos") int puntos,
            @JsonProperty("multiplicador") int multiplicador) {

        this.nombre = nombre;
        this.palo = Palo.fromString(palo);
        this.valor = valor;
        this.puntos = puntos;
        this.multiplicador = multiplicador;
    }


    public int getValor(){
        return this.valor;
    }

    public Palo getPalo(){
        return this.palo;
    }

    public boolean cartaTieneMismoValor(Carta carta){
        return carta.valorEsIgual(this.valor);
    }

    private boolean valorEsIgual(int valorCarta){
        return valorCarta == valor;
    }

    public boolean paloEsIgual(Palo paloCarta){
        return Objects.equals(paloCarta, palo);
    }

    public boolean paloEsIgual(Carta carta){
        return carta.paloEsIgual(this.palo);
        //return this.paloEsIgual(carta.getPalo());
    }

    public boolean esInmediatamenteSuperior(Carta carta){
        return (carta.valorEsIgual(this.valor + 1));
    }

    public boolean esInmediatamenteInferior(Carta carta){
        return (carta.valorEsIgual(this.valor - 1));
    }

    public void modificarMultiplicador(int multiplicador){
        this.multiplicador = multiplicador;
    }

    public void agregarPuntos(int puntos) {
        this.puntos += puntos;
    }

    public int actualizarPuntajeTotal(int puntajeTotal) {
        return puntajeTotal + puntos;
    }

    public int actualizarMultiplicadorTotal(int multiplicadorTotal) {
        return multiplicadorTotal + multiplicador;
    }
}
