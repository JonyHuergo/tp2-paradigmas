package org.example.Comodines;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.Activacion;
import org.example.Comodin;
import org.example.Efecto;
import org.example.ManoPoker;


public class ComodinComodin extends Comodin {
    public ComodinComodin(){
        super(new Comodin(4, "Siempre", new Efecto(1, 4)));
    }
    public void aplicarEfecto(ManoPoker mano) {
        return;
    }