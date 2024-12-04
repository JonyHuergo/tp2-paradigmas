package org.example.Tarot;


import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class InicializadorDeTarots {
    private static final Map<String, Class<? extends Tarot>> REGISTRO = new HashMap<>();

    static {
        REGISTRO.put("TarotAgregaPuntos", TarotSobreMano.class);
        REGISTRO.put("TarotMultiplicador", TarotSobreMano.class);
        REGISTRO.put("El Tonto", TarotSobreCarta.class);
        REGISTRO.put("El Mago", TarotSobreMano.class);
        REGISTRO.put("La Suma Sacerdotisa", TarotSobreMano.class);
        REGISTRO.put("La Emperatriz", TarotSobreCarta.class);
        REGISTRO.put("El Emperador", TarotSobreMano.class);
        REGISTRO.put("El Hierofante", TarotSobreCarta.class);
        REGISTRO.put("Los Amantes", TarotSobreMano.class);
        REGISTRO.put("El Carro", TarotSobreCarta.class);
        REGISTRO.put("Justicia", TarotSobreCarta.class);
        REGISTRO.put("El Ermitano", TarotSobreMano.class);
        REGISTRO.put("La rueda de la fortuna", TarotSobreMano.class);
        REGISTRO.put("Fuerza", TarotSobreMano.class);
        REGISTRO.put("El Ahorcado", TarotSobreMano.class);
        REGISTRO.put("Muerte", TarotSobreMano.class);
        REGISTRO.put("La Torre", TarotSobreCarta.class);
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
