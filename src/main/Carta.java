package main;

public class Carta {
    private String palo;
    private int valor;

    public Carta(String palo, int valor) {
        this.palo = palo;
        this.valor = valor;
    }

    public int getValor(){
        return this.valor;
    }

    public String getPalo(){
        return this.palo;
    }

    public boolean cartaTieneMismoValor(Carta carta){
        return carta.valorEsIgual(this.valor);
    }

    private boolean valorEsIgual(int valorCarta){
        return valorCarta == valor;
    }

    public boolean cartaTieneMismoPalo(Carta carta){
        return carta.paloEsIgual(this.palo);
    }

    private boolean paloEsIgual(String paloCarta){
        return paloCarta == palo;
    }

    public boolean esInmediatamenteSuperior(Carta carta){
        return (carta.valorEsIgual(this.valor + 1));
    }

    public boolean esInmediatamenteInferior(Carta carta){
        return (carta.valorEsIgual(this.valor - 1));
    }
}
