package org.example.Pantallas;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class EndGameScreen extends VBox {
    public Button exitButton;
    public EndGameScreen() {
        StackPane root = new StackPane();
        String textColor = "#ffffff";
        String highlightColor = "#e8793c";
        String panelColor = "#3d4148";

        String backgroundImage = getClass().getResource("/background.png").toExternalForm();
        String backgroundStyle = "-fx-background-image: url('" + backgroundImage + "'); " +
                "-fx-background-size: cover; " +
                "-fx-background-position: center;";

        ImageView endGameView = new ImageView(new Image("background.png"));
        endGameView.setFitWidth(800);
        endGameView.setFitHeight(600);


        GaussianBlur blurEffect = new GaussianBlur(10); // Cambia el valor según la intensidad deseada
        endGameView.setEffect(blurEffect);
        root.getChildren().add(0, endGameView);


        this.setStyle(backgroundStyle);

        Font arcadeFont = Font.loadFont(
                getClass().getResourceAsStream("/arcadeclassic.regular.ttf"), 72
        );


        Label title = new Label("GAME OVER");
        title.setFont(arcadeFont);
        title.setTextFill(Color.web(highlightColor));

        DropShadow outerShadow = new DropShadow();
        outerShadow.setOffsetX(0);
        outerShadow.setOffsetY(0);
        outerShadow.setColor(Color.BLACK); // Borde exterior negro
        outerShadow.setRadius(12); // Radio más grande para que el negro sea notorio

        DropShadow innerShadow = new DropShadow();
        innerShadow.setOffsetX(0);
        innerShadow.setOffsetY(0);
        innerShadow.setColor(Color.WHITE); // Borde interior blanco
        innerShadow.setRadius(8); // Radio intermedio para el blanco
        innerShadow.setInput(outerShadow); // Encadena con el borde negro

        // Aplica los bordes al texto
        title.setEffect(innerShadow);

        // Alinear y configurar el VBox
        this.setAlignment(Pos.CENTER); // Centra todo en el VBox


        VBox statsBox = new VBox(10);
        statsBox.setPadding(new Insets(20));
        statsBox.setStyle("-fx-background-color: " + panelColor + "; -fx-background-radius: 10;");
        statsBox.setAlignment(Pos.CENTER);


        statsBox.getChildren().addAll(
                createStatRow("Best Hand", "★ 1,014", highlightColor),
                createStatRow("Most Played Hand", "Two Pair [8]", textColor),
                createStatRow("Cards Played", "59", textColor),
                createStatRow("Cards Discarded", "26", textColor),
                createStatRow("Cards Purchased", "6", textColor),
                createStatRow("Times Rerolled", "0", textColor),
                createStatRow("New Discoveries", "11", textColor),
                createStatRow("Seed", "TUTORIAL", textColor)
        );

        // Información adicional (derecha)
        VBox additionalBox = new VBox(10);
        additionalBox.setPadding(new Insets(20));
        additionalBox.setStyle("-fx-background-color: " + panelColor + "; -fx-background-radius: 10;");
        additionalBox.setAlignment(Pos.CENTER);
        additionalBox.getChildren().addAll(
                createStatRow("Ante", "2", textColor),
                createStatRow("Round", "5", textColor),
                createStatRow("Defeated By", "The Wall", highlightColor)
        );

        // Contenedor principal de estadísticas
        HBox statsContainer = new HBox(20, statsBox, additionalBox);
        statsContainer.setAlignment(Pos.CENTER);

        // Botones
        Button newRun = createNewGameButton();
        exitButton = createMainMenuButton();
        HBox buttonsBox = new HBox(20, newRun, exitButton);
        buttonsBox.setAlignment(Pos.CENTER);
        buttonsBox.setPadding(new Insets(20, 0, 0, 0));

        // Agregar todo al VBox principal
        this.getChildren().addAll(title, statsContainer, buttonsBox);
        this.setAlignment(Pos.TOP_CENTER);
        this.setPadding(new Insets(20));
    }

    // Método para crear filas de estadísticas
    private HBox createStatRow(String label, String value, String valueColor) {
        HBox container = new HBox(10);
        container.setStyle("-fx-background-color: #3d4148; -fx-border-color: #707070; -fx-border-radius: 5; -fx-background-radius: 5;");
        container.setPadding(new Insets(5, 10, 5, 10));
        container.setAlignment(Pos.CENTER_LEFT);

        Label statLabel = new Label(label + ":");
        statLabel.setFont(new Font("Arial", 16));
        statLabel.setTextFill(Color.web("#ffffff"));

        Label statValue = new Label(value);
        statValue.setFont(new Font("Arial", 16));
        statValue.setTextFill(Color.web(valueColor));

        container.getChildren().addAll(statLabel, statValue);
        return container;
    }

    private Button createButton(String text, String color) {
        Button button = new Button(text);
        button.setFont(new Font("Arial", 16));
        button.setStyle("-fx-background-color: " + color + "; -fx-text-fill: white; -fx-background-radius: 10; -fx-padding: 10 20;");
        return button;
    }

    private Button createMainMenuButton() {
        Button mainMenuButton = new Button();
        // Cargar la imagen
        Image mainMenuImage = new Image(getClass().getResourceAsStream("/Botones/Exit.png"));
        ImageView mainMenuImageView = new ImageView(mainMenuImage);


        mainMenuImageView.setFitWidth(100);
        mainMenuImageView.setFitHeight(60);


        mainMenuButton.setGraphic(mainMenuImageView);


        mainMenuButton.setStyle("-fx-background-color: transparent;");

        mainMenuButton.setOnAction(event -> {
            System.out.println("Volver al menú principal");
        });

        return mainMenuButton;
    }


    private Button createNewGameButton() {
        Button mainMenuButton = new Button();

        Image mainMenuImage = new Image(getClass().getResourceAsStream("/Botones/NewGame.png"));
        ImageView mainMenuImageView = new ImageView(mainMenuImage);


        mainMenuImageView.setFitWidth(150);
        mainMenuImageView.setFitHeight(60);


        mainMenuButton.setGraphic(mainMenuImageView);


        mainMenuButton.setStyle("-fx-background-color: transparent;");


        mainMenuButton.setOnAction(event -> {

            System.out.println("Volver al menú principal");
        });

        return mainMenuButton;
    }
}
