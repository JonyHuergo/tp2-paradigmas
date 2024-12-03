package org.example.Pantallas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class PantallaInicioScreen extends VBox {
    public Button playButton;
    public Button optionsButton;
    public Button collectionButton;
    public Button exitButton;

    public PantallaInicioScreen() {
        StackPane root = new StackPane();
        Image bgImage = new Image("background.png", 800, 500, false, true);

        BackgroundImage backgroundImage = new BackgroundImage(
                bgImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(800, 600, false, false, true, false)
        );

        ImageView backgroundView = new ImageView(new Image("background.png"));
        backgroundView.setFitWidth(800);
        backgroundView.setFitHeight(600);

        GaussianBlur blurEffect = new GaussianBlur(10); // Cambia el valor según la intensidad deseada
        backgroundView.setEffect(blurEffect);

        root.getChildren().add(0, backgroundView);

        root.setBackground(new Background(backgroundImage));

        // Carta que aparece en el título
        ImageView cardImage = new ImageView(new Image("cartas/Picas_As.png"));
        cardImage.setFitWidth(120);
        cardImage.setFitHeight(120);

        // Parte del título antes de la segunda "A"
        Text firstPart = new Text("BAL");
        firstPart.setFont(Font.font("RetroFont", FontWeight.BOLD, 100));
        firstPart.setFill(Color.WHITE);

        // Parte del título después de la segunda "A"
        Text secondPart = new Text("TRO");
        secondPart.setFont(Font.font("RetroFont", FontWeight.BOLD, 100));
        secondPart.setFill(Color.WHITE);

        // Botones
        playButton = createStyledButton("/Botones/Play.png", 120, 55);
        optionsButton = createStyledButton("/Botones/Options.png", 150, 55);
        collectionButton = createStyledButton("/Botones/Collection.png", 150, 60);
        exitButton = createStyledButton("/Botones/Exit.png", 120, 55);


        // Diseño para los botones
        HBox buttonsBox = new HBox(10, playButton, optionsButton, collectionButton);
        buttonsBox.setAlignment(Pos.CENTER);
        HBox exitBox = new HBox(exitButton);
        exitBox.setAlignment(Pos.BOTTOM_CENTER);



        BackgroundFill backgroundFill = new BackgroundFill(
                Color.web("#2775b7"),
                new CornerRadii(30),
                Insets.EMPTY
        );

        VBox greyBox = new VBox(10, buttonsBox);
        greyBox.setAlignment(Pos.CENTER);
        Background background = new Background(backgroundFill);
        greyBox.setBackground(background);
        greyBox.setPadding(new Insets(2)); // Padding de la caja gris
        greyBox.setMaxWidth(300);

        // Diseño del título: poner la carta entre "BAL" y "TRO"
        HBox titleBox = new HBox(0, firstPart, cardImage, secondPart);
        titleBox.setAlignment(Pos.TOP_CENTER);

        VBox layout = new VBox(50, titleBox, greyBox, exitBox);
        layout.setAlignment(Pos.CENTER);

        this.getChildren().addAll(root);
        root.getChildren().add(layout);

        // Configuración de la escena

    }

    private Button createStyledButton(String imagePath, double width, double height) {
        Button button = new Button();

        // Cargar la imagen
        if (imagePath != null && !imagePath.isEmpty()) {
            Image buttonImage = new Image(getClass().getResourceAsStream(imagePath));
            ImageView buttonImageView = new ImageView(buttonImage);

            // Ajustar tamaño de la imagen
            buttonImageView.setFitWidth(width);
            buttonImageView.setFitHeight(height);


            // Configurar la imagen como gráfico del botón
            button.setGraphic(buttonImageView);
        }

        button.setStyle("-fx-background-radius: 30; " +
                "-fx-border-radius: 30; " +
                "-fx-border-width: 2px; " +
                "-fx-border-color: transparent; " +
                "-fx-background-color: #2775b7;");

        button.setOnMouseEntered(e -> button.setStyle(
                "-fx-background-radius: 30; " +
                        "-fx-border-radius: 30; " +
                        "-fx-border-width: 2px; " +
                        "-fx-border-color: transparent; " +
                        "-fx-background-color: #2775b7; " +
                        "-fx-scale-x: 1.1; -fx-scale-y: 1.1;"));

        button.setOnMouseExited(e -> button.setStyle(
                "-fx-background-radius: 30; " +
                        "-fx-border-radius: 30; " +
                        "-fx-border-width: 2px; " +
                        "-fx-border-color: transparent; " +
                        "-fx-background-color: #2775b7; " +    // Fondo original
                        "-fx-scale-x: 1.0; -fx-scale-y: 1.0;"));

        return button;
    }

//
//    private void handlePlay() {
//        LectorArchivosJson lectorArchivosJson = new LectorArchivosJson();
//        try {
//            List<Ronda> rondas = lectorArchivosJson.leerBalatro();
//            ArrayList<Carta> mazo = lectorArchivosJson.leerMazo();
//
//            Juego juego = new Juego(new Mazo(mazo));
//            juego.setRondas(rondas);
//            System.out.println("Juego iniciado.");
////            juego.jugar(primaryStage);
//            System.out.println("Juego terminado.");
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("Error al leer los archivos JSON.");
//        }
//    }
//
//    private void handleOptions() {
//        // TODO: Implementar funcionalidad de botón opciones?
//        System.out.println("Botón Options");
//    }
//
//    private void handleCollection() {
//        // TODO: Implementar funcionalidad de botón colección?
//        System.out.println("Botón Collection");
//    }
}

