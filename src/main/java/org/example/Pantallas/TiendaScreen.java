package org.example.Pantallas;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import org.example.*;
import org.example.Controladores.PantallaJuegoController;
import org.example.Handlers.*;
import org.example.Comodin.Comodin;
import org.example.Tarot.Tarot;

import java.util.List;

public class TiendaScreen extends VBox {

    private List<Comodin> comodines;
    private List<Tarot> tarots;
    private Carta carta;
    private Button selectedButton;  // Para almacenar el botón seleccionado


    public TiendaScreen(Tienda tienda, Stage stage, MediaPlayer mediaPlayer, Mazo mazo, int puntajeASuperar, Jugador jugador) {
        super();
        this.setStyle("-fx-background-color: green;");
        this.setSpacing(10);
        this.setPadding(new javafx.geometry.Insets(20));

        // Título
        Label titulo = new Label("Tienda");
        titulo.setStyle("-fx-font-size: 32px; -fx-text-fill: white; -fx-font-weight: bold;");
        titulo.setAlignment(Pos.CENTER);
        this.getChildren().add(titulo);

        this.comodines = tienda.obtenerComodines();
        this.tarots = tienda.obtenerTarots();
        this.carta = tienda.obtenerCarta();

        // FlowPane para comodines
        FlowPane comodinesPane = new FlowPane();
        comodinesPane.setHgap(10);
        comodinesPane.setVgap(10);
        comodinesPane.setAlignment(Pos.CENTER);
        for (Comodin comodin : comodines) {
            String imagePath = "/Comodines/" + comodin.getRuta() + ".png";
            Image image = new Image(imagePath);
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(150);
            imageView.setFitHeight(150);

            Button cartaButton = new Button();
            cartaButton.setGraphic(imageView);
            cartaButton.setStyle("-fx-background-color: transparent; -fx-padding: -5;");

            // Acción de selección de comodín
            cartaButton.setOnAction(event -> seleccionarCarta(stage, mediaPlayer,comodin, cartaButton, mazo, jugador, puntajeASuperar));
            comodinesPane.getChildren().add(cartaButton);
        }
        this.getChildren().add(comodinesPane);

        // FlowPane para tarots
        FlowPane tarotPane = new FlowPane();
        tarotPane.setHgap(10);
        tarotPane.setVgap(10);
        tarotPane.setAlignment(Pos.CENTER);
        for (Tarot tarot : tarots) {
            String imagePath = tarot.getRuta();
            Image image = new Image(imagePath);
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(100);
            imageView.setFitHeight(150);

            Button cartaButton = new Button();
            cartaButton.setGraphic(imageView);
            cartaButton.setStyle("-fx-background-color: transparent; -fx-padding: -5;");

            // Acción de selección de tarot
            cartaButton.setOnAction(event -> seleccionarCarta(stage, mediaPlayer, tarot, cartaButton, mazo, jugador, puntajeASuperar));
            tarotPane.getChildren().add(cartaButton);
        }
        this.getChildren().add(tarotPane);

        // FlowPane para carta
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

        // Acción de selección de carta
        cartaButton.setOnAction(event -> seleccionarCarta(stage, mediaPlayer, carta, cartaButton, mazo, jugador, puntajeASuperar));

        cartaPane.getChildren().add(cartaButton);
        this.getChildren().add(cartaPane);

        // Botón avanzar
        Button avanzarButton = new Button("Avanzar");
        avanzarButton.setStyle("-fx-font-size: 16px; -fx-background-color: yellow;");


        avanzarButton.setOnAction(new AvanzarButtonHandler(stage, mediaPlayer, mazo, puntajeASuperar));
        this.setAlignment(Pos.CENTER);
        this.getChildren().add(avanzarButton);
    }

    // Método para seleccionar una carta (comodín, tarot o carta)
    private void seleccionarCarta(Stage stage, MediaPlayer mediaPlayer, Comprable seleccion, Button cartaButton, Mazo mazo, Jugador jugador, int puntajeASuperar) {
        // Si ya se ha seleccionado una carta, deselecciona la carta previamente seleccionada
        if (selectedButton != null) {
            // Restaura el estilo de la carta previamente seleccionada
            selectedButton.setOpacity(1.0);  // Restaura la opacidad
            selectedButton.setStyle("-fx-background-color: transparent; -fx-padding: -5;");  // Restaura el estilo
        }

        // Actualiza la carta seleccionada
        selectedButton = cartaButton;
        selectedButton.setOpacity(1.0);  // Asegura que la carta seleccionada no tenga opacidad reducida
        selectedButton.setStyle("-fx-background-color: transparent; -fx-padding: -5; -fx-effect: innershadow( gaussian , rgba(255,255,255,0.8), 20, 0.5, 0, 0);");  // Agrega un brillo

        // Aplica opacidad a todas las cartas que no sean la seleccionada
        deseleccionarTodasLasCartas();

        EventHandler<ActionEvent> handler = seleccion.crearHandler(new PantallaJuegoController(stage, mediaPlayer),mazo, jugador, puntajeASuperar);
        cartaButton.setOnAction(handler);


        // Aquí podrías guardar el objeto 'seleccion' si necesitas usarlo después (comodín, tarot o carta)
        // Por ejemplo:
        // this.cartaSeleccionada = seleccion; // O comodín o tarot
    }

    // Método para deseleccionar todas las cartas (opacarlas)
    private void deseleccionarTodasLasCartas() {
        // Desactiva la opacidad en todas las cartas (comodines, tarots y la carta)
        for (Node node : this.getChildren()) {
            if (node instanceof FlowPane) {
                FlowPane pane = (FlowPane) node;
                for (Node subNode : pane.getChildren()) {
                    if (subNode instanceof Button && subNode != selectedButton) {
                        subNode.setOpacity(0.5);  // Baja la opacidad de las demás cartas
                    }
                }
            }
        }
    }
}





