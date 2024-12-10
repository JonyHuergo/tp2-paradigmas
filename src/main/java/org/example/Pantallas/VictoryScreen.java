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

public class VictoryScreen extends VBox {
    public Button newGameButton;
    public Button exitButton;

    public VictoryScreen() {
        StackPane root = new StackPane();
        String highlightColor = "#34eb5c";

        String backgroundImage = getClass().getResource("/background.png").toExternalForm();
        String backgroundStyle = "-fx-background-image: url('" + backgroundImage + "'); " +
                "-fx-background-size: cover; " +
                "-fx-background-position: center;";

        ImageView backgroundView = new ImageView(new Image("background.png"));
        backgroundView.setFitWidth(800);
        backgroundView.setFitHeight(600);

        GaussianBlur blurEffect = new GaussianBlur(10);
        backgroundView.setEffect(blurEffect);
        root.getChildren().add(0, backgroundView);

        this.setStyle(backgroundStyle);

        Font arcadeFont = Font.loadFont(
                getClass().getResourceAsStream("/arcadeclassic.regular.ttf"), 72
        );

        Label title = new Label("VICTORY");
        title.setFont(arcadeFont);
        title.setTextFill(Color.web(highlightColor));

        DropShadow shadow = new DropShadow();
        shadow.setOffsetX(0);
        shadow.setOffsetY(0);
        shadow.setColor(Color.BLACK);
        shadow.setRadius(12);
        title.setEffect(shadow);

        // Botones
        newGameButton = createNewGameButton();
        exitButton = createExitButton();
        HBox buttonsBox = new HBox(20, newGameButton, exitButton);
        buttonsBox.setAlignment(Pos.CENTER);
        buttonsBox.setPadding(new Insets(20, 0, 0, 0));

        // Añadir todo al contenedor principal
        this.getChildren().addAll(title, buttonsBox);
        this.setAlignment(Pos.TOP_CENTER);
        this.setPadding(new Insets(20));
    }

    private Button createExitButton() {
        Button exitButton = new Button();
        Image exitImage = new Image(getClass().getResourceAsStream("/Botones/Exit.png"));
        ImageView exitImageView = new ImageView(exitImage);
        exitImageView.setFitWidth(100);
        exitImageView.setFitHeight(60);
        exitButton.setGraphic(exitImageView);
        exitButton.setStyle("-fx-background-color: transparent;");
        return exitButton;
    }

    private Button createNewGameButton() {
        Button newGameButton = new Button();
        Image newGameImage = new Image(getClass().getResourceAsStream("/Botones/NewGame.png"));
        ImageView newGameImageView = new ImageView(newGameImage);
        newGameImageView.setFitWidth(150);
        newGameImageView.setFitHeight(60);
        newGameButton.setGraphic(newGameImageView);
        newGameButton.setStyle("-fx-background-color: transparent;");
        return newGameButton;
    }
}
