package org.example;

import java.util.ArrayList;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Comodin {
    private String nombre;
    private String descripcion;
    private Object activacion;
    private Puntaje puntajeAdicional;
    private Puntaje multiplicadorAdicional;

    public Comodin(int puntajeAdicional, int multiplicadorAdicional) {
        this.puntajeAdicional = new Puntaje(puntajeAdicional);
        this.multiplicadorAdicional = new Puntaje(multiplicadorAdicional);
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

    public Puntaje aplicarModificacionPuntaje(Puntaje puntaje){
        return puntaje.sumarCon(puntajeAdicional);
    }

    public Puntaje aplicarModificacionMultiplicador(Puntaje puntaje){
        return puntaje.multiplicarCon(multiplicadorAdicional);
    }

}
