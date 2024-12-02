package org.example;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Juego {
    private Jugador jugador;
    private List<Ronda> ronda;
    private int cantidadRondas;
    private boolean juegoGanado = false;

    public Juego(Mazo mazo) {
        this.jugador = new Jugador(mazo);
        this.ronda = new ArrayList<>();
        this.cantidadRondas = 3;
    }

    public void setRondas(List<Ronda> rondas) {
        this.ronda = rondas;
        this.cantidadRondas = rondas.size();
    }

    public void jugar(Stage primaryStage) {
        while (!juegoGanado) {
            for (int i = ronda.size() - 1; i >= 0; i--) { // RREVISAR ITERACIÓN DE RONDA
                Ronda currentRonda = ronda.get(i);
                ArrayList<Carta> cartasIniciales = jugador.getMazo().repartirCartas(5);
                int puntajeASuperar = currentRonda.getPuntajeASuperar();
                mostrarRonda(primaryStage, cartasIniciales, puntajeASuperar);
            }
            this.juegoGanado = true;
        }
    }

    private void mostrarRonda(Stage primaryStage, ArrayList<Carta> cartasIniciales, int puntajeASuperar) {
        // Crear un FlowPane para contener las cartas
        FlowPane cartasPane = new FlowPane();

        // Iterar sobre las cartas en el mazo del jugador
        for (Carta carta : cartasIniciales) {
            // Crear la ruta de la imagen de cada carta
            String imagePath = "/cartas/" + carta.getRuta() + ".png";

            // Crear la imagen y el ImageView correspondiente
            Image image = new Image(imagePath);
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(90);
            imageView.setFitHeight(90);

            // Agregar el ImageView al FlowPane
            cartasPane.getChildren().add(imageView);
        }
        cartasPane.setStyle("-fx-padding: 0;-fx-margin: 0;");

        // Crear una carta con la imagen de la parte atrás
        String backImagePath = "/cartas/card_back.png";
        Image backImage = new Image(backImagePath);
        ImageView backImageView = new ImageView(backImage);
        backImageView.setFitWidth(90);
        backImageView.setFitHeight(90);

        // Agregar la carta con la imagen de la parte de atrás al FlowPane (a la derecha)
        cartasPane.getChildren().add(backImageView);

        // Crear un Panel (Panel de la izquierda)
        StackPane leftPanel = new StackPane();
        leftPanel.setStyle("-fx-background-color: gray;");  // Establecer un color de fondo para el panel izquierdo
        leftPanel.setMinWidth(200); // Aproximadamente un cuarto del ancho total

        // Crear un Label para mostrar el puntajeASuperar
        Label puntajeLabel = new Label("Puntaje a superar: " + puntajeASuperar);
        puntajeLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: white; -fx-padding: 10;");

        // Agregar el Label al panel izquierdo
        leftPanel.getChildren().add(puntajeLabel);

        // Crear un StackPane para el contenido principal (Centro verde)
        StackPane contentPane = new StackPane();
        contentPane.setStyle("-fx-background-color: green;");  // Establecer un color de fondo verde

        contentPane.getChildren().add(cartasPane);  // Agregar el FlowPane con las cartas al StackPane

        // Crear un BorderPane para organizar los elementos
        BorderPane layout = new BorderPane();
        layout.setLeft(leftPanel);  // Colocar el panel izquierdo
        layout.setCenter(contentPane);  // Colocar el área principal con color verde en el centro

        // Crear la escena con el BorderPane
        Scene scene = new Scene(layout, 800, 600);
        primaryStage.setTitle("BALATRO");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void PartidaPerdida(){

    }

    public void hacerJugada(){
        jugador.crearJugada();
    }
}
