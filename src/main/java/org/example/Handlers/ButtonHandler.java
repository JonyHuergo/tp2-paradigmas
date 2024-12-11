package org.example.Handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaException;
import java.io.File;


public abstract class ButtonHandler implements EventHandler<ActionEvent> {
    protected final Stage stage;
    public final MediaPlayer mediaPlayer;

    public ButtonHandler(Stage stage, MediaPlayer mediaPlayer) {
        this.stage = stage;
        this.mediaPlayer = mediaPlayer;
    }

    static void playSound() {
        String soundFilePath = "src/main/resources/sounds/button-pressed.mp3";
        try {
            String buttonPressedSound = new File(soundFilePath).toURI().toString();
            AudioClip audio = new AudioClip(buttonPressedSound);
            audio.play();
        } catch (MediaException e) {
            System.out.println("Error al reproducir el sonido: " + e.getMessage());
        }
    }

    @Override
    public abstract void handle(ActionEvent event);
}
