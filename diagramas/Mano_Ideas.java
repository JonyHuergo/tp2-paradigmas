import java.util.*;

public class Mano {
    private List<Carta> cartas;

    public Mano(List<Carta> cartas) {
        this.cartas = cartas;
    }

    private Map<String, Integer> obtenerConteoDeRangos() {
        Map<String, Integer> conteoDeRangos = new HashMap<>();
        for (Carta carta : cartas) {
            conteoDeRangos.put(carta.getRango(), conteoDeRangos.getOrDefault(carta.getRango(), 0) + 1);
        }
        return conteoDeRangos;
    }

    private boolean esColor() { // Flush en inglés
        String palo = cartas.get(0).getPalo();
        for (Carta carta : cartas) {
            if (!carta.getPalo().equals(palo)) {
                return false;
            }
        }
        return true;
    }

    private boolean esEscalera() {
        List<Integer> rangos = new ArrayList<>();
        for (Carta carta : cartas) {
            rangos.add(valorRango(carta.getRango()));
        }
        Collections.sort(rangos);

        for (int i = 0; i < rangos.size() - 1; i++) {
            if (rangos.get(i) + 1 != rangos.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    private boolean esEscaleraReal() {
        // Verifica si la mano es un color y contiene los rangos 10, J, Q, K, A
        Set<String> rangosRequeridos = new HashSet<>(Arrays.asList("10", "J", "Q", "K", "A"));
        Set<String> rangosEnMano = new HashSet<>();
        
        for (Carta carta : cartas) {
            rangosEnMano.add(carta.getRango());
        }
        
        return esColor() && rangosEnMano.equals(rangosRequeridos);
    }

    private int valorRango(String rango) {
        switch (rango) {
            case "A": return 14;
            case "K": return 13;
            case "Q": return 12;
            case "J": return 11;
            default: return Integer.parseInt(rango);
        }
    }

    public String evaluarMano() {
        Map<String, Integer> conteoDeRangos = obtenerConteoDeRangos();
        Collection<Integer> valores = conteoDeRangos.values();
        
        if (esEscaleraReal()) {
            return "Escalera Real";
        } else if (esColor() && esEscalera()) {
            return "Escalera de Color";
        } else if (valores.contains(4)) {
            return "Póker";
        } else if (valores.contains(3) && valores.contains(2)) {
            return "Full";
        } else if (esColor()) {
            return "Color";
        } else if (esEscalera()) {
            return "Escalera";
        } else if (valores.contains(3)) {
            return "Trío";
        } else if (Collections.frequency(valores, 2) == 2) {
            return "Doble Par";
        } else if (valores.contains(2)) {
            return "Par";
        } else {
            return "Carta Alta";
        }
    }
}