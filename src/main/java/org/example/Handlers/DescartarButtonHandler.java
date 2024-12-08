package org.example.Handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class DescartarButtonHandler implements EventHandler<ActionEvent> {

    DescartarButtonHandler(){

    }
    @Override
    public void handle(ActionEvent event) {
        ActionHandler.actionSound();
    }
}
