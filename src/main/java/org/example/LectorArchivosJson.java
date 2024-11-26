package org.example;

import jakarta.json.*;
import org.example.Comodin.Activacion;
import org.example.Comodin.Comodin;
import org.example.Comodin.InicilizadorDeComodines;
import org.example.Tarot.InicializadorDeTarots;
import org.example.Tarot.Tarot;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LectorArchivosJson {
    String rutaBalatroJson = "src/main/resources/balatro.json";
    String rutaMazoJson = "src/main/resources/mazo.json";
    String rutaComodinesJson = "src/main/resources/comodines.json";
    String rutaTarotsJson = "src/main/resources/tarots.json";

    public List leerBalatro() throws IOException {
        List<Ronda> rondasLeidas = new ArrayList<>();
        try (InputStream lector = new FileInputStream("src/main/resources/balatro.json");
             JsonReader jsonReader = Json.createReader(lector)) {

            JsonObject jsonObject = jsonReader.readObject();

            JsonArray rondas = jsonObject.getJsonArray("rondas");
            for (JsonObject ronda : rondas.getValuesAs(JsonObject.class)) {
                int nro = ronda.getInt("nro");
                int manos = ronda.getInt("manos");
                int descartes = ronda.getInt("descartes");
                int puntajeASuperar = ronda.getInt("puntajeASuperar");

                // tienda
                JsonObject tienda = ronda.getJsonObject("tienda");

                // comodines
                List<Comodin> comodinesLeidos = new ArrayList<>();
                comodinesLeidos = this.obtenerComodines(tienda);
                // tarots
                List<Tarot> tarotsLeidos = new ArrayList<>();
                JsonArray tarots = tienda.getJsonArray("tarots");
                for (JsonObject tarot : tarots.getValuesAs(JsonObject.class)) {
                    String nombre = tarot.getString("nombre");
                    String descripcion = tarot.getString("descripcion");
                    String sobre = tarot.getString("sobre");
                    String ejemplar = tarot.getString("ejemplar");
                    Map<String, Object> efecto = leerEfecto(tarot);
                    int puntos = (int) efecto.get("puntos");
                    float multiplicador = (float) efecto.get("multiplicador");
                    tarotsLeidos.add(InicializadorDeTarots.crearTarot(nombre, descripcion, sobre, ejemplar, puntos, multiplicador));
                }
                /*
                tarotsLeidos = obtenerTarots()*/

                // Procesar carta
                JsonObject carta = tienda.getJsonObject("carta");
                String nombre = carta.getString("nombre");
                String palo = carta.getString("palo");
                String numero = carta.getString("numero");
                int puntos = carta.getInt("puntos");
                String mult = carta.getString("multiplicador");

                Carta cartaLeida = new Carta(nombre, palo, numero, puntos, mult);

                Tienda tiendaLeida = new Tienda(comodinesLeidos, tarotsLeidos, cartaLeida);
                rondasLeidas.add(new Ronda(nro, manos, descartes, puntajeASuperar, tiendaLeida));
            }
        }
        return rondasLeidas;
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
                System.out.println();  // separar comodines
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
                System.out.println();  // separar comodines
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
                System.out.println();  // separar comodines
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

                System.out.println();  // separar tarots
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo JSON: " + e.getMessage());}
    }

    private List<Comodin> obtenerComodines(JsonObject jsonObject) {
        List<Comodin> comodinesLeidos = new ArrayList<>();
        JsonArray comodines = jsonObject.getJsonArray("comodines");
        for (JsonObject comodin : comodines.getValuesAs(JsonObject.class)) {
            String nombre = comodin.getString("nombre");
            String descripcion = comodin.getString("descripcion");

            if (comodin.containsKey("comodines")) { //ver linea 307 del json
                JsonArray subcomodines = comodin.getJsonArray("comodines");
                List<Comodin> subComodinesLeidos = new ArrayList<>();
                for (int j = 0; j < subcomodines.size(); j++) {
                    JsonObject subcomodin = subcomodines.getJsonObject(j);
                    String subNombre = subcomodin.getString("nombre");
                    String subDescripcion = subcomodin.getString("descripcion");
                    String subActivacion = leerActivacion(subcomodin);
                    Map<String, Object> efecto = leerEfecto(subcomodin);
                    int subPuntos = (int) efecto.get("puntos");
                    float subMultiplicador = (float) efecto.get("multiplicador");
                    System.out.println("    Efecto: " + efecto);

                    subComodinesLeidos.add(InicilizadorDeComodines.crearComodin(nombre, descripcion, subActivacion, subPuntos, subMultiplicador));
                };
                comodinesLeidos.add(InicilizadorDeComodines.crearComodinCombo(nombre, descripcion, subComodinesLeidos));
                break;
            }
            String activacion = leerActivacion(comodin);
            Map<String, Object> efecto = leerEfecto(comodin);
            int puntos = (int) efecto.get("puntos");
            float multiplicador = (float) efecto.get("multiplicador");
            comodinesLeidos.add(InicilizadorDeComodines.crearComodin(nombre, descripcion, activacion, puntos, multiplicador));
        }
        return comodinesLeidos;
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
