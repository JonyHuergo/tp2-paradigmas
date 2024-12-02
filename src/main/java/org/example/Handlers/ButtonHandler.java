package org.example.Handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaException;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.Pantallas.JuegoScreen;
import org.example.Pantallas.OptionsScreen;
import org.example.Pantallas.CollectionScreen;
import javafx.scene.layout.StackPane;

import java.io.File;


public abstract class ButtonHandler implements EventHandler<ActionEvent> {
    protected final Stage stage;
    public final MediaPlayer mediaPlayer;// Ruta al archivo de sonido

    public ButtonHandler(Stage stage, MediaPlayer mediaPlayer) {
        this.stage = stage;
        this.mediaPlayer = mediaPlayer;
    }

    // Método para reproducir el sonido
    static void playSound() {
        String soundFilePath = "src/main/resources/sounds/button-pressed.mp3";
        try {
            // Crear el objeto Media con la ruta al archivo de sonido
            String buttonPressedSound = new File(soundFilePath).toURI().toString();
            AudioClip audio = new AudioClip(buttonPressedSound);
            audio.play();
        } catch (MediaException e) {
            System.out.println("Error al reproducir el sonido: " + e.getMessage());
        }
    }

    // Método abstracto que será implementado por cada handler para la lógica específica
    @Override
    public abstract void handle(ActionEvent event);
}
