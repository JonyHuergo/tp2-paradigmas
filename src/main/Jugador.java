package main;

public class Jugador {
    private Mazo mazo;
    private String nombre;
    private List<Carta> mano;

    public Jugador(){
        mazo = new Mazo();
    }

    public void repartirCartas(int cantidad) {
        mano = mazo.repartir(cantidad);
    }
}
