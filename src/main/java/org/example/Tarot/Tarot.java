package org.example.Tarot;

import org.example.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import org.example.Controladores.PantallaJuegoController;
import org.example.Handlers.CompraTarotHandler;

public abstract class Tarot extends Comprable { // Tentativo, revisar
    protected String nombre;
    protected String descripcion;
    protected String sobre;
    protected String ejemplar;
    protected int puntos;
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

    @Override
    public String getRuta() {
        String nombreFormateado = nombre.replace(" ", "_");
        return "/Tarots/Tarot_" + nombreFormateado + ".png";
    }


    public abstract void aplicarEfecto(ManoPoker manoPoker);


    @Override
    public EventHandler<ActionEvent> crearHandler(PantallaJuegoController pantallaJuegoController,Mazo mazo, Jugador jugador, Ronda ronda) {
        return new CompraTarotHandler(this, jugador,pantallaJuegoController, mazo, ronda);
    }

    @Override
    public String getDescripcion(){
        return descripcion;
    }
}
