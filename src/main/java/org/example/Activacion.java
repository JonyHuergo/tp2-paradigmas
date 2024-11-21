package org.example;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Activacion {
    private String tipo;
    private String valor;

    @JsonCreator
    public Activacion(JsonNode activacionNode) {
        if (activacionNode.isTextual()) {
            // Si el nodo es un texto (caso de un String simple)
            this.tipo = "String";
            this.valor = activacionNode.asText();
        } else if (activacionNode.isObject()) {
            // Si el nodo es un objeto (caso de un Map o objeto JSON)
            ObjectNode objectNode = (ObjectNode) activacionNode;
            // Asumimos que el objeto tiene una sola propiedad
            this.tipo = objectNode.fieldNames().next();  // Tomamos la clave
            this.valor = objectNode.get(this.tipo).asText();  // Tomamos el valor
        }
    }
}