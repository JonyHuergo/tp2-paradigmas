package org.example.Controladores;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.Handlers.CollectionButtonHandler;
import org.example.Handlers.OptionsButtonHandler;
import org.example.Handlers.PlayButtonHandler;
import org.example.Pantallas.PantallaInicioScreen;

public class PantallaInicioController {
    private final Stage stage;

    public PantallaInicioController(Stage stage) {
        this.stage = stage;
    }

    public void iniciarPantallaInicio() {
        PantallaInicioScreen pantallaInicio = new PantallaInicioScreen();

        // Asignar los controladores a cada botón
        pantallaInicio.playButton.setOnAction(new PlayButtonHandler(stage));
        pantallaInicio.optionsButton.setOnAction(new OptionsButtonHandler(stage));
        pantallaInicio.collectionButton.setOnAction(new CollectionButtonHandler(stage));

        Scene scene = new Scene(pantallaInicio, 800, 600);
        stage.setScene(scene);
    }
}
