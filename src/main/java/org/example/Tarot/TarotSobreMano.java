package org.example.Tarot;

import org.example.Carta;
import org.example.Manos.DoblePar;
import org.example.Manos.Mano;
import org.example.Manos.Par;

public class TarotSobreMano extends Tarot {

    public TarotSobreMano(String nombre, String descripcion, String sobre, String ejemplar, int puntos, float multiplicador){
        /*switch (input) {
            case "par":
                Mano manoPar = new Par();
                break;
            case "doble par":
                Mano doblePar = new DoblePar();
                break;
            case "opcion3":
                System.out.println("Caso 3");
                break;
            default:
                System.out.println("Caso no reconocido");
        }*/
        super(nombre, descripcion, sobre, ejemplar, puntos, multiplicador);
    }
    public TarotSobreMano(int puntos){
        super(puntos);
    }

    @Override
    public void aplicarEfecto(Carta carta) {

    };

}

