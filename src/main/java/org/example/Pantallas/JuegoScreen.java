package org.example.Pantallas;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import org.example.Comodin.Comodin;
import org.example.Handlers.CartaButtonHandler;
import org.example.Controladores.FlujoJuegoController;
import org.example.Carta;
import org.example.Jugador;
import org.example.Mazo;
import org.example.Ronda;

import java.util.ArrayList;
import java.util.List;

public class JuegoScreen extends VBox {
    public JuegoScreen(ArrayList<Carta> cartasIniciales, List<Ronda> rondas, int numeroRonda, FlujoJuegoController controller, Mazo mazo, Jugador jugador, ArrayList<Comodin> comodines) {
        super();

        Ronda ronda = rondas.get(numeroRonda);
        FlowPane comodinesPane = mostrarComodines(comodines);

        VBox marcador = crearMarcador(controller, jugador, numeroRonda);
        FlowPane cartasPane = mostrarCartas(cartasIniciales, controller, mazo);


        Label puntajeLabel = new Label("Puntaje a superar: " + ronda.getPuntajeASuperar());
        puntajeLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: white; -fx-padding: 10;");

        Label manoLabel = controller.getManoLabel();
        manoLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: white; -fx-padding: 10;");

        Label descartesLabel = new Label("Descartes Disponibles: " + jugador.getDescartes());
        descartesLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: white; -fx-padding: 10;");

        Label jugadasLabel = new Label("Jugadas Disponibles: " + jugador.getCantidadJugadas());
        jugadasLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: white; -fx-padding: 10;");


        HBox titleBox = crearTitulo();

        VBox leftPanelContent = new VBox(10);
        leftPanelContent.setStyle("-fx-padding: 5;");
        leftPanelContent.getChildren().addAll(titleBox, puntajeLabel, manoLabel, descartesLabel , jugadasLabel ,marcador);


        StackPane leftPanel = new StackPane();
        leftPanel.setStyle("-fx-background-color: gray;");
        leftPanel.setMinWidth(200);


        HBox botonesBox = new HBox(10);
        botonesBox.setAlignment(Pos.BOTTOM_LEFT);
        botonesBox.setPadding(new Insets(10));


        Button descartarButton = new Button("Descartar");
        descartarButton.setFont(Font.font("RetroFont", FontWeight.BOLD, 16));

        descartarButton.setStyle("-fx-background-color: #FF4500; -fx-text-fill: white; -fx-padding: 5; -fx-background-radius: 5;");

        descartarButton.setOnAction(event -> {
            controller.descartarCartas(mazo);

        });
        ronda.descontarDescarte();



        Button jugarManoButton = new Button("Jugar Mano");
        jugarManoButton.setFont(Font.font("RetroFont", FontWeight.BOLD, 16));
        jugarManoButton.setStyle("-fx-background-color: yellow; -fx-text-fill: black; -fx-padding: 5; -fx-background-radius: 5;");
        jugarManoButton.setOnAction(event -> {
            controller.jugarMano();
        });


        botonesBox.getChildren().addAll(descartarButton, jugarManoButton);



        leftPanelContent.getChildren().add(botonesBox);


        leftPanel.getChildren().add(leftPanelContent);

        StackPane contentPane = new StackPane();
        contentPane.setStyle("-fx-background-color: green;");
        contentPane.setMinHeight(800);
        contentPane.getChildren().add(comodinesPane);
        contentPane.getChildren().add(cartasPane);


        BorderPane layout = new BorderPane();
        layout.setLeft(leftPanel);
        layout.setCenter(contentPane);
        this.getChildren().add(layout);
    }

    private FlowPane mostrarComodines(ArrayList<Comodin> comodines) {
        FlowPane comodinesPane = new FlowPane();

        for (Comodin comodin : comodines) {
            String imagePath = comodin.getRuta();

            Image image = new Image(imagePath);
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(90);
            imageView.setFitHeight(90);

            comodinesPane.getChildren().add(imageView);
        }

        comodinesPane.setStyle("-fx-padding: 10; -fx-hgap: -20; -fx-vgap: 0;");

        return comodinesPane;
    }

