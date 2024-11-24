package org.example;

import jakarta.json.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class LectorArchivosJson {
    String rutaBalatroJson = "src/main/resources/balatro.json";
    String rutaMazoJson = "src/main/resources/mazo.json";
    String rutaComodinesJson = "src/main/resources/comodines.json";
    String rutaTarotsJson = "src/main/resources/tarots.json";

    public void leerBalatro() throws IOException {

        try (InputStream lector = new FileInputStream(rutaBalatroJson);
             JsonReader jsonReader = Json.createReader(lector)) {

            JsonObject jsonObject = jsonReader.readObject();

            JsonArray rondas = jsonObject.getJsonArray("rondas");
            for (JsonObject ronda : rondas.getValuesAs(JsonObject.class)) {
                System.out.println("Ronda número: " + ronda.getInt("nro"));
                System.out.println("Manos: " + ronda.getInt("manos"));
                System.out.println("Descartes: " + ronda.getInt("descartes"));
                System.out.println("Puntaje a superar: " + ronda.getInt("puntajeASuperar"));

                // tienda
                JsonObject tienda = ronda.getJsonObject("tienda");

                // comodines
                JsonArray comodines = tienda.getJsonArray("comodines");
                for (JsonObject comodin : comodines.getValuesAs(JsonObject.class)) {
                    System.out.println("  Comodín: " + comodin.getString("nombre"));
                    System.out.println("    Descripción: " + comodin.getString("descripcion"));

                    if (comodin.containsKey("comodines")) { //ver linea 307 del json
                        System.out.println("Subcomodines:");
                        JsonArray subcomodines = comodin.getJsonArray("comodines");

                        for (int j = 0; j < subcomodines.size(); j++) {
                            JsonObject subcomodin = subcomodines.getJsonObject(j);
                            String nombre = subcomodin.getString("nombre");
                            String descripcion = subcomodin.getString("descripcion");
                            JsonValue activacion = subcomodin.get("activacion");
                            if (activacion.getValueType() == JsonValue.ValueType.OBJECT) {
                                JsonObject activacionObj = (JsonObject) activacion;
                                System.out.println("Activación: " + activacionObj);
                            } else {
                                System.out.println("Activación: " + activacion.toString());
                            }
                            Map<String, Object> efecto = leerEfecto(subcomodin);
                            int puntos = (int) efecto.get("puntos");
                            float multiplicador = (float) efecto.get("multiplicador");
                            System.out.println("    Efecto: " + efecto);
                        };
                        break;
                    }
                    JsonValue activacion = comodin.get("activacion");
                    if (activacion.getValueType() == JsonValue.ValueType.OBJECT) {
                        JsonObject activacionObj = (JsonObject) activacion;
                        System.out.println("Activación: " + activacionObj);
                    } else {
                        System.out.println("Activación: " + activacion.toString());
                    }
                    Map<String, Object> efecto = leerEfecto(comodin);
                    int puntos = (int) efecto.get("puntos");
                    float multiplicador = (float) efecto.get("multiplicador");
                    System.out.println("    Efecto: " + efecto);
                }

                // tarots
                JsonArray tarots = tienda.getJsonArray("tarots");
                for (JsonObject tarot : tarots.getValuesAs(JsonObject.class)) {
                    String nombre = tarot.getString("nombre");
                    String descripcion = tarot.getString("descripcion");
                    String sobre = tarot.getString("sobre");
                    String ejemplar = tarot.getString("ejemplar");
                    Map<String, Object> efecto = leerEfecto(tarot);
                    int puntos = (int) efecto.get("puntos");
                    float multiplicador = (float) efecto.get("multiplicador");
                    System.out.println("  Tarot: " + nombre);
                    System.out.println("    Descripción: " + descripcion);
                    System.out.println("    Efecto: " + efecto);
                    System.out.println("    Sobre: " + sobre);
                    System.out.println("    Ejemplar: " + ejemplar);
                }

                // Procesar carta
                JsonObject carta = tienda.getJsonObject("carta");
                System.out.println("  Carta: " + carta.getString("nombre"));
                System.out.println("    Palo: " + carta.getString("palo"));
                System.out.println("    Número: " + carta.getString("numero"));
                System.out.println("    Puntos: " + carta.getInt("puntos"));
                System.out.println("    Multiplicador: x" + carta.getString("multiplicador"));

            }
        }
    }

    public void leerMazo() {
        try (InputStream reader = new FileInputStream(rutaMazoJson)) {
            JsonReader jsonReader = Json.createReader(reader);
            JsonObject jsonObject = jsonReader.readObject();

            JsonArray mazo = jsonObject.getJsonArray("mazo");

            for (int i = 0; i < mazo.size(); i++) {
                JsonObject carta = mazo.getJsonObject(i);

                String nombre = carta.getString("nombre");
                String palo = carta.getString("palo");
                String numero = carta.getString("numero");
                int puntos = carta.getInt("puntos");
                String multiplicador = carta.getString("multiplicador");

                System.out.println("Carta " + (i + 1) + ":");
                System.out.println("Nombre: " + nombre);
                System.out.println("Palo: " + palo);
                System.out.println("Número: " + numero);
                System.out.println("Puntos: " + puntos);
                System.out.println("Multiplicador: " + multiplicador);
                System.out.println("=====================================");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void leerComodines(){
        try (InputStream lector = new FileInputStream(rutaComodinesJson);
             JsonReader jsonReader = Json.createReader(lector)) {

            JsonObject jsonObject = jsonReader.readObject();

            //"Al Puntaje"
            JsonObject alPuntaje = jsonObject.getJsonObject("Al Puntaje");
            String descripcion = alPuntaje.getString("descripcion");
            System.out.println("Descripción: " + descripcion);

            JsonArray comodinesAlPuntaje = alPuntaje.getJsonArray("comodines");

            for (JsonObject comodin : comodinesAlPuntaje.getValuesAs(JsonObject.class)) {
                String nombre = comodin.getString("nombre");
                String descripcionComodin = comodin.getString("descripcion");
                String activacion = comodin.getString("activacion");
                Map<String, Object> efecto = leerEfecto(comodin);
                int puntos = (int) efecto.get("puntos");
                float multiplicador = (float) efecto.get("multiplicador");
                System.out.println("Comodín:");
                System.out.println("  Nombre: " + nombre);
                System.out.println("    Efecto: " + efecto);
                System.out.println("  Descripción: " + descripcionComodin);
                System.out.println("  Activación: " + activacion);
            }

            //Bonus por mano jugada
            JsonArray comodinesBonusPorManoJugada = jsonObject.getJsonObject("Bonus por Mano Jugada").getJsonArray("comodines");
            String descripcionBonusPorManoJugada = jsonObject.getJsonObject("Bonus por Mano Jugada").getString("descripcion");
            for (JsonObject comodin : comodinesBonusPorManoJugada.getValuesAs(JsonObject.class)) {
                System.out.println("Comodín: " + comodin.getString("nombre"));
                System.out.println("Descripción: " + comodin.getString("descripcion"));
                String activacion = leerActivacion(comodin);
                System.out.println("Activación: " + activacion);
                Map<String, Object> efecto = leerEfecto(comodin);
                int puntos = (int) efecto.get("puntos");
                float multiplicador = (float) efecto.get("multiplicador");
                System.out.println("Efecto: +" + puntos + " puntos, x" + multiplicador);
                System.out.println();  // Separar comodines
            }

            //Bonus por Descarte
            JsonArray comodinesBonusPorDescarte = jsonObject.getJsonObject("Bonus por Descarte").getJsonArray("comodines");
            String descripcionBonusPorDescarte = jsonObject.getJsonObject("Bonus por Descarte").getString("descripcion");
            for (JsonObject comodin : comodinesBonusPorDescarte.getValuesAs(JsonObject.class)) {
                System.out.println("Comodín: " + comodin.getString("nombre"));
                System.out.println("Descripción: " + comodin.getString("descripcion"));
                String activacion = leerActivacion(comodin);
                System.out.println("Activación: " + activacion);
                Map<String, Object> efecto = leerEfecto(comodin);
                int puntos = (int) efecto.get("puntos");
                float multiplicador = (float) efecto.get("multiplicador");
                System.out.println("Efecto: +" + puntos + " puntos, x" + multiplicador);
                System.out.println();  // Separar comodines
            }

            //Chance de activarse aleatoriamente
            JsonArray comodinesAleatorios = jsonObject.getJsonObject("Chance de activarse aleatoriamente").getJsonArray("comodines");
            String descripcionComodinAleatorios = jsonObject.getJsonObject("Chance de activarse aleatoriamente").getString("descripcion");
            for (JsonObject comodin : comodinesAleatorios.getValuesAs(JsonObject.class)) {
                System.out.println("Comodín: " + comodin.getString("nombre"));
                System.out.println("Descripción: " + comodin.getString("descripcion"));
                String activacion = leerActivacion(comodin);
                System.out.println("Activación: " + activacion);
                Map<String, Object> efecto = leerEfecto(comodin);
                int puntos = (int) efecto.get("puntos");
                float multiplicador = (float) efecto.get("multiplicador");
                System.out.println("Efecto: +" + puntos + " puntos, x" + multiplicador);
                System.out.println();  // Separar comodines
            }

            //Combinación
            JsonArray comodinesPorCombinacion = jsonObject.getJsonObject("Combinación").getJsonArray("comodines");
            String descripcionPorCombinacion = jsonObject.getJsonObject("Combinación").getString("descripcion");
            for (JsonObject comodin : comodinesPorCombinacion.getValuesAs(JsonObject.class)) {
                System.out.println("Comodín: " + comodin.getString("nombre"));
                System.out.println("Descripción: " + comodin.getString("descripcion"));

                JsonArray subComodines = comodin.getJsonArray("comodines");
                for (JsonObject subComodin : subComodines.getValuesAs(JsonObject.class)) {
                    System.out.println("  subComodín: " + subComodin.getString("nombre"));
                    System.out.println("  descripción: " + subComodin.getString("descripcion"));

                    String subActivacion = leerActivacion(subComodin);
                    System.out.println("  Activación: " + subActivacion);

                    Map<String, Object> subEfecto = leerEfecto(subComodin);
                    int subPuntos = (int) subEfecto.get("puntos");
                    float subMultiplicador = (float) subEfecto.get("multiplicador");
                    System.out.println("  Efecto: +" + subPuntos + " puntos, x" + subMultiplicador);
                }
            }

        } catch (IOException e) {
            System.err.println("Error al leer el archivo JSON: " + e.getMessage());}
    }

    public void leerTarots() {
        try (InputStream lector = new FileInputStream(rutaTarotsJson);
             JsonReader jsonReader = Json.createReader(lector)) {
            JsonArray tarots = jsonReader.readObject().getJsonArray("tarots");

            for (JsonObject tarot : tarots.getValuesAs(JsonObject.class)) {
                System.out.println("Tarot: " + tarot.getString("nombre"));
                System.out.println("Descripción: " + tarot.getString("descripcion"));

                JsonObject efecto = tarot.getJsonObject("efecto");
                int puntos = efecto.getInt("puntos");
                double multiplicador = efecto.getJsonNumber("multiplicador").doubleValue();
                System.out.println("Efecto: +" + puntos + " puntos, x" + multiplicador);

                String sobre = tarot.getString("sobre");
                String ejemplar = tarot.getString("ejemplar");
                System.out.println("Sobre: " + sobre);
                System.out.println("Ejemplar: " + ejemplar);

                System.out.println();  // Separar tarots
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo JSON: " + e.getMessage());}
    }
    private Map<String, Object> leerEfecto(JsonObject objeto) {
        if (objeto.containsKey("efecto")) {
            JsonObject efecto = objeto.getJsonObject("efecto");
            int puntos = efecto.getInt("puntos");
            float multiplicador = (float) efecto.getJsonNumber("multiplicador").doubleValue();

            Map<String, Object> resultado = new HashMap<>();
            resultado.put("puntos", puntos);
            resultado.put("multiplicador", multiplicador);
            return resultado;
        } else {
            throw new IllegalArgumentException("El objeto JSON no contiene un efecto válido");
        }
    }
    private static String leerActivacion(JsonObject objeto) {
        if (objeto.containsKey("activacion")) {
            JsonValue activacion = objeto.get("activacion");
            if (activacion.getValueType() == JsonValue.ValueType.OBJECT) {
                JsonObject activacionObj = (JsonObject) activacion;
                return activacionObj.toString();
            } else {
                return activacion.toString();
            }
        } else {
            throw new IllegalArgumentException("El objeto JSON no contiene la clave 'activacion'");
        }
    }
}
