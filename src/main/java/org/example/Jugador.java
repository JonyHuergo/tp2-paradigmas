package org.example;

import org.example.Comodin.Comodin;

import java.util.ArrayList;

public class Jugador {
    private String nombre;
    private Mazo mazo;
    private ArrayList<Carta> cartasDisponibles = new ArrayList<>();  // cambiar debido al problema con new
    private int limiteCartas = 8;
    private ManoPoker manoPoker;
    private ArrayList<Comodin> comodines = new ArrayList<>();
    private int puntaje;
    private int descartes = 3;
    private int jugadas = 5;
    private Jugada jugadaActual;
    private ArrayList<Jugada> listadoJugadas = new ArrayList<>();

    public Jugador(){
        mazo = new Mazo();
        puntaje = 0;
    }

    public Jugador(Mazo mazo){
        this.manoPoker = new ManoPoker();
        this.mazo = mazo;
        puntaje = 0;
    }

    public Mazo getMazo() {
        return mazo;
    }

    //    public Jugador(Mazo mazo, ArrayList<Carta> mano, ArrayList<Carta> manoElegida){
//        this.mazo = mazo;
//        this.mano = mano;
////        this.manoElegida = manoElegida;
//        puntaje = 0;
//    }

    public Jugador(Mazo mazo, ArrayList<Carta> mano, ManoPoker manoPoker, ArrayList<Comodin> comodines){
        this.mazo = mazo;
        this.cartasDisponibles = mano;
        this.manoPoker = manoPoker;
        this.puntaje = 0;
        this.comodines = comodines;
    }

    public void actualizarPuntajeBase(int puntajeBase){
        manoPoker.actualizarPuntajeBase(puntajeBase);
    }

    public void actualizarMult(float mult){
        manoPoker.actualizarMultiplicadorBase(mult);
    }

    public void multiplicarMult(float mult){
        manoPoker.multiplicarMultiplicadorBase(mult);
    }

    public void setManoPoker(ManoPoker manoPoker) {
        this.manoPoker = manoPoker;
    }

    public void setComodines(ArrayList<Comodin> comodines) {
        this.comodines = comodines;
    }

    public void setMazo(Mazo mazo){
        this.mazo = mazo;
    }

    public int cantidadDeCartasDisponibles(){
        return cartasDisponibles.size();
    }

    public boolean puntajeEsIgual(int puntaje){
        return(this.puntaje == puntaje);
    }

    public boolean tieneManoDeTipo(String manoEsperada){
        return(manoPoker.manoNombreEsIgual(manoEsperada));
    }

    public boolean tieneDescartes(){
        return(descartes>0);
    }

    public int calcularPuntosPorDescarte(int puntosPorDescarte) {
        return(puntosPorDescarte*descartes);
    }

    public void repartirCartas() {          // como hacemos para que no se reparta una misma carta dos veces? estado de carta?
        mazo.repartir(cartasDisponibles,limiteCartas);
    }

    public void elegirCarta(int pos){
        Carta cartaElegida = cartasDisponibles.remove(pos);
//        cartasDisponibles.remove(pos);
        this.manoPoker.agregarCarta(cartaElegida);
    }

    public void evaluarMano(){              // este hay que cambiar, el puntaje depende de las jugadas
        manoPoker.definirTipodeMano();
        manoPoker.sumarValorCartas();
        for (Comodin comodin : comodines) {
            comodin.usar(this);
        }
        puntaje += manoPoker.hacerCalculo();
    }

    public void reiniciarMano(){            // la mano que fue jugada se pierde
        manoPoker = new ManoPoker();
        repartirCartas();
    }

    public void crearJugada(){              // el estado actual se guarda en la jugada para que ese no se vea alterado por futuros cambios
        this.jugadaActual = new Jugada(manoPoker, comodines, descartes);
        listadoJugadas.add(jugadaActual);
        jugadas = jugadas - 1;
        reiniciarMano();
    }

    public float evaluarJugadaActual(){
        return(jugadaActual.evaluarJugada());
    }

    public float evaluarJugadas(){
        float aux = 0;
        for (Jugada jugada : listadoJugadas) {
            aux += jugada.evaluarJugada();
        }
        return(aux);
    }
    public ArrayList<Carta> getCartasDisponibles(){
        return(cartasDisponibles);
    }
}
