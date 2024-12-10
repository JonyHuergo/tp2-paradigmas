package org.example;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import org.example.Comodin.Comodin;
import org.example.Pantallas.PantallaInicioScreen;
import org.example.Tarot.Tarot;

import java.util.ArrayList;

public class Jugador {
    private String nombre;
    private Mazo mazo;
    private ArrayList<Carta> cartasDisponibles = new ArrayList<>();  // cambiar debido al problema con new
    private int limiteCartas = 8;
    private ManoPoker manoPoker;
    private ArrayList<Comodin> comodines = new ArrayList<>();
    private ArrayList<Tarot> tarotsUsados = new ArrayList<>();
    private int puntaje;
    private int descartes;//CADA RONDA TIENE DISTINTOS DESCARTES Y CANTIDAD DE MANOS CAMBIAR ESTO (usar un getter?)
    private int jugadas;
    private Jugada jugadaActual;
    private ArrayList<Jugada> listadoJugadas = new ArrayList<>();
    private boolean perdio = false;

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

    public float calcularPuntosPorDescarte(float puntosPorDescarte) {
        return(puntosPorDescarte*descartes);
    }

    public void repartirCartas() {
        mazo.repartir(cartasDisponibles,limiteCartas);
    }

    public void elegirCarta(int pos){
        Carta cartaElegida = cartasDisponibles.remove(pos);
        this.manoPoker.agregarCarta(cartaElegida);
    }

    public void descartarCartas(){
        if (descartes != 0){
            reiniciarMano();
            descartes --;
        } else{
        mostrarAlerta("Ya usó todos los descartes posibles.");
        }
    }

    public void reiniciarMano(){
        ArrayList<Carta> copiaCartasIniciales = new ArrayList<>(cartasDisponibles);

        int cartasARepartir = manoPoker.getCantidadDeCartas();
        ArrayList<Carta> cartasRepartidas = mazo.repartirCartas(cartasARepartir);


        int indiceRepartida = 0;

        // Iterar sobre la copia para encontrar y reemplazar seleccionadas
        for (int i = 0; i < copiaCartasIniciales.size(); i++) {
            Carta carta = copiaCartasIniciales.get(i);

            if (manoPoker.tieneCarta(carta)) {
                cartasDisponibles.set(i, cartasRepartidas.get(indiceRepartida));
                indiceRepartida++;
            }
        }

        // Limpiar la lista de cartas seleccionadas
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

    public float jugar(){
        if (jugadas != 0) {
            crearJugada();
            reiniciarMano();
            jugadas --;
            return evaluarJugadas();
        } else{
            perder();
        }
        return 0;
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
        mostrarAlerta("Perdiste.");
        perdio = true;
    }


    public void crearJugada(){              // el estado actual se guarda en la jugada para que ese no se vea alterado por futuros cambios
        this.jugadaActual = new Jugada(manoPoker, comodines, descartes, tarotsUsados);
        listadoJugadas.add(jugadaActual);
    }

    public void crearJugada(int numeroRonda){              // el estado actual se guarda en la jugada para que ese no se vea alterado por futuros cambios
        this.jugadaActual = new Jugada(manoPoker, comodines, descartes, tarotsUsados, numeroRonda);
        listadoJugadas.add(jugadaActual);
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

    public void usarTarot(Tarot tarot){
        tarotsUsados.add(tarot);
    }

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
}
