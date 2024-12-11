package org.example.Controladores;

import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import org.example.*;
import java.util.List;

public class FlujoRondasController {
    private final Stage stage;
    private final MediaPlayer mediaPlayer;

        public FlujoRondasController(Stage stage, MediaPlayer mediaPlayer) {
            this.mediaPlayer = mediaPlayer;
            this.stage = stage;
        }

        public void iniciarFlujo( List<Ronda> rondas, int numeroRonda, Jugador jugador){

            Ronda ronda = rondas.get(numeroRonda);

            PantallaTiendaController pantallaTienda = new PantallaTiendaController(stage, mediaPlayer, null, rondas, numeroRonda, jugador);
            pantallaTienda.iniciarPantallaTienda(ronda.obtenerTienda());



            PantallaJuegoController pantallaJuego= new PantallaJuegoController(stage, mediaPlayer);

        }
}
