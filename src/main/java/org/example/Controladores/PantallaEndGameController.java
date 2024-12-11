package org.example.Controladores;

import javafx.scene.Scene;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import org.example.Handlers.ExitButtonHandler;
import org.example.Pantallas.EndGameScreen;

public class PantallaEndGameController {
    private final Stage stage;
    private MediaPlayer mediaPlayer;

    public PantallaEndGameController(Stage stage, MediaPlayer mediaPlayer) {
        this.stage = stage;
        this.mediaPlayer = mediaPlayer;
    }

    public void iniciarPantallaEndGame() {
        EndGameScreen pantallaEndGame = new EndGameScreen();


        pantallaEndGame.exitButton.setOnAction(new ExitButtonHandler(stage, mediaPlayer));

        Scene scene = new Scene(pantallaEndGame, 800, 600);
        stage.setScene(scene);
    }
}
