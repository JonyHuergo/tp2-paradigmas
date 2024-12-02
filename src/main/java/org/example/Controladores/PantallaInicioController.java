package org.example.Controladores;

import javafx.scene.Scene;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import org.example.Handlers.CollectionButtonHandler;
import org.example.Handlers.OptionsButtonHandler;
import org.example.Handlers.PlayButtonHandler;
import org.example.Pantallas.PantallaInicioScreen;

public class PantallaInicioController {
    private final Stage stage;
    private MediaPlayer mediaPlayer;

    public PantallaInicioController(Stage stage, MediaPlayer mediaPlayer) {
        this.stage = stage;
        this.mediaPlayer = mediaPlayer;
    }

    public void iniciarPantallaInicio() {
        PantallaInicioScreen pantallaInicio = new PantallaInicioScreen();

        // Asignar los controladores a cada botón
        pantallaInicio.playButton.setOnAction(new PlayButtonHandler(stage, mediaPlayer));
        pantallaInicio.optionsButton.setOnAction(new OptionsButtonHandler(stage, mediaPlayer));
        pantallaInicio.collectionButton.setOnAction(new CollectionButtonHandler(stage, mediaPlayer));

        Scene scene = new Scene(pantallaInicio, 800, 600);
        stage.setScene(scene);
    }
}
