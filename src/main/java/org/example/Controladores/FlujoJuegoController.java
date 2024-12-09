package org.example.Controladores;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.example.*;
import org.example.Manos.Mano;
import org.example.Pantallas.JuegoScreen;

import java.util.ArrayList;

public class FlujoJuegoController {

    // for 8 rondas:
    //     muestra tiendaScreen (elije 4 cartas)
    //     muestra JuegoScreen
    //     for 5 turnos:
    //          -reparten aleatoriamente 8 cartas del mazo
    //          -jugarlas tratando de armar una mano de poker.
    //          -aplicar algún modificador usando un tarot.
    //          -descartar las que quiera hasta tres veces, donde debe recibir del mazo las cartas suficientes para completar
    //          -calcular puntaje utilizando los comodines de izquierda a derecha y se suma el puntaje y se avanza al siguiente


    private final ArrayList<Carta> cartasSeleccionadas = new ArrayList<>();
    private Label manoLabel;
    private Label puntajeLabel;
    private Label multiplicadorLabel;
    private final ArrayList<Carta> cartasIniciales;// Label to update hand name
    private final Jugador jugador;
    private final Stage stage;
    private final Ronda ronda;
    private int manosJugadas;

    public FlujoJuegoController(Stage stage, Ronda ronda, Label manoLabel, Label puntajeLabel, Label multiplicadorLabel, ArrayList<Carta> cartasIniciales, Mazo mazo, Jugador jugador) {
        this.manoLabel = manoLabel;
        this.puntajeLabel = puntajeLabel;
        this.multiplicadorLabel = multiplicadorLabel;
        this.cartasIniciales = cartasIniciales;
        this.jugador = jugador;
        this.stage = stage;
        this.ronda = ronda;
        this.manosJugadas = 0;
    }

    public void seleccionarCarta(Carta carta, Button cartaButton, Mazo mazo) {
//        if (cartasSeleccionadas.contains(carta)) {
//            // Deseleccionar carta
//            cartasSeleccionadas.remove(carta);
//            cartaButton.setStyle("-fx-background-color: transparent; -fx-padding: -5;");
////        } else if (cartasSeleccionadas.size() < 5) {
//        } else{
//
//        // Seleccionar carta
//            cartasSeleccionadas.add(carta);
//            cartaButton.setStyle("-fx-background-color: transparent;-fx-padding: -5;-fx-translate-y: -10");
//        }
        if (!jugador.tieneCarta(carta) && !jugador.superaLimite()){
            jugador.agregarCarta(carta);
            cartaButton.setStyle("-fx-background-color: transparent;-fx-padding: -5;-fx-translate-y: -10");

        } else {
            jugador.removerCarta(carta);
            cartaButton.setStyle("-fx-background-color: transparent; -fx-padding: -5;");
        }

        // Si hay cartas seleccionadas, analizar la mano
        if (jugador.getCantidadDeCartas()!=0) {


//            AnalizadorMano analizador = new AnalizadorMano();
//            Mano mano = analizador.analizarMano(cartasSeleccionadas);
//            String nombreMano = mano.getNombre();
            String nombreMano = jugador.definirTipoDeMano();

            String valorPuntaje = String.valueOf(jugador.getPuntajeBase());
            puntajeLabel.setText(valorPuntaje);

            float multiplicadorBase = jugador.getMultiplicadorBase();
            String valorMultiplicador = String.format("%.0f", multiplicadorBase);
            multiplicadorLabel.setText(valorMultiplicador);


            // Actualizar el nombre de la mano en el label
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
//        jugador.descartarCartas(cartasIniciales, cartasSeleccionadas, mazo);
//        mostrarNuevaPantalla(cartasIniciales, mazo);
        jugador.descartarCartas();
        mostrarNuevaPantalla(jugador.getCartasDisponibles(), mazo);
    }

    public void jugarMano() {
        jugador.jugar();
        Mazo mazo = jugador.getMazo();
        mostrarNuevaPantalla(jugador.getCartasDisponibles(), mazo);

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



        if (manosJugadas != ronda.getCantidadDeManos()){
            JuegoScreen nuevaPantalla = new JuegoScreen(nuevasCartas, ronda, this, mazo, jugador, jugador.getComodines());
            Scene nuevaScene = new Scene(nuevaPantalla, 800, 600); // Ajusta el tamaño según tu diseño

            stage.setScene(nuevaScene); // Cambiar la escena en la ventana principal
            manosJugadas = manosJugadas+1;
        }
        else {
            System.out.println("nueva pantalla");
        }




    }

}
