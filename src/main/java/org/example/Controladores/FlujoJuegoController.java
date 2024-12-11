package org.example.Controladores;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import org.example.*;
import org.example.Handlers.ExitButtonHandler;
import org.example.Handlers.PlayButtonHandler;
import org.example.Pantallas.EndGameScreen;
import org.example.Pantallas.JuegoScreen;
import org.example.Pantallas.VictoryScreen;

import java.util.ArrayList;
import java.util.List;

public class FlujoJuegoController {

    private final ArrayList<Carta> cartasSeleccionadas = new ArrayList<>();
    private Label manoLabel;
    private Label puntajeLabel;
    private Label multiplicadorLabel;
    private final ArrayList<Carta> cartasIniciales;// Label to update hand name
    private final Jugador jugador;
    private final Stage stage;
    private final List<Ronda> rondas;
    private int numeroRonda;
    private int manosJugadas;
    private MediaPlayer mediaPlayer;

    public FlujoJuegoController(Stage stage, List<Ronda> rondas, int numeroRonda, Label manoLabel, Label puntajeLabel, Label multiplicadorLabel, ArrayList<Carta> cartasIniciales, Mazo mazo, Jugador jugador, MediaPlayer mediaPlayer) {
        this.manoLabel = manoLabel;
        this.puntajeLabel = puntajeLabel;
        this.multiplicadorLabel = multiplicadorLabel;
        this.cartasIniciales = cartasIniciales;
        this.jugador = jugador;
        this.stage = stage;
        this.rondas = rondas;
        this.numeroRonda = numeroRonda;
        this.manosJugadas = 0;
        this.mediaPlayer = mediaPlayer;
    }

    public void seleccionarCarta(Carta carta, Button cartaButton, Mazo mazo) {
        if (!jugador.tieneCarta(carta) && !jugador.superaLimite()){
            jugador.agregarCarta(carta);
            cartaButton.setStyle("-fx-background-color: transparent;-fx-padding: -5;-fx-translate-y: -10");

        } else {
            jugador.removerCarta(carta);
            cartaButton.setStyle("-fx-background-color: transparent; -fx-padding: -5;");
        }


        if (jugador.getCantidadDeCartas()!=0) {


            String nombreMano = jugador.definirTipoDeMano();

            String valorPuntaje = String.valueOf(jugador.getPuntajeBase());
            puntajeLabel.setText(valorPuntaje);

            float multiplicadorBase = jugador.getMultiplicadorBase();
            String valorMultiplicador = String.format("%.0f", multiplicadorBase);
            multiplicadorLabel.setText(valorMultiplicador);

            manoLabel.setText("Mano: " + nombreMano);


        } else {
            manoLabel.setText("Mano: Ninguna");
            puntajeLabel.setText("0");
            multiplicadorLabel.setText("0");
        }
    }

    public ArrayList<Carta> getCartasSeleccionadas() {
        return cartasSeleccionadas;
    }

    public ArrayList<Carta> getCartasIniciales() {

        return cartasIniciales;
    }

    public void descartarCartas(Mazo mazo){
        jugador.descartarCartas();
        mostrarNuevaPantalla(jugador.getCartasDisponibles(), mazo);
    }

    public void jugarMano(Mazo mazo) {
        float puntaje = jugador.jugar(numeroRonda);

//         mazo = jugador.getMazo();

        if (jugador.perdio()){
            EndGameScreen pantallaDeDerrota = new EndGameScreen();
            jugador.reinicarJugador();
            numeroRonda = 0;
            pantallaDeDerrota.newGameButton.setOnAction(new PlayButtonHandler(stage, mediaPlayer, rondas, mazo, numeroRonda, jugador));
            pantallaDeDerrota.exitButton.setOnAction(new ExitButtonHandler(stage, mediaPlayer));
            Scene scene = new Scene(pantallaDeDerrota, 800, 600);
            stage.setScene(scene);
            return;
        }

        Ronda ronda = rondas.get(numeroRonda);
        int numeroRondaNueva = numeroRonda + 1;


        if (puntaje >= ronda.getPuntajeASuperar()) {
            if (numeroRondaNueva >= rondas.size()) {
                VictoryScreen pantallaDeVictoria = new VictoryScreen();
                jugador.reinicarJugador();
                numeroRonda = 0;
                pantallaDeVictoria.newGameButton.setOnAction(new PlayButtonHandler(stage, mediaPlayer, rondas, mazo, numeroRonda, jugador));
                pantallaDeVictoria.exitButton.setOnAction(new ExitButtonHandler(stage, mediaPlayer));
                Scene scene = new Scene(pantallaDeVictoria, 800, 600);
                stage.setScene(scene);
            } else {
                PantallaTiendaController nuevaPantallaTienda = new PantallaTiendaController(
                        stage, mediaPlayer, mazo, rondas, numeroRondaNueva, jugador
                );
                nuevaPantallaTienda.iniciarPantallaTienda(rondas.get(numeroRondaNueva).obtenerTienda());
            }
        } else {
            mostrarNuevaPantalla(jugador.getCartasDisponibles(), mazo);
        }
    }

    public Label getManoLabel() {
        return manoLabel;
    }


    public Label getPuntajeLabel() {
        return puntajeLabel;
    }

    public Label getMultiplicadorLabel() {
        return multiplicadorLabel;
    }

    private void mostrarNuevaPantalla(ArrayList<Carta> nuevasCartas, Mazo mazo) {

        Ronda ronda = rondas.get(numeroRonda);

            JuegoScreen nuevaPantalla = new JuegoScreen(nuevasCartas, rondas, numeroRonda, this, mazo, jugador, jugador.getComodines());
            Scene nuevaScene = new Scene(nuevaPantalla, 800, 600);

            stage.setScene(nuevaScene);
            manosJugadas = manosJugadas+1;

    }

}
