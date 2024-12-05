package org.example.Pantallas;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import org.example.Controladores.PantallaJuegoController;
import org.example.Handlers.AvanzarButtonHandler;
import org.example.Handlers.CompraCartaHandler;
import org.example.Handlers.CompraComodinHandler;
import org.example.Handlers.CompraTarotHandler;
import org.example.Jugador;
import org.example.Mazo;
import org.example.Tienda;
import org.example.Carta;
import org.example.Comodin.Comodin;
import org.example.Tarot.Tarot;

import java.util.List;

public class TiendaScreen extends VBox {

    private List<Comodin> comodines;
    private List<Tarot> tarots;
    private Carta carta;


    public TiendaScreen(Tienda tienda, Stage stage, MediaPlayer mediaPlayer, Mazo mazo, int puntajeASuperar, Jugador jugador) {
        super();
        this.setStyle("-fx-background-color: green;"); // Fondo verde
        this.setSpacing(10); // Espaciado entre elementos
        this.setPadding(new javafx.geometry.Insets(20));

        // Título
        Label titulo = new Label("Tienda");
        titulo.setStyle("-fx-font-size: 32px; -fx-text-fill: white; -fx-font-weight: bold;");
        titulo.setAlignment(Pos.CENTER);

        // Añadir el título al VBox
        this.getChildren().add(titulo);

        this.comodines = tienda.obtenerComodines();
        this.tarots = tienda.obtenerTarots();
        this.carta = tienda.obtenerCarta();

        FlowPane comodinesPane = new FlowPane();
        comodinesPane.setHgap(10); // Espaciado horizontal entre botones
        comodinesPane.setVgap(10); // Espaciado vertical entre botones
        comodinesPane.setAlignment(Pos.CENTER);

        // Añadir botones al FlowPane
        for (Comodin comodin : comodines) {
            String imagePath = "/Comodines/" + comodin.getRuta() + ".png";
            System.out.println(comodin.getRuta());// Ruta de la imagen

            Image image = new Image(imagePath);
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(150);
            imageView.setFitHeight(150);

            Button cartaButton = new Button();
            cartaButton.setGraphic(imageView);
            cartaButton.setStyle("-fx-background-color: transparent; -fx-padding: -5;");

            cartaButton.setOnAction(new CompraComodinHandler(new PantallaJuegoController(stage, mediaPlayer), mazo, puntajeASuperar, comodin, jugador));

            comodinesPane.getChildren().add(cartaButton); // Añadir botón al FlowPane
        }

        // Añadir el FlowPane al TiendaScreen
        this.getChildren().add(comodinesPane);

        FlowPane tarotPane = new FlowPane();
        tarotPane.setHgap(10); // Espaciado horizontal entre botones
        tarotPane.setVgap(10); // Espaciado vertical entre botones
        tarotPane.setAlignment(Pos.CENTER);

        for (Tarot tarot : tarots) {
            String imagePath = tarot.getRuta(); // Ruta de la imagen

            Image image = new Image(imagePath);
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(100);
            imageView.setFitHeight(150);

            Button cartaButton = new Button();
            cartaButton.setGraphic(imageView);
            cartaButton.setStyle("-fx-background-color: transparent; -fx-padding: -5;");

            cartaButton.setOnAction(new CompraTarotHandler(tarot, jugador));

            tarotPane.getChildren().add(cartaButton); // Añadir botón al FlowPane
        }

        // Añadir el FlowPane al TiendaScreen
        this.getChildren().add(tarotPane);

        FlowPane cartaPane = new FlowPane();
        cartaPane.setHgap(10);
        cartaPane.setVgap(10);
        cartaPane.setAlignment(Pos.CENTER);

        String imagePath = "/cartas/" + carta.getRuta() + ".png";

        Image image = new Image(imagePath);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(150);
        imageView.setFitHeight(150);

        Button cartaButton = new Button();
        cartaButton.setGraphic(imageView);
        cartaButton.setStyle("-fx-background-color: transparent; -fx-padding: -5;");

        cartaButton.setOnAction(new CompraCartaHandler(carta, mazo));

        cartaPane.getChildren().add(cartaButton); // Añadir botón al FlowPane

        // Añadir el FlowPane al TiendaScreen
        this.getChildren().add(cartaPane);

        Button avanzarButton = new Button("Avanzar");
        avanzarButton.setStyle("-fx-font-size: 16px; -fx-background-color: yellow;");
        avanzarButton.setOnAction(new AvanzarButtonHandler(stage, mediaPlayer, mazo, puntajeASuperar)); // Asignamos el manejador

        // Centramos el botón y lo añadimos al final
        this.setAlignment(Pos.CENTER);
        this.getChildren().add(avanzarButton);
    }
}
