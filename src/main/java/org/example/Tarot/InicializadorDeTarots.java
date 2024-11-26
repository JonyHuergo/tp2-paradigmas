package org.example.Tarot;


import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class InicializadorDeTarots {
    private static final Map<String, Class<? extends Tarot>> REGISTRO = new HashMap<>();

    static {
        REGISTRO.put("TarotAgregaPuntos", TarotAgregaPuntos.class);
        REGISTRO.put("TarotMultiplicador", TarotMultiplicador.class);
        REGISTRO.put("El Tonto", TarotAgregaPuntos.class);
        REGISTRO.put("El Mago", TarotAgregaPuntos.class);
        REGISTRO.put("La Suma Sacerdotisa", TarotAgregaPuntos.class);
        REGISTRO.put("La Emperatriz", TarotAgregaPuntos.class);
        REGISTRO.put("El Emperador", TarotAgregaPuntos.class);
        REGISTRO.put("El Hierofante", TarotAgregaPuntos.class);
        REGISTRO.put("Los Amantes", TarotAgregaPuntos.class);
        REGISTRO.put("El Carro", TarotAgregaPuntos.class);
        REGISTRO.put("Justicia", TarotAgregaPuntos.class);
        REGISTRO.put("El Ermitano", TarotAgregaPuntos.class);
        REGISTRO.put("La rueda de la fortuna", TarotAgregaPuntos.class);
        REGISTRO.put("Fuerza", TarotAgregaPuntos.class);
        REGISTRO.put("El Ahorcado", TarotAgregaPuntos.class);
        REGISTRO.put("Muerte", TarotAgregaPuntos.class);
        REGISTRO.put("La Torre", TarotAgregaPuntos.class);
    }

    public static Tarot crearTarot(String nombre, String descripcion, String sobre, String ejemplar, int puntos, float multiplicador) {
        Class<? extends Tarot> tarotClass = REGISTRO.get(nombre);
        if (tarotClass == null) {
            throw new IllegalArgumentException("Tipo de tarot desconocido: " + nombre);
        }
        try {
            Constructor<? extends Tarot> constructor = tarotClass.getDeclaredConstructor(String.class, String.class, String.class, String.class, int.class, float.class);
            return constructor.newInstance(nombre, descripcion, sobre, ejemplar, puntos, multiplicador);

        } catch (Exception e) {
            throw new RuntimeException("Error al crear el Tarot", e);
        }
    }
}
