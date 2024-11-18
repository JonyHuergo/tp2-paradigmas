package org.example;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Puntaje {
    private int valor;

    @JsonCreator
    public Puntaje(@JsonProperty("puntajeASuperar") int valor) {
        this.valor = valor;
    }

    public Puntaje sumarCon(Puntaje otroPuntaje){
        return new Puntaje(this.valor + otroPuntaje.getValor());
    }

    public Puntaje multiplicarCon(Puntaje otroPuntaje){ return new Puntaje(this.valor * otroPuntaje.getValor()); }

    public int getValor() {
        return valor;
    }

    public boolean esMenorA(Puntaje otroPuntaje){
        return valor > otroPuntaje.getValor();
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Puntaje puntaje = (Puntaje) obj;
        //return puntaje.getValor() == valor;
        return this.valor == puntaje.valor;
    }

}

