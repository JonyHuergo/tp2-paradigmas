package org.example;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            // Icono
            primaryStage.getIcons().add(new Image("logo.png")); // Usar la ruta de tu imagen

            // Panel raíz
            StackPane root = new StackPane();

            // Styling del fondo
            Image bgImage = new Image("background.png", 800, 600, false, true);
            BackgroundImage backgroundImage = new BackgroundImage(
                    bgImage,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    new BackgroundSize(800, 600, false, false, true, false)
            );
            root.setBackground(new Background(backgroundImage));

            // Styling del título
            Text title = new Text("BALATRO");
            title.setFont(Font.font("RetroFont", FontWeight.BOLD, 60));
            title.setFill(Color.WHITE);

            // Carta que aparece en el título
            ImageView cardImage = new ImageView(new Image("/card.png"));
            cardImage.setFitWidth(60);
            cardImage.setFitHeight(80);

            // Botones
            Button playButton = createStyledButton("PLAY", "#0078D7");
            Button optionsButton = createStyledButton("OPTIONS", "#FFA500");
            Button collectionButton = createStyledButton("COLLECTION", "#32CD32");

            // Event handlers de los botones
            playButton.setOnAction(e -> handlePlay());
            optionsButton.setOnAction(e -> handleOptions());
            collectionButton.setOnAction(e -> handleCollection());

            // Diseño para los botones
            HBox buttonsBox = new HBox(10, playButton, optionsButton, collectionButton);
            buttonsBox.setAlignment(Pos.CENTER);

            // Caja gris alrededor de los botones usando un VBox con relleno y color de fondo
            VBox greyBox = new VBox(10, buttonsBox);
            greyBox.setAlignment(Pos.CENTER);
            greyBox.setBackground(new Background(new BackgroundFill(Color.DIMGRAY, null, null)));
            greyBox.setPadding(new Insets(5)); // Padding de la caja gris
            greyBox.setMaxWidth(300);

            // Posicionando el título y los botones
            VBox titleBox = new VBox(10, cardImage, title);
            titleBox.setAlignment(Pos.TOP_CENTER);

            VBox layout = new VBox(50, titleBox, greyBox);
            layout.setAlignment(Pos.CENTER);

            root.getChildren().add(layout);

            // Configuración de la escena
            Scene scene = new Scene(root, 800, 600);
            primaryStage.setScene(scene);
            primaryStage.setTitle("BALATRO");
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (Exception e) {
            System.err.println("Error al cargar los recursos: " + e.getMessage());
        }
    }

    private Button createStyledButton(String text, String baseColor) {
        Button button = new Button(text);
        button.setStyle(
                String.format("-fx-font-size: 16px; -fx-background-color: %s; -fx-text-fill: white;", baseColor)
        );

        // Efecto hover
        button.setOnMouseEntered(e ->
                button.setStyle(String.format("-fx-font-size: 16px; -fx-background-color: derive(%s, 20%%); -fx-text-fill: white;", baseColor))
        );
        button.setOnMouseExited(e ->
                button.setStyle(String.format("-fx-font-size: 16px; -fx-background-color: %s; -fx-text-fill: white;", baseColor))
        );

        return button;
    }

    private void handlePlay() {
        // TODO: Implementar funcionalidad de botón juego
        System.out.println("Botón Play");
    }

    private void handleOptions() {
        // TODO: Implementar funcionalidad de botón opciones?
        System.out.println("Botón Options");
    }

    private void handleCollection() {
        // TODO: Implementar funcionalidad de botón colección?
        System.out.println("Botón Collection");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
