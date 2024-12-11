package org.example;

import javafx.scene.image.Image;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import org.example.Controladores.PantallaInicioController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// problema 1 al empezar una nueva partida despues de perder o ganar
// problema 2 al pasar de ronda no se vuelve a repartir la mano (menos importante no se nota)

public class Juego {
    private Stage primaryStage;
    private Jugador jugador;
    private List<Ronda> rondas;
    private int numeroRonda;
    private Mazo mazo;

    public Juego(){

    }

    public void iniciar(Stage primaryStage){
        this.primaryStage = primaryStage;

        leerArchivo();

        crearPantallaInicio();

        mostrarPantalla();

    }

    public void leerArchivo(){
        LectorArchivosJson lectorArchivosJson = new LectorArchivosJson();
        ArrayList<Carta> cartasLeidas = null;
        try {
            rondas = lectorArchivosJson.leerBalatro();
            cartasLeidas = lectorArchivosJson.leerMazo();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al leer los archivos JSON.");
        }

        mazo = new Mazo(cartasLeidas);
        jugador = new Jugador(mazo.clonar());

        numeroRonda = 0;
        Ronda ronda = rondas.get(numeroRonda);

        int descartes = ronda.getDescartes();
        jugador.setCantidadDeDescartes(descartes);

        int manos= ronda.getCantidadDeManos();
        jugador.setCantidadDeManos(manos);
    }

    public void leerArchivo(LectorArchivosJson lectorArchivosJson) { // Sobrecarga del método
        try {
            rondas = lectorArchivosJson.leerBalatro();
            ArrayList<Carta> cartasLeidas = lectorArchivosJson.leerMazo();
            mazo = new Mazo(cartasLeidas);
            jugador = new Jugador(mazo);

            numeroRonda = 0;
            Ronda ronda = rondas.get(numeroRonda);

            jugador.setCantidadDeDescartes(ronda.getDescartes());
            jugador.setCantidadDeManos(ronda.getCantidadDeManos());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al leer los archivos JSON.");
        }
    }

    public void crearPantallaInicio(){
        MediaPlayer mediaPlayer = null;

        try {
            primaryStage.getIcons().add(new Image("logo.png"));
            primaryStage.setTitle("BALATRO");
            primaryStage.setResizable(false);

            PantallaInicioController controller = new PantallaInicioController(primaryStage, mediaPlayer);
            controller.iniciarPantallaInicio(rondas, mazo, numeroRonda, jugador);

        } catch (Exception e) {
            System.err.println("Error al cargar los recursos: " + e.getMessage());
        }
    }

    public void mostrarPantalla(){
        primaryStage.show();
    }

    public Jugador getJugador() {
        return jugador;
    }

}
