package org.example;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import org.example.Controladores.PantallaInicioController;


public class Main extends Application {

    private Stage primaryStage;
    private MediaPlayer mediaPlayer;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        try {

            primaryStage.getIcons().add(new Image("logo.png"));
            primaryStage.setTitle("BALATRO");
            primaryStage.setResizable(false);

            // Inicia el controlador de la pantalla inicial
            PantallaInicioController controller = new PantallaInicioController(primaryStage, mediaPlayer);
            controller.iniciarPantallaInicio();

            primaryStage.show();

            // Panel raíz
            StackPane root = new StackPane();

            //Para probar pantalla final
//            EndGameScreen endGamePantalla = new EndGameScreen(root);
//
//            Scene scene = new Scene(endGamePantalla, 700, 650);
//            primaryStage.setTitle("Balatro");
//            primaryStage.setScene(scene);
//            primaryStage.show();

//            PantallaInicioScreen pantallaInicio = new PantallaInicioScreen(root);
//
//            Scene scene = new Scene(root, 800, 600);
//            primaryStage.setScene(scene);
//            primaryStage.setTitle("BALATRO");
//            primaryStage.setResizable(false);
//            primaryStage.show();
            // Styling del fondo

        } catch (Exception e) {
            System.err.println("Error al cargar los recursos: " + e.getMessage());
        }
    }



    public static void main(String[] args) {
        launch(args);
    }
}



