package org.example.Pantallas;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.example.Handlers.CartaButtonHandler;
import org.example.Controladores.FlujoJuegoController;
import org.example.Carta;

import java.util.ArrayList;

public class JuegoScreen extends VBox {
    public JuegoScreen(ArrayList<Carta> cartasIniciales, int puntajeASuperar, FlujoJuegoController controller) {
        super();

        // Crear un FlowPane para contener las cartas
        FlowPane cartasPane = new FlowPane();

        // Iterar sobre las cartas en el mazo del jugador
        for (Carta carta : cartasIniciales) {
            // Crear la ruta de la imagen de cada carta
            String imagePath = "/cartas/" + carta.getRuta() + ".png";

            // Crear la imagen y el ImageView correspondiente
            Image image = new Image(imagePath);
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(90);
            imageView.setFitHeight(90);

            // Crear un botón que contiene la carta
            Button cartaButton = new Button();
            cartaButton.setGraphic(imageView);
            cartaButton.setStyle("-fx-background-color: transparent; -fx-padding: -5;");

            // Delegar la lógica del botón al handler
            cartaButton.setOnAction(new CartaButtonHandler(carta, cartaButton, controller));

            // Agregar el botón al FlowPane
            cartasPane.getChildren().add(cartaButton);
        }

        cartasPane.setStyle("-fx-padding: 10; -fx-hgap: -20; -fx-vgap: 0; -fx-translate-y: 500;");

        // Crear un Label para mostrar el puntajeASuperar
        Label puntajeLabel = new Label("Puntaje a superar: " + puntajeASuperar);
        puntajeLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: white; -fx-padding: 10;");

        // Obtener el manoLabel del controller, que ya fue configurado en PantallaJuegoController
        Label manoLabel = controller.getManoLabel();
        manoLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: white; -fx-padding: 10;");

        // Crear un VBox para organizar los Labels verticalmente
        VBox leftPanelContent = new VBox(10);  // 10 is the space between the labels
        leftPanelContent.setStyle("-fx-padding: 10;");
        leftPanelContent.getChildren().addAll(puntajeLabel, manoLabel);

        // Crear un Panel (Panel de la izquierda)
        StackPane leftPanel = new StackPane();
        leftPanel.setStyle("-fx-background-color: gray;");  // Establecer un color de fondo para el panel izquierdo
        leftPanel.setMinWidth(200); // Aproximadamente un cuarto del ancho total

        // Agregar el VBox con los Labels al panel izquierdo
        leftPanel.getChildren().add(leftPanelContent);

        // Crear un StackPane para el contenido principal (Centro verde)
        StackPane contentPane = new StackPane();
        contentPane.setStyle("-fx-background-color: green;");  // Establecer un color de fondo verde
        contentPane.setMinHeight(800);
        contentPane.getChildren().add(cartasPane);

        // Crear un BorderPane para organizar los elementos
        BorderPane layout = new BorderPane();
        layout.setLeft(leftPanel); // Colocar el panel izquierdo
        layout.setCenter(contentPane); // Colocar el área principal con color verde en el centro

        // Agregar el BorderPane como único hijo de esta pantalla
        this.getChildren().add(layout);
    }
}
