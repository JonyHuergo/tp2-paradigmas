package org.example.Handlers;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import org.example.Pantallas.OptionsScreen;
import javafx.scene.layout.StackPane;

public class OptionsButtonHandler extends ButtonHandler {

    public OptionsButtonHandler(Stage stage, MediaPlayer mediaPlayer) {
        super(stage, mediaPlayer);
    }

    @Override
    public void handle(ActionEvent event) {
        ActionHandler.actionSound();
        OptionsScreen pantallaOptions = new OptionsScreen(new StackPane());
        Scene optionsScene = new Scene(pantallaOptions, 800, 600);
        stage.setScene(optionsScene);
    }
}
