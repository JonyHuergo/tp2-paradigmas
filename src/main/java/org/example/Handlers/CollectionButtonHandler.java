package org.example.Handlers;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import org.example.Pantallas.CollectionScreen;
import javafx.scene.layout.StackPane;

public class CollectionButtonHandler extends ButtonHandler {

    public CollectionButtonHandler(Stage stage, MediaPlayer mediaPlayer) {
        super(stage, mediaPlayer);
    }

    @Override
    public void handle(ActionEvent event) {
        ActionHandler.actionSound();
        CollectionScreen pantallaCollection = new CollectionScreen(new StackPane());
        Scene collectionScene = new Scene(pantallaCollection, 800, 600);
        stage.setScene(collectionScene);
    }
}

