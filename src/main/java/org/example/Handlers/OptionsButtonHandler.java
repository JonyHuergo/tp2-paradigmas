package org.example.Handlers;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.Pantallas.OptionsScreen;
import javafx.scene.layout.StackPane;

public class OptionsButtonHandler extends ButtonHandler {

    public OptionsButtonHandler(Stage stage) {
        super(stage);
    }

    @Override
    public void handle(ActionEvent event) {
        // Lógica para mostrar la pantalla de opciones
        OptionsScreen pantallaOptions = new OptionsScreen(new StackPane());
        Scene optionsScene = new Scene(pantallaOptions, 800, 600);
        stage.setScene(optionsScene);
    }
}
