package org.example.Tarot;

import org.example.Carta;

public abstract class Tarot { // Tentativo, revisar
    private String nombre;
    private String descripcion;
    private String sobre;
    private String ejemplar;
    private int puntos;
    protected float multiplicador;

    public Tarot(String nombre, String descripcion, String sobre, String ejemplar, int puntos, float multiplicador){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.sobre = sobre;
        this.ejemplar = ejemplar;
        this.puntos = puntos;
        this.multiplicador = multiplicador;
    }
    public Tarot(float multiplicador){
        this.multiplicador = multiplicador;
    }
    public Tarot(int puntos){
        this.puntos = puntos;
    }
    public abstract void aplicarEfecto(Carta carta);

}
