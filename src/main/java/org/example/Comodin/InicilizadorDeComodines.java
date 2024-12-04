package org.example.Comodin;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InicilizadorDeComodines {
    private static final Map<String, Class<? extends Comodin>> REGISTRO = new HashMap<>();

    static {
        REGISTRO.put("Comodin", ComodinBase.class);
        REGISTRO.put("Caminante", ComodinBase.class);
        REGISTRO.put("Comodin Astuto", ComodinPorManoJugada.class);
        REGISTRO.put("Comodin loco", ComodinPorManoJugada.class);
        REGISTRO.put("Comodin Generoso", ComodinPorManoJugada.class);
        REGISTRO.put("Comodin Arriesgado", ComodinPorManoJugada.class);
        REGISTRO.put("Comodin Abundante", ComodinPorManoJugada.class);
        REGISTRO.put("Comodin Poderoso", ComodinPorManoJugada.class);
        REGISTRO.put("Comodin Afortunado", ComodinPorManoJugada.class);
        REGISTRO.put("Comodin Real", ComodinPorManoJugada.class);
        REGISTRO.put("Comodin Épico", ComodinPorManoJugada.class);
        REGISTRO.put("Bandera", ComodinPorDescarte.class);
        REGISTRO.put("Cumbre Mistica", ComodinPorDescarte.class);
        REGISTRO.put("Descarte Dorado", ComodinPorManoJugada.class);
        REGISTRO.put("Eco de Montaña", ComodinPorDescarte.class);
        REGISTRO.put("Ritual del Valle", ComodinPorDescarte.class);
        REGISTRO.put("Descarte Sagrado", ComodinPorDescarte.class);
        REGISTRO.put("Talisman de Descarte", ComodinPorDescarte.class);
        REGISTRO.put("Portal del Infinito", ComodinPorDescarte.class);
        REGISTRO.put("Gros Michel", ComodinBase.class);
        REGISTRO.put("Cavendish", ComodinBase.class);
        REGISTRO.put("Manzano Dorado", ComodinBase.class);
        REGISTRO.put("Estrella Fugaz", ComodinBase.class);
        REGISTRO.put("Tigre de Bengal", ComodinBase.class);
        REGISTRO.put("Tornado de Suerte", ComodinBase.class);
        REGISTRO.put("Flor del Cerezo", ComodinBase.class);
        REGISTRO.put("Rayo de Fortuna", ComodinBase.class);
        REGISTRO.put("Locura", ComodinCombo.class);
        REGISTRO.put("Bananas", ComodinCombo.class);
        REGISTRO.put("Destino Explosivo", ComodinCombo.class);
        REGISTRO.put("Suerte Suprema", ComodinCombo.class);
    }   //REGISTRO.put("Extasis Salvaje", ComodinCombo.class);

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
            throw new RuntimeException("Error al crear el comodin" + nombre, e);
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
            throw new RuntimeException("Error al crear el comodín" + nombre, e);
        }
    }
}