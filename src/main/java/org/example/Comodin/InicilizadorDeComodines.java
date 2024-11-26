package org.example.Comodin;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InicilizadorDeComodines {
    private static final Map<String, Class<? extends Comodin>> REGISTRO = new HashMap<>();

    static {
        REGISTRO.put("Comodin", Comodin.class);
        REGISTRO.put("Caminante", ComodinCombo.class);
        REGISTRO.put("Comodin Astuto", ComodinPorDescarte.class);
        REGISTRO.put("Comodin loco", ComodinPorDescarte.class);
        REGISTRO.put("Comodin Generoso", ComodinPorDescarte.class);
        REGISTRO.put("Comodin Arriesgado", ComodinPorDescarte.class);
        REGISTRO.put("Comodin Abundante", ComodinPorDescarte.class);
        REGISTRO.put("Comodin Poderoso", ComodinPorDescarte.class);
        REGISTRO.put("Comodin Afortunado", ComodinPorDescarte.class);
        REGISTRO.put("Comodin Real", ComodinPorDescarte.class);
        REGISTRO.put("Comodin Épico", ComodinPorDescarte.class);
        REGISTRO.put("Bandera", ComodinPorDescarte.class);
        REGISTRO.put("Cumbre Mistica", ComodinPorDescarte.class);
        REGISTRO.put("Descarte Dorado", ComodinPorDescarte.class);
        REGISTRO.put("Eco de Montaña", ComodinPorDescarte.class);
        REGISTRO.put("Ritual del Valle", ComodinPorDescarte.class);
        REGISTRO.put("Descarte Sagrado", ComodinPorDescarte.class);
        REGISTRO.put("Talisman de Descarte", ComodinPorDescarte.class);
        REGISTRO.put("Portal del Infinito", ComodinPorDescarte.class);
        REGISTRO.put("Gros Michel", ComodinPorDescarte.class);
        REGISTRO.put("Cavendish", ComodinPorDescarte.class);
        REGISTRO.put("Manzano Dorado", ComodinPorDescarte.class);
        REGISTRO.put("Estrella Fugaz", ComodinPorDescarte.class);
        REGISTRO.put("Tigre de Bengal", ComodinPorDescarte.class);
        REGISTRO.put("Tornado de Suerte", ComodinPorDescarte.class);
        REGISTRO.put("Flor del Cerezo", ComodinPorDescarte.class);
        REGISTRO.put("Rayo de Fortuna", ComodinPorDescarte.class);
        REGISTRO.put("Locura", ComodinPorDescarte.class);
        REGISTRO.put("Bananas", ComodinPorDescarte.class);
        REGISTRO.put("Destino Explosivo", ComodinPorDescarte.class);
        REGISTRO.put("Suerte Suprema", ComodinPorDescarte.class);
    }

    public static Comodin crearComodin(String nombre, String descripcion, String activacion, int puntos, float multiplicador) {
        Class<? extends Comodin> comodinClass = REGISTRO.get(nombre);
        if (comodinClass == null) {
            throw new IllegalArgumentException("Tipo de comodín desconocido: " + nombre);
        }
        try {
            Constructor<? extends Comodin> constructor = comodinClass.getDeclaredConstructor(String.class, String.class, String.class, int.class, float.class);
            return constructor.newInstance(nombre, descripcion, activacion, puntos, multiplicador);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al crear el comodín", e);
        }
    }
    public static Comodin crearComodinCombo(String nombre, String descripcion, List<Comodin> subComodines) {
        Class<? extends Comodin> comodinClass = REGISTRO.get(nombre);
        if (comodinClass == null) {
            throw new IllegalArgumentException("Tipo de comodín desconocido: " + nombre);
        }
        try {
            Constructor<? extends Comodin> constructor = comodinClass.getDeclaredConstructor(String.class, String.class, List.class);
            return constructor.newInstance(nombre, descripcion, subComodines);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al crear el comodín", e);
        }
    }
}