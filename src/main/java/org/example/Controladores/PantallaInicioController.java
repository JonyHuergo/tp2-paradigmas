package org.example.Controladores;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.example.Pantallas.JuegoScreen;
import org.example.Pantallas.PantallaInicioScreen;
import org.example.Pantallas.EndGameScreen;

public class PantallaInicioController {
    private final Stage stage;

    public PantallaInicioController(Stage stage) {
        this.stage = stage;
    }

    public void iniciarPantallaInicio() {
        PantallaInicioScreen pantallaInicio = new PantallaInicioScreen();

        // Agregar manejador al botón "Play"
        pantallaInicio.playButton.setOnAction(e -> mostrarPantallaJuego());

        Scene scene = new Scene(pantallaInicio, 800, 600);
        stage.setScene(scene);
    }

    private void mostrarPantallaJuego() {
        // Aquí puedes reemplazar con la pantalla del juego real
        JuegoScreen pantallaJuego = new JuegoScreen(new StackPane());
        Scene gameScene = new Scene(pantallaJuego, 800, 600);
        stage.setScene(gameScene);
    }
}
