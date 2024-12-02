package org.example.Pantallas;
import javafx.scene.image.ImageView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class EndGamePantalla extends VBox {
    public EndGamePantalla() {

        String textColor = "#ffffff";
        String highlightColor = "#ff6347";
        String panelColor = "#3d4148";

        String backgroundImage = getClass().getResource("/background.png").toExternalForm();
        String backgroundStyle = "-fx-background-image: url('" + backgroundImage + "'); " +
                "-fx-background-size: cover; " +
                "-fx-background-position: center;";


        this.setStyle(backgroundStyle);

        Font arcadeFont = Font.loadFont(
                getClass().getResourceAsStream("/arcadeclassic.regular.ttf"), 36
        );


        Label title = new Label("GAME OVER");
        title.setFont(arcadeFont);
        title.setTextFill(Color.web(highlightColor));


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
        Button mainMenu = createMainMenuButton();
        HBox buttonsBox = new HBox(20, newRun, mainMenu);
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
        Image mainMenuImage = new Image(getClass().getResourceAsStream("/ExitBoton.png"));
        ImageView mainMenuImageView = new ImageView(mainMenuImage);


        mainMenuImageView.setFitWidth(130);
        mainMenuImageView.setFitHeight(100);


        mainMenuButton.setGraphic(mainMenuImageView);


        mainMenuButton.setStyle("-fx-background-color: transparent;");

        mainMenuButton.setOnAction(event -> {
            System.out.println("Volver al menú principal");
        });

        return mainMenuButton;
    }


    private Button createNewGameButton() {
        Button mainMenuButton = new Button();

        Image mainMenuImage = new Image(getClass().getResourceAsStream("/NewGameBoton.png"));
        ImageView mainMenuImageView = new ImageView(mainMenuImage);


        mainMenuImageView.setFitWidth(170);
        mainMenuImageView.setFitHeight(120);


        mainMenuButton.setGraphic(mainMenuImageView);


        mainMenuButton.setStyle("-fx-background-color: transparent;");


        mainMenuButton.setOnAction(event -> {

            System.out.println("Volver al menú principal");
        });

        return mainMenuButton;
    }
}
