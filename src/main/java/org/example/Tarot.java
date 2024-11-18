package org.example;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.ArrayList;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "nombre")
@JsonSubTypes({
        @JsonSubTypes.Type(value = TarotAgregar10Puntos.class, name = "TarotAgregar10Puntos"),
        @JsonSubTypes.Type(value = TarotMultiplicadorX6.class, name = "TarotMultiplicadorX6"),
        @JsonSubTypes.Type(value = TarotElMago.class, name = "El Mago"),
        @JsonSubTypes.Type(value = TarotElMago.class, name = "El Carro"),
        @JsonSubTypes.Type(value = TarotElMago.class, name = "Fuerza"),
        @JsonSubTypes.Type(value = TarotElMago.class, name = "Justicia"),
        @JsonSubTypes.Type(value = TarotElMago.class, name = "La Emperatriz"),
        @JsonSubTypes.Type(value = TarotElMago.class, name = "El Tonto"),
        @JsonSubTypes.Type(value = TarotElMago.class, name = "El Hierofante"),
        @JsonSubTypes.Type(value = TarotElMago.class, name = "La Suma Sacerdotisa"),
        @JsonSubTypes.Type(value = TarotElMago.class, name = "El Emperador"),
        @JsonSubTypes.Type(value = TarotElMago.class, name = "Los Amantes"),
        @JsonSubTypes.Type(value = TarotElMago.class, name = "La Torre"),
        @JsonSubTypes.Type(value = TarotElMago.class, name = "La rueda de la fortuna"),
        @JsonSubTypes.Type(value = TarotElMago.class, name = "Muerte"),
        @JsonSubTypes.Type(value = TarotElMago.class, name = "El Ahorcado"),
        @JsonSubTypes.Type(value = TarotElMago.class, name = "El Ermitano"),
})//añadir una clase para cada tarot, por ahora todos los tarots inician el mago
public abstract class Tarot {
    private String nombre;
    private String descripcion;
    private Puntaje puntos;
    private Puntaje multiplicador;
    private String sobre;
    private String ejemplar;

    public Tarot(){};

    @JsonCreator
    public Tarot(@JsonProperty("nombre") String nombre, @JsonProperty("descripcion") String descripcion, @JsonProperty("efecto") Efecto efecto, @JsonProperty("sobre")String sobre, @JsonProperty("ejemplar")String ejemplar) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.sobre = sobre;
        this.ejemplar = ejemplar;
        this.puntos = efecto.getPuntos();
        this.multiplicador = efecto.getMultiplicador();
    }

    public abstract void aplicarEfecto(Carta carta);
}
