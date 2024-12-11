package org.example;

import jakarta.json.*;
import org.example.Comodin.Comodin;
import org.example.Comodin.InicilizadorDeComodines;
import org.example.Tarot.InicializadorDeTarots;
import org.example.Tarot.Tarot;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

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

                JsonObject tienda = ronda.getJsonObject("tienda");

                List<Comodin> comodinesLeidos = this.obtenerComodines(tienda);

                List<Tarot> tarotsLeidos = obtenerTarots(tienda);

                Carta cartaLeida = obtenerCarta(tienda);

                Tienda tiendaLeida = crearTienda(comodinesLeidos, tarotsLeidos, cartaLeida);
                rondasLeidas.add(new Ronda(nro, manos, descartes, puntajeASuperar, tiendaLeida));
            }
        }
        return rondasLeidas;
    }

    public ArrayList<Carta> leerMazo() {
        ArrayList<Carta> cartasLeidas = new ArrayList<>();
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
                cartasLeidas.add(new Carta(nombre, palo, numero, puntos, multiplicador));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cartasLeidas;
    }

    public void leerComodines() {
        try (InputStream lector = new FileInputStream(rutaComodinesJson);
                JsonReader jsonReader = Json.createReader(lector)) {

            JsonObject jsonObject = jsonReader.readObject();

            // "Al Puntaje"
            JsonObject alPuntaje = jsonObject.getJsonObject("Al Puntaje");
            String descripcion = alPuntaje.getString("descripcion");

            JsonArray comodinesAlPuntaje = alPuntaje.getJsonArray("comodines");

            for (JsonObject comodin : comodinesAlPuntaje.getValuesAs(JsonObject.class)) {
                String nombre = comodin.getString("nombre");
                String descripcionComodin = comodin.getString("descripcion");
                String activacion = comodin.getString("activacion");
                Map<String, Object> efecto = leerEfecto(comodin);
                int puntos = (int) efecto.get("puntos");
                float multiplicador = (float) efecto.get("multiplicador");
            }

            // Bonus por mano jugada
            JsonArray comodinesBonusPorManoJugada = jsonObject.getJsonObject("Bonus por Mano Jugada")
                    .getJsonArray("comodines");
            String descripcionBonusPorManoJugada = jsonObject.getJsonObject("Bonus por Mano Jugada")
                    .getString("descripcion");
            for (JsonObject comodin : comodinesBonusPorManoJugada.getValuesAs(JsonObject.class)) {
                String activacion = leerActivacion(comodin);
                Map<String, Object> efecto = leerEfecto(comodin);
                int puntos = (int) efecto.get("puntos");
                float multiplicador = (float) efecto.get("multiplicador");
            }

            // Bonus por Descarte
            JsonArray comodinesBonusPorDescarte = jsonObject.getJsonObject("Bonus por Descarte")
                    .getJsonArray("comodines");
            String descripcionBonusPorDescarte = jsonObject.getJsonObject("Bonus por Descarte")
                    .getString("descripcion");
            for (JsonObject comodin : comodinesBonusPorDescarte.getValuesAs(JsonObject.class)) {
                String activacion = leerActivacion(comodin);
                Map<String, Object> efecto = leerEfecto(comodin);
                int puntos = (int) efecto.get("puntos");
                float multiplicador = (float) efecto.get("multiplicador");
            }

            // Chance de activarse aleatoriamente
            JsonArray comodinesAleatorios = jsonObject.getJsonObject("Chance de activarse aleatoriamente")
                    .getJsonArray("comodines");
            String descripcionComodinAleatorios = jsonObject.getJsonObject("Chance de activarse aleatoriamente")
                    .getString("descripcion");
            for (JsonObject comodin : comodinesAleatorios.getValuesAs(JsonObject.class)) {
                String activacion = leerActivacion(comodin);
                Map<String, Object> efecto = leerEfecto(comodin);
                int puntos = (int) efecto.get("puntos");
                float multiplicador = (float) efecto.get("multiplicador");
            }

            // Combinación
            JsonArray comodinesPorCombinacion = jsonObject.getJsonObject("Combinación").getJsonArray("comodines");
            String descripcionPorCombinacion = jsonObject.getJsonObject("Combinación").getString("descripcion");
            for (JsonObject comodin : comodinesPorCombinacion.getValuesAs(JsonObject.class)) {
                JsonArray subComodines = comodin.getJsonArray("comodines");
                for (JsonObject subComodin : subComodines.getValuesAs(JsonObject.class)) {

                    String subActivacion = leerActivacion(subComodin);

                    Map<String, Object> subEfecto = leerEfecto(subComodin);
                    int subPuntos = (int) subEfecto.get("puntos");
                    float subMultiplicador = (float) subEfecto.get("multiplicador");
                }
            }

        } catch (IOException e) {
            System.err.println("Error al leer el archivo JSON: " + e.getMessage());
        }
    }

    public void leerTarots() {
        try (InputStream lector = new FileInputStream(rutaTarotsJson);
                JsonReader jsonReader = Json.createReader(lector)) {
            JsonArray tarots = jsonReader.readObject().getJsonArray("tarots");

            for (JsonObject tarot : tarots.getValuesAs(JsonObject.class)) {
                JsonObject efecto = tarot.getJsonObject("efecto");
                int puntos = efecto.getInt("puntos");
                double multiplicador = efecto.getJsonNumber("multiplicador").doubleValue();

                String sobre = tarot.getString("sobre");
                String ejemplar = tarot.getString("ejemplar");
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo JSON: " + e.getMessage());
        }
    }

    private List<Comodin> obtenerComodines(JsonObject jsonObject) {
        List<Comodin> comodinesLeidos = new ArrayList<>();
        JsonArray comodines = jsonObject.getJsonArray("comodines");

        for (JsonObject comodin : comodines.getValuesAs(JsonObject.class)) {
            String nombre = comodin.getString("nombre");
            String descripcion = comodin.getString("descripcion");

            // Verificamos si el comodín tiene subcomodines (de tipo "Combinación")
            if (comodin.containsKey("comodines")) {
                JsonArray subcomodines = comodin.getJsonArray("comodines");
                List<Comodin> subComodinesLeidos = new ArrayList<>();

                // Procesamos los subcomodines
                for (JsonObject subcomodin : subcomodines.getValuesAs(JsonObject.class)) {
                    String subNombre = subcomodin.getString("nombre");
                    String subDescripcion = subcomodin.getString("descripcion");
                    String subActivacion = leerActivacion(subcomodin);
                    Map<String, Object> subEfecto = leerEfecto(subcomodin);
                    int subPuntos = (int) subEfecto.get("puntos");
                    float subMultiplicador = (float) subEfecto.get("multiplicador");

                    subComodinesLeidos.add(InicilizadorDeComodines.crearComodin(subNombre, subDescripcion,
                            subActivacion, subPuntos, subMultiplicador));
                }

                // Si el comodín es una combinación, lo agregamos como un combo
                comodinesLeidos.add(InicilizadorDeComodines.crearComodinCombo(nombre, descripcion, subComodinesLeidos));
            } else {
                // Si no tiene subcomodines, se procesa como un comodín normal
                String activacion = leerActivacion(comodin);
                Map<String, Object> efecto = leerEfecto(comodin);
                int puntos = (int) efecto.get("puntos");
                float multiplicador = (float) efecto.get("multiplicador");

                comodinesLeidos.add(
                        InicilizadorDeComodines.crearComodin(nombre, descripcion, activacion, puntos, multiplicador));
            }
        }

        return comodinesLeidos;
    }

    private List<Tarot> obtenerTarots(JsonObject jsonObject) {
        List<Tarot> tarotsLeidos = new ArrayList<>();
        JsonArray tarots = jsonObject.getJsonArray("tarots");
        for (JsonObject tarot : tarots.getValuesAs(JsonObject.class)) {
            String nombre = tarot.getString("nombre");
            String descripcion = tarot.getString("descripcion");
            String sobre = tarot.getString("sobre");
            String ejemplar = tarot.getString("ejemplar");
            Map<String, Object> efecto = leerEfecto(tarot);
            int puntos = (int) efecto.get("puntos");
            float multiplicador = (float) efecto.get("multiplicador");
            tarotsLeidos
                    .add(InicializadorDeTarots.crearTarot(nombre, descripcion, sobre, ejemplar, puntos, multiplicador));
        }
        return tarotsLeidos;
    }

    private Carta obtenerCarta(JsonObject jsonObject) {
        JsonObject carta = jsonObject.getJsonObject("carta");
        String nombre = carta.getString("nombre");
        String palo = carta.getString("palo");
        String numero = carta.getString("numero");
        int puntos = carta.getInt("puntos");
        String mult = carta.getString("multiplicador");

        Carta cartaLeida = new Carta(nombre, palo, numero, puntos, mult);
        return cartaLeida;
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
                Iterator<String> claves = activacionObj.keySet().iterator();
                String segundaClave = claves.next();
                String valor = activacionObj.get(segundaClave).toString();
                return valor;
            } else {
                return activacion.toString();
            }
        } else {
            throw new IllegalArgumentException("El objeto JSON no contiene la clave 'activacion'");
        }
    }

    public Tienda crearTienda(List<Comodin> comodinesLeidos, List<Tarot> tarotsLeidos, Carta cartaLeida) {
        return new Tienda(comodinesLeidos, tarotsLeidos, cartaLeida);
    }
}
