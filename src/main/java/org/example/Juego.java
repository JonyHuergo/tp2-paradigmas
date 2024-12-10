package org.example;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import org.example.Controladores.PantallaInicioController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// problema 1 al emp[ezar una nueva partida despues de perder o ganar
// problema 2 al pasar de ronda no se vuelve a repartir la mano (menos importante no se nota)

public class Juego {
    private Stage primaryStage;
    private Jugador jugador;
    private List<Ronda> rondas;
    private int numeroRonda;

    public Juego(){}

    /*
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
    */

    public void iniciar(Stage primaryStage){
        this.primaryStage = primaryStage;

        leerArchivo();

        crearPantallaInicio();

        mostrarPantalla();

    }

    public void leerArchivo(){
        LectorArchivosJson lectorArchivosJson = new LectorArchivosJson();
        ArrayList<Carta> cartasLeidas = null;
        try {
            rondas = lectorArchivosJson.leerBalatro();
            cartasLeidas = lectorArchivosJson.leerMazo();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al leer los archivos JSON.");
        }

        // Crea el Mazo y al Jugador con este mazo
        Mazo mazo = new Mazo(cartasLeidas);
        jugador = new Jugador(mazo);

        // Se selecciona la primera ronda para iniciar
        numeroRonda = 0;
        Ronda ronda = rondas.get(numeroRonda);

        // esto de abajo esta muy raro, cambiar por algo del estilo: ronda.modificarJugador(jugador)
        int descartes = ronda.getDescartes();
        jugador.setCantidadDeDescartes(descartes);

        int manos= ronda.getCantidadDeManos();
        jugador.setCantidadDeManos(manos);
    }

    public void crearPantallaInicio(){
        MediaPlayer mediaPlayer = null;

        try {
            primaryStage.getIcons().add(new Image("logo.png"));
            primaryStage.setTitle("BALATRO");
            primaryStage.setResizable(false);

            // Inicia el controlador de la pantalla inicial
            PantallaInicioController controller = new PantallaInicioController(primaryStage, mediaPlayer);
            controller.iniciarPantallaInicio(rondas, numeroRonda, jugador);

        } catch (Exception e) {
            System.err.println("Error al cargar los recursos: " + e.getMessage());
        }
    }

    public void mostrarPantalla(){
        primaryStage.show();
    }

}
