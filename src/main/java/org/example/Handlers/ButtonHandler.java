package org.example.Handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;


public abstract class ButtonHandler implements EventHandler<ActionEvent> {
    protected final Stage stage;

    public ButtonHandler(Stage stage) {
        this.stage = stage;
    }

    // Método abstracto que será implementado por cada handler para la lógica específica
    @Override
    public abstract void handle(ActionEvent event);
}
