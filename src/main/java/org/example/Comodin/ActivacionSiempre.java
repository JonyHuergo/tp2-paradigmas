package org.example.Comodin;

public class ActivacionSiempre implements Activacion {


    @Override
    public boolean revisarCondicion(String nombreMano) {
        return true;
    }
}
