package org.example.Handlers;

import javafx.event.ActionEvent;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class ExitButtonHandler extends ButtonHandler {
    public ExitButtonHandler(Stage stage, MediaPlayer mediaPlayer) {
        super(stage, mediaPlayer);
    }

    @Override
    public void handle(ActionEvent event) {
        System.exit(0);
    }
}
