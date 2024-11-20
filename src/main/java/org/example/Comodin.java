package org.example;

import java.util.ArrayList;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class Comodin {
    private String nombre;
    private String descripcion;
    private Object activacion;
    private int puntajeAdicional;
    private int multiplicadorAdicional;

    public Comodin(int puntajeAdicional, int multiplicadorAdicional) {
        this.puntajeAdicional = puntajeAdicional;
        this.multiplicadorAdicional = multiplicadorAdicional;
    }

    // Constructor con mapeo directo del JSON
    @JsonCreator
    public Comodin(@JsonProperty("nombre") String nombre, @JsonProperty("descripcion") String descripcion, @JsonProperty("activacion") Activacion activacion, @JsonProperty("efecto") Efecto efecto) {
            this.nombre = nombre;
            this.descripcion = descripcion;
            this.activacion = activacion;
            this.puntajeAdicional = efecto.getPuntos();
            this.multiplicadorAdicional = efecto.getMultiplicador();
    }

    public void aplicarEfecto(Carta carta){
        // Va a haber que crear hijas que implementen esta clase
        // para facilitar las pruebas, utilizo la implementacion de
        // un comodín que sume 8 al multiplicador de la carta
        carta.actualizarMultiplicadorTotal(this.multiplicadorAdicional);//deberia ser 8
    }

    public void aplicarEfecto(ManoPoker mano){
        return;
    }

    public int aplicarModificacionPuntaje(int puntaje){
        return (puntaje + puntajeAdicional);
    }

    public int aplicarModificacionMultiplicador(int puntaje){
        return (puntaje *multiplicadorAdicional);
    }
}
