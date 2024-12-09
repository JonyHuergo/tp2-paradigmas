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
//        marcador.setStyle("-fx-border-radius: 30");
        // Crear un FlowPane para contener las cartas
        FlowPane cartasPane = mostrarCartas(cartasIniciales, controller, mazo);

        // Crear un Label para mostrar el puntajeASuperar
        Label puntajeLabel = new Label("Puntaje a superar: " + ronda.getPuntajeASuperar());
        puntajeLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: white; -fx-padding: 10;");

        // Obtener el manoLabel del controller, que ya fue configurado en PantallaJuegoController
        Label manoLabel = controller.getManoLabel();
        manoLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: white; -fx-padding: 10;");

        Label descartesLabel = new Label("Descartes Disponibles: " + jugador.getDescartes());
        descartesLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: white; -fx-padding: 10;");

        Label jugadasLabel = new Label("Jugadas Disponibles: " + jugador.getCantidadJugadas());
        jugadasLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: white; -fx-padding: 10;");


        HBox titleBox = crearTitulo();

        // Crear un VBox para organizar los Labels verticalmente
        VBox leftPanelContent = new VBox(10);  // 10 is the space between the labels
        leftPanelContent.setStyle("-fx-padding: 5;");
        leftPanelContent.getChildren().addAll(titleBox, puntajeLabel, manoLabel, descartesLabel , jugadasLabel ,marcador);

        // Crear un Panel (Panel de la izquierda)
        StackPane leftPanel = new StackPane();
        leftPanel.setStyle("-fx-background-color: gray;");  // Establecer un color de fondo para el panel izquierdo
        leftPanel.setMinWidth(200); // Aproximadamente un cuarto del ancho total

        // Crear un HBox para contener los botones
        HBox botonesBox = new HBox(10); // Espaciado de 10 entre botones
        botonesBox.setAlignment(Pos.BOTTOM_LEFT); // Alinear los botones en la parte inferior izquierda
        botonesBox.setPadding(new Insets(10)); // Espaciado interno

// Crear botón "Descartar"
        Button descartarButton = new Button("Descartar");
        descartarButton.setFont(Font.font("RetroFont", FontWeight.BOLD, 16));

        descartarButton.setStyle("-fx-background-color: #FF4500; -fx-text-fill: white; -fx-padding: 5; -fx-background-radius: 5;");

        descartarButton.setOnAction(event -> {
            controller.descartarCartas(mazo);

        });
        ronda.descontarDescarte();



// Crear botón "Jugar mano"
        Button jugarManoButton = new Button("Jugar Mano");
        jugarManoButton.setFont(Font.font("RetroFont", FontWeight.BOLD, 16));
        jugarManoButton.setStyle("-fx-background-color: yellow; -fx-text-fill: black; -fx-padding: 5; -fx-background-radius: 5;");
        jugarManoButton.setOnAction(event -> {
            controller.jugarMano();
        });

        // Agregar botones al HBox
        botonesBox.getChildren().addAll(descartarButton, jugarManoButton);



        // Agregar los botones al VBox del panel izquierdo (leftPanelContent)
        leftPanelContent.getChildren().add(botonesBox);


        leftPanel.getChildren().add(leftPanelContent);

        // Crear un StackPane para el contenido principal (Centro verde)
        StackPane contentPane = new StackPane();
        contentPane.setStyle("-fx-background-color: green;");  // Establecer un color de fondo verde
        contentPane.setMinHeight(800);
        contentPane.getChildren().add(comodinesPane);
        contentPane.getChildren().add(cartasPane);

        // Crear un BorderPane para organizar los elementos
        BorderPane layout = new BorderPane();
        layout.setLeft(leftPanel); // Colocar el panel izquierdo
        layout.setCenter(contentPane); // Colocar el área principal con color verde en el centro
        // Agregar el BorderPane como único hijo de esta pantalla
        this.getChildren().add(layout);
    }

    private FlowPane mostrarComodines(ArrayList<Comodin> comodines) {
        FlowPane comodinesPane = new FlowPane();

        for (Comodin comodin : comodines) {
            // Crear la ruta de la imagen de cada carta
            String imagePath = comodin.getRuta();

            // Crear la imagen y el ImageView correspondiente
            Image image = new Image(imagePath);
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(90);
            imageView.setFitHeight(90);

            // Agregar el ImageView al FlowPane
            comodinesPane.getChildren().add(imageView);
        }

        // Ajustar el estilo del FlowPane
        comodinesPane.setStyle("-fx-padding: 10; -fx-hgap: -20; -fx-vgap: 0;");

        return comodinesPane;
    }

    private VBox crearMarcador( FlujoJuegoController controller, Jugador jugador, int numeroRonda) {
        // Contenedor principal
        VBox marcador = new VBox();
        // Espaciado entre las cajas
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

        // Round Score
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
        FlowPane cartasPane = new FlowPane();
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
        // Iterar sobre las cartas en el mazo del jugador
        FlowPane cartasPane = new FlowPane();

        for (Carta carta : cartasIniciales) {
            // Crear la ruta de la imagen de cada carta
            String imagePath = carta.getRuta();


            // Crear la imagen y el ImageView correspondiente
            Image image = new Image(imagePath);
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(90);
            imageView.setFitHeight(90);

            // Crear un botón que contiene la carta
            Button cartaButton = new Button();
            cartaButton.setGraphic(imageView);
            cartaButton.setStyle("-fx-background-color: transparent; -fx-padding: -5;");

            // Delegar la lógica del botón al handler
            cartaButton.setOnAction(new CartaButtonHandler(carta, cartaButton, controller, mazo));

            // Agregar el botón al FlowPane
            cartasPane.getChildren().add(cartaButton);
            cartasPane.setStyle("-fx-padding: 10; -fx-hgap: -20; -fx-vgap: 0; -fx-translate-y: 500;");


        }

        return cartasPane;
    }

}

