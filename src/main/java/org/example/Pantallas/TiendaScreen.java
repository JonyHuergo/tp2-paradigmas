package org.example.Pantallas;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.example.*;
import org.example.Controladores.PantallaJuegoController;
import org.example.Handlers.*;
import org.example.Comodin.Comodin;
import org.example.Tarot.Tarot;

import java.util.ArrayList;
import java.util.List;

public class TiendaScreen extends VBox {

    private List<Comodin> comodines;
    private List<Tarot> tarots;
    private Carta carta;
    private Button selectedButton = null;
    private Comprable cartaSeleccionada = null;


    public TiendaScreen(Tienda tienda, Stage stage, MediaPlayer mediaPlayer, Mazo mazo, List<Ronda> rondas, int numeroRonda, Jugador jugador) {
        super();
        this.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #4d7e64, #112e22);" +
                        "-fx-border-color: #2b2b2b; -fx-border-width: 20; -fx-border-radius: 10;"
        );


        this.setSpacing(10);
        this.setPadding(new javafx.geometry.Insets(20));


        Image pokerChipImage = new Image(getClass().getResourceAsStream("/poker-chip.png"));
        ImageView leftChip = new ImageView(pokerChipImage);
        ImageView rightChip = new ImageView(pokerChipImage);

// Ajusta el tamaño de las imágenes si es necesario
        leftChip.setFitWidth(70);  // Ancho de la imagen
        leftChip.setFitHeight(70); // Alto de la imagen
        rightChip.setFitWidth(70);
        rightChip.setFitHeight(70);

        // Título
        Label titulo = new Label("TIENDA");
        Font arcadeFont = Font.loadFont(
                getClass().getResourceAsStream("/Roboto-Black.ttf"), 72
        );
        titulo.setStyle(    "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-effect: dropshadow(gaussian, black, 4, 0.5, 0, 0);"
        );
        titulo.setFont(arcadeFont);
        titulo.setAlignment(Pos.CENTER);

        HBox titleBox = new HBox(10); // 10 es el espacio entre los elementos
        titleBox.setAlignment(Pos.CENTER);
        titleBox.getChildren().addAll(leftChip, titulo, rightChip);

// Agrega el HBox al diseño principal
        this.getChildren().add(titleBox);

        this.comodines = tienda.obtenerComodines();
        this.tarots = tienda.obtenerTarots();
        this.carta = tienda.obtenerCarta();

        List<Comprable> comprables = new ArrayList<>();
        comprables.addAll(comodines);
        comprables.addAll(tarots);
        comprables.add(carta);
        cartaSeleccionada = comprables.get(0);

        FlowPane cartasPane = crearYAsignarCartas(comprables,stage, mediaPlayer, mazo, jugador, rondas, numeroRonda );
        this.getChildren().add(cartasPane);


    }

    // Método para seleccionar una carta (comodín, tarot o carta)
    private void seleccionarCarta(Comprable seleccionada, Button cartaButton) {
        if (selectedButton != null && selectedButton != cartaButton) {
            selectedButton.setOpacity(1.0);
            selectedButton.setStyle("-fx-background-color: transparent; -fx-padding: -5;");
        }
        cartaSeleccionada = seleccionada;
        selectedButton = cartaButton;

        selectedButton.setOpacity(1.0);
        selectedButton.setStyle("-fx-background-color: transparent; -fx-padding: -5; -fx-effect: innershadow(gaussian, rgba(255,255,255,0.8), 20, 0.5, 0, 0);");

        deseleccionarTodasLasCartas();


    }


    // Método para deseleccionar todas las cartas (opacarlas)
    private void deseleccionarTodasLasCartas() {
        // Desactiva la opacidad en todas las cartas (comodines, tarots y la carta)
        for (Node node : this.getChildren()) {
            if (node instanceof FlowPane) {
                FlowPane pane = (FlowPane) node;
                for (Node subNode : pane.getChildren()) {
                    if (subNode instanceof Button && subNode != selectedButton) {
                        subNode.setOpacity(0.5);  // Baja la opacidad de las demás cartas
                    }
                }
            }
        }
    }
    private FlowPane crearYAsignarCartas(List<Comprable> cartas, Stage stage, MediaPlayer mediaPlayer, Mazo mazo, Jugador jugador, List<Ronda> rondas, int numeroRonda) {
        Label descripcionLabel = new Label(" ");
        descripcionLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: white; -fx-padding: 10px;");
        this.getChildren().add(descripcionLabel);

        FlowPane flowPane = new FlowPane();
        flowPane.setHgap(10);
        flowPane.setVgap(10);
        flowPane.setAlignment(Pos.CENTER);

        // Crear el botón de avanzar
        Button avanzarButton = new Button("Avanzar");
        avanzarButton.setStyle("-fx-font-size: 16px; -fx-background-color: yellow;");
        avanzarButton.getStyleClass().add("button-avanzar"); // Agregar clase para referencia futura
        

        // Un único manejador para todos los botones
        EventHandler<ActionEvent> seleccionHandler = event -> {
            Button cartaButton = (Button) event.getSource();
            // Se obtiene la carta asociada al botón desde el 'UserData'
            Comprable seleccionada = (Comprable) cartaButton.getUserData();

            descripcionLabel.setText(seleccionada.getDescripcion());

            // Llama al método para seleccionar la carta
            seleccionarCarta(seleccionada, cartaButton);

            // Actualiza el manejador del botón de avanzar
            actualizarHandlerAvanzar(avanzarButton, stage, mediaPlayer, mazo, jugador, rondas, numeroRonda);
        };

        // Crear los botones para cada carta y asociarles el manejador
        for (Comprable carta : cartas) {
            String imagePath = carta.getRuta();
            Image image = new Image(imagePath);
            ImageView imageView = new ImageView(image);


            if (cartas.get(2) == carta || cartas.get(3) == carta){
                imageView.setFitWidth(120);
                imageView.setFitHeight(150);
            }
            else {
                imageView.setFitWidth(150);
                imageView.setFitHeight(150);
            }
            Button cartaButton = new Button();
            cartaButton.setGraphic(imageView);
            cartaButton.setStyle("-fx-background-color: transparent; -fx-padding: -5;");
            cartaButton.setUserData(carta); // Asocia la carta al botón


            // Asigna el manejador de acción al botón
            cartaButton.setOnAction(seleccionHandler);
            flowPane.getChildren().add(cartaButton);
        }


        // Agrega el botón de avanzar al diseño
        this.setAlignment(Pos.CENTER);
        this.getChildren().add(avanzarButton);

        return flowPane;
    }

    private void actualizarHandlerAvanzar(Button avanzarButton, Stage stage, MediaPlayer mediaPlayer, Mazo mazo, Jugador jugador, List<Ronda> rondas, int numeroRonda) {
        if (cartaSeleccionada != null) {
            EventHandler<ActionEvent> handler = cartaSeleccionada.crearHandler(new PantallaJuegoController(stage, mediaPlayer), mazo, jugador, rondas, numeroRonda);
            avanzarButton.setOnAction(handler);
        }
    }


}





