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
import org.example.Pantallas.PantallaInicioScreen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        try {

// Para probar la pantalla final
//            EndGamePantalla endGamePantalla = new EndGamePantalla();
//
//            Scene scene = new Scene(endGamePantalla, 700, 650);
//            primaryStage.setTitle("Balatro");
//            primaryStage.setScene(scene);
//            primaryStage.show();
            // Icono

            primaryStage.getIcons().add(new Image("logo.png")); // Usar la ruta de tu imagen

            // Panel raíz
            StackPane root = new StackPane();
            PantallaInicioScreen pantallaInicio = new PantallaInicioScreen(root);
            Scene scene = new Scene(root, 800, 600);
            primaryStage.setScene(scene);
            primaryStage.setTitle("BALATRO");
            primaryStage.setResizable(false);
            primaryStage.show();
            // Styling del fondo

        } catch (Exception e) {
            System.err.println("Error al cargar los recursos: " + e.getMessage());
        }
    }



    public static void main(String[] args) {
        launch(args);
    }
}
