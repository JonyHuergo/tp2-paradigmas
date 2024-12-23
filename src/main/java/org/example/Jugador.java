package org.example;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import org.example.Comodin.Comodin;
import org.example.Pantallas.PantallaInicioScreen;
import org.example.Tarot.Tarot;

import java.util.ArrayList;

public class Jugador {
    private Mazo mazo;
    private ArrayList<Carta> cartasDisponibles = new ArrayList<>();
    private int limiteCartas = 8;
    private ManoPoker manoPoker;
    private ArrayList<Comodin> comodines = new ArrayList<>();
    private ArrayList<Tarot> tarotsUsados = new ArrayList<>();
    private int descartes;
    private int jugadas;
    private Jugada jugadaActual;
    private ArrayList<Jugada> listadoJugadas = new ArrayList<>();
    private boolean perdio = false;

    public Jugador(){
        mazo = new Mazo();
    }

    public Jugador(Mazo mazo){
        this.manoPoker = new ManoPoker();
        this.mazo = mazo;
    }

    public Mazo getMazo() {
        return mazo;
    }


    public Jugador(Mazo mazo, ArrayList<Carta> mano, ManoPoker manoPoker, ArrayList<Comodin> comodines){
        this.mazo = mazo;
        this.cartasDisponibles = mano;
        this.manoPoker = manoPoker;
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

    public void agregarComodin(Comodin comodin){
        comodines.add(comodin);
    }

    public void agregarTarot(Tarot tarot){
        tarotsUsados.add(tarot);
    }

    public void setMazo(Mazo mazo){
        this.mazo = mazo;
    }

    public int cantidadDeCartasDisponibles(){
        return cartasDisponibles.size();
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

    public float calcularPuntosPorDescarte(float puntosPorDescarte) {
        return(puntosPorDescarte*descartes);
    }

    public void repartirCartas() {
        mazo.repartir(cartasDisponibles,limiteCartas);
    }

//    public void elegirCarta(int pos){
//        Carta cartaElegida = cartasDisponibles.remove(pos);
//        this.manoPoker.agregarCarta(cartaElegida);
//    }

    public void descartarCartas(){
        if (descartes != 0) {
            reiniciarMano();
            descartes --;
        } else {
        mostrarAlerta("Ya usó todos los descartes posibles.");
        }
    }

    public void reiniciarMano(){
        ArrayList<Carta> copiaCartasIniciales = new ArrayList<>(cartasDisponibles);

        int cartasARepartir = manoPoker.getCantidadDeCartas();
        ArrayList<Carta> cartasRepartidas = mazo.repartirCartas(cartasARepartir);

        int indiceRepartida = 0;

        for (int i = 0; i < copiaCartasIniciales.size(); i++) {
            Carta carta = copiaCartasIniciales.get(i);
            if (manoPoker.tieneCarta(carta)) {
                cartasDisponibles.set(i, cartasRepartidas.get(indiceRepartida));
                indiceRepartida++;
            }
        }
        manoPoker = new ManoPoker();;
    }

    public int getCantDeComodines(){
        return comodines.size();
    }

    public int getCantidadDeTarots(){
        return tarotsUsados.size();
    }

    public String definirTipoDeMano(){
        manoPoker.definirTipodeMano();
        return manoPoker.getNombreMano();
    }

    public int getPuntajeBase(){
        return manoPoker.getPuntajeBase();
    }

    public float getMultiplicadorBase(){
        return manoPoker.getMultiplicadorBase();
    }

    public float jugar(int numeroRonda){
        if (jugadas != 0) {
            crearJugada(numeroRonda);
            reiniciarMano();
            jugadas --;
            return evaluarJugadas(numeroRonda);
        } else{
            perder();
        }
        return 0;
    }

    private void perder() {
        perdio = true;
    }

    public void crearJugada(){
        this.jugadaActual = new Jugada(manoPoker, comodines, descartes, tarotsUsados);
        listadoJugadas.add(jugadaActual);
    }

    public void crearJugada(int numeroRonda){
        this.jugadaActual = new Jugada(manoPoker, comodines, descartes, tarotsUsados, numeroRonda);
        listadoJugadas.add(jugadaActual);
    }

    public float evaluarJugadaActual(){
        return(jugadaActual.evaluarJugada());
    }

//    public float evaluarJugadas(){
//        float aux = 0;
//        for (Jugada jugada : listadoJugadas) {
//            aux += jugada.evaluarJugada();
//        }
//        return(aux);
//    }

    public float evaluarJugadas(int numeroRonda){
        float aux = 0;
        for (Jugada jugada : listadoJugadas) {
            if (jugada.tieneNumeroRonda(numeroRonda)) {
                aux += jugada.evaluarJugada();
            }
        }
        return(aux);
    }

    public ArrayList<Carta> getCartasDisponibles(){
        return(cartasDisponibles);
    }

//    public void usarTarot(Tarot tarot){
//        tarotsUsados.add(tarot);
//    }

    private void mostrarAlerta(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle("Límite alcanzado");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    public boolean tieneCarta(Carta carta){
        return manoPoker.tieneCarta(carta);
    }

    public int getCantidadDeCartas(){
        return manoPoker.getCantidadDeCartas();
    }

    public void agregarCarta(Carta carta){
        manoPoker.agregarCarta(carta);
    }

    public void removerCarta(Carta carta) { manoPoker.removerCarta(carta); }

    public Boolean superaLimite(){
        return (getCantidadDeCartas() >= 5);
    }

    public ArrayList<Comodin> getComodines(){
        return comodines;
    }

    public void setCantidadDeDescartes(int cantDeDescartes){
        descartes = cantDeDescartes;
    }

    public void setCantidadDeManos(int cantDeManos){
        jugadas = cantDeManos;
    }

    public int getCantidadJugadas(){
        return jugadas;
    }

    public int getDescartes(){
        return descartes;
    }

    public boolean perdio() {
        return perdio;
    }

    public void agregarCartaMazo(Carta carta){
        mazo.agregarCarta(carta);
    }

    public ManoPoker getManoPoker(){
        return(manoPoker);
    }

    public void reinicarJugador(){
        listadoJugadas = new ArrayList<>();
        tarotsUsados = new ArrayList<>();
        comodines = new ArrayList<>();
        cartasDisponibles = new ArrayList<>();
        reiniciarMano();
        perdio = false;
    }
}
