package org.example;

import javafx.application.Application;
import javafx.scene.image.Image;
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
        } catch (Exception e) {
            System.err.println("Error al cargar los recursos: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}



