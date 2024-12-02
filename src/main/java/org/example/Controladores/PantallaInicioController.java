package org.example.Controladores;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.example.Pantallas.*;

public class PantallaInicioController {
    private final Stage stage;

    public PantallaInicioController(Stage stage) {
        this.stage = stage;
    }

    public void iniciarPantallaInicio() {
        PantallaInicioScreen pantallaInicio = new PantallaInicioScreen();

        // Agregar manejador al botón "Play"
        pantallaInicio.playButton.setOnAction(e -> mostrarPantallaJuego());
        pantallaInicio.collectionButton.setOnAction(e -> mostrarPantallaCollection());
        pantallaInicio.optionsButton.setOnAction(e -> mostrarPantallaOptions());

        Scene scene = new Scene(pantallaInicio, 800, 600);
        stage.setScene(scene);
    }

    private void mostrarPantallaJuego() {
        // no hay nada todavia
        JuegoScreen pantallaJuego = new JuegoScreen(new StackPane());
        Scene gameScene = new Scene(pantallaJuego, 800, 600);
        stage.setScene(gameScene);
    }
    private void mostrarPantallaOptions(){
        // no hay nada todavia
        OptionsScreen pantallaOptions = new OptionsScreen(new StackPane());
        Scene optionsScene = new Scene(pantallaOptions, 800, 600);
        stage.setScene(optionsScene);
    }

    private void mostrarPantallaCollection(){
        //no hay nada todavia
        CollectionScreen pantallaCollection = new CollectionScreen(new StackPane());
        Scene collectionScene = new Scene(pantallaCollection, 800, 600);
        stage.setScene(collectionScene);
    }


}
