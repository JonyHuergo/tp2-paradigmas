package org.example.Handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.*;
import org.example.Controladores.PantallaJuegoController;
import org.example.Controladores.SeleccionarCartaController;
import org.example.Tarot.Tarot;
import org.example.Tarot.TarotSobreCarta;

import java.io.IOException;
import java.util.List;

public class CompraTarotHandler implements EventHandler<ActionEvent> {

    private final Tarot tarot;
    private final Jugador jugador;
    private PantallaJuegoController pantallaJuegoController;
    private Mazo mazo;
    private List<Ronda> rondas;
    private int numeroRonda;


    public CompraTarotHandler(Tarot tarot, Jugador jugador, PantallaJuegoController pantallaJuegoController, Mazo mazo, List<Ronda> rondas, int numeroRonda) {
        this.tarot = tarot;
        this.jugador = jugador;
        this.pantallaJuegoController = pantallaJuegoController;
        this.mazo = mazo;
        this.rondas = rondas;
        this.numeroRonda = numeroRonda;

    }

    private Carta abrirPantallaSeleccionCarta() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/SeleccionarCarta.fxml"));
            SeleccionarCartaController seleccionarCartaController = new SeleccionarCartaController(mazo);
            loader.setController(seleccionarCartaController);
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
            seleccionarCartaController.initialize();
            Carta cartaSeleccionada = seleccionarCartaController.getCartaSeleccionada();
            return cartaSeleccionada;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void handle(ActionEvent event) {
        System.out.println("Tarot card purchased: ");
        ActionHandler.actionSound();
        System.out.println(jugador.getCantidadDeTarots());


        if (tarot.esSobreCarta()) {

            Carta cartaSeleccionada = abrirPantallaSeleccionCarta();
            if (cartaSeleccionada != null) {
                ((TarotSobreCarta) tarot).usarSobre(cartaSeleccionada);
                jugador.agregarTarot(tarot);

            }else{
                jugador.agregarTarot(tarot);
            }


        }
        pantallaJuegoController.iniciarPantallaJuego(mazo, rondas, numeroRonda, jugador);

    }


}