    private VBox crearMarcador( FlujoJuegoController controller, Jugador jugador, int numeroRonda) {
        VBox marcador = new VBox();
        marcador.setStyle("-fx-background-color: #202020;");
        marcador.setAlignment(Pos.TOP_CENTER);
        marcador.setStyle(
                "-fx-background-color: #333333; " +  // Fondo oscuro
                        "-fx-background-radius: 15; " +     // Bordes redondeados
                        "-fx-padding: 10; " +               // Espaciado interno
                        "-fx-border-color: white; " +       // Borde blanco
                        "-fx-border-radius: 15; " +         // Borde redondeado
                        "-fx-border-width: 2;"              // Ancho del borde
        );

        VBox roundScoreBox = new VBox();
        roundScoreBox.setAlignment(Pos.CENTER);

        Label roundLabel = new Label("Round Score");
        roundLabel.setFont(Font.font("RetroFont", 18));
        roundLabel.setTextFill(Color.WHITE);

        Label roundScore = new Label(Float.toString(jugador.evaluarJugadas(numeroRonda)));
        roundScore.setFont(Font.font("RetroFont", 22));
        roundScore.setTextFill(Color.WHITE);
        roundScore.setStyle("-fx-background-color: #333333; -fx-padding: 5; -fx-background-radius: 5;");

        roundScoreBox.getChildren().addAll(roundLabel, roundScore);

        // Puntaje azul

        Label blueScore = controller.getPuntajeLabel();
        blueScore.setFont(Font.font("RetroFont", 24));
        blueScore.setTextFill(Color.WHITE);
        blueScore.setStyle("-fx-background-color: #0066FF; -fx-padding: 10; -fx-background-radius: 5;");

        // Texto "X"
        Label versusLabel = new Label("X");
        versusLabel.setFont(Font.font("RetroFont", 24));
        versusLabel.setTextFill(Color.WHITE);

        // Puntaje rojo
        Label redScore = controller.getMultiplicadorLabel();
        redScore.setFont(Font.font("RetroFont", 24));
        redScore.setTextFill(Color.WHITE);
        redScore.setStyle("-fx-background-color: #FF0000; -fx-padding: 10; -fx-background-radius: 5;");

        // Panel de puntajes
        HBox scoresBox = new HBox(10);
        scoresBox.setAlignment(Pos.CENTER);
        scoresBox.setStyle(
                "-fx-background-color: #333333; " +
                        "-fx-background-radius: 15; " +
                        "-fx-padding: 10; " +
                        "-fx-border-color: white; " +
                        "-fx-border-radius: 15; " +
                        "-fx-border-width: 2;"
        );
        scoresBox.getChildren().addAll(blueScore, versusLabel, redScore);

        marcador.getChildren().addAll(roundScoreBox, scoresBox);

        return marcador;
    }

    public HBox crearTitulo() {
        ImageView cardImage = new ImageView(new Image("cartas/Picas_As.png"));
        cardImage.setFitWidth(45);
        cardImage.setFitHeight(45);

        Text firstPart = new Text("BAL");
        firstPart.setFont(Font.font("RetroFont", FontWeight.BOLD, 50));
        firstPart.setFill(Color.WHITE);

        Text secondPart = new Text("TRO");
        secondPart.setFont(Font.font("RetroFont", FontWeight.BOLD, 50));
        secondPart.setFill(Color.WHITE);

        HBox titleBox = new HBox(firstPart,cardImage, secondPart);
        titleBox.setSpacing(5);
        titleBox.setAlignment(Pos.CENTER_LEFT);
        titleBox.setStyle("-fx-padding: 10;");

        return titleBox;
    }

    public FlowPane mostrarCartas(ArrayList<Carta> cartasIniciales,  FlujoJuegoController controller,Mazo mazo){
        FlowPane cartasPane = new FlowPane();

        for (Carta carta : cartasIniciales) {
            String imagePath = carta.getRuta();


            Image image = new Image(imagePath);
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(90);
            imageView.setFitHeight(90);


            Button cartaButton = new Button();
            cartaButton.setGraphic(imageView);
            cartaButton.setStyle("-fx-background-color: transparent; -fx-padding: -5;");


            cartaButton.setOnAction(new CartaButtonHandler(carta, cartaButton, controller, mazo));


            cartasPane.getChildren().add(cartaButton);
            cartasPane.setStyle("-fx-padding: 10; -fx-hgap: -20; -fx-vgap: 0; -fx-translate-y: 500;");


        }

        return cartasPane;
    }

}

