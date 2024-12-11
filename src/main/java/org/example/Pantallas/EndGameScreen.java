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
    public Button newGameButton;
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
        outerShadow.setColor(Color.BLACK);
        outerShadow.setRadius(12);

        DropShadow innerShadow = new DropShadow();
        innerShadow.setOffsetX(0);
        innerShadow.setOffsetY(0);
        innerShadow.setColor(Color.WHITE);
        innerShadow.setRadius(8);
        innerShadow.setInput(outerShadow);


        title.setEffect(innerShadow);


        this.setAlignment(Pos.CENTER);


        newGameButton = createNewGameButton();
        exitButton = createMainMenuButton();
        HBox buttonsBox = new HBox(20, newGameButton, exitButton);
        buttonsBox.setAlignment(Pos.CENTER);
        buttonsBox.setPadding(new Insets(20, 0, 0, 0));

        this.getChildren().addAll(title, buttonsBox);
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

        Image mainMenuImage = new Image(getClass().getResourceAsStream("/Botones/Exit.png"));
        ImageView mainMenuImageView = new ImageView(mainMenuImage);

        mainMenuImageView.setFitWidth(100);
        mainMenuImageView.setFitHeight(60);

        mainMenuButton.setGraphic(mainMenuImageView);
        mainMenuButton.setStyle("-fx-background-color: transparent;");

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

        return mainMenuButton;
    }
}
