import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.swing.JPanel;

public class Jugador {

    private  final int TOTAL_CARTAS = 10;
    private  final int SEPARACION = 40;
    private  final int MARGEN = 10;
    private Carta[] cartas = new Carta[TOTAL_CARTAS];
    private Random r = new Random();
    
    // solucion al metodo repartir, para evitar cartas repetidas al usar random
    public void repartir() {
        List<Integer> indices = new ArrayList<>(); //arreglo temporal
        for (int i = 1; i <= 52; i++) { 
            indices.add(i);
        }
        Collections.shuffle(indices, r);
        for (int i = 0; i < TOTAL_CARTAS; i++) {
            cartas[i] = new Carta(indices.get(i));
        }
    }

    public void mostrar(JPanel pnl) {
        pnl.removeAll();
        int posicion = MARGEN;
        // Mostrar cada carta en el panel
        for (Carta carta : cartas) {
            carta.mostrar(pnl, posicion, MARGEN);
            posicion += SEPARACION;
        }
        pnl.revalidate();
        pnl.repaint();
    }
    public String getGrupos() {
        String resultado = "";
        // 1. Detectar pares y ternas por nombre
        int[] contadores = new int[NombreCarta.values().length];
        for (Carta carta : cartas) {
            contadores[carta.getNombre().ordinal()]++;
        }
        for (int i = 0; i < contadores.length; i++) {
            if (contadores[i] == 2) {
                resultado += "Par de " + NombreCarta.values()[i] + "\n";
            } else if (contadores[i] == 3) {
                resultado += "Terna de " + NombreCarta.values()[i] + "\n";
            }
        }

        // 2. Detectar escaleras por pinta
        for (Pinta pinta : Pinta.values()) {
            int[] valores = new int[13];
            for (Carta carta : cartas) {
                if (carta.getPinta() == pinta) {
                    valores[carta.getNombre().ordinal()] = 1;
                }
            }
            // Buscar secuencias de 2 o más cartas consecutivas
            int inicio = -1, fin = -1, count = 0;
            for (int i = 0; i < valores.length; i++) {
                if (valores[i] == 1) {
                    if (inicio == -1) inicio = i;
                    fin = i;
                    count++;
                } else {
                    if (count >= 2) {
                        resultado += (count == 2 ? "Par" : "Terna") + " de " + pinta + " de " +
                            NombreCarta.values()[inicio] + " a " + NombreCarta.values()[fin] + "\n";
                    }
                    inicio = -1; fin = -1; count = 0;
                }
            }
            if (count >= 2) {
                resultado += (count == 2 ? "Par" : "Terna") + " de " + pinta + " de " +
                    NombreCarta.values()[inicio] + " a " + NombreCarta.values()[fin] + "\n";
            }
        }

        // 3. Cartas que sobran (no están en pares, ternas ni escaleras)
        resultado += "\nSobran:\n";
        for (Carta carta : cartas) {
            int idx = carta.getNombre().ordinal();
            boolean enGrupo = contadores[idx] > 1;
            if (!enGrupo) {
                resultado += NombreCarta.values()[idx] + " de " + carta.getPinta() + "\n";
            }
        }

        // 4. Calcular puntos de las cartas que sobran
        int puntos = 0;
        for (Carta carta : cartas) {
            int idx = carta.getNombre().ordinal();
            boolean enGrupo = contadores[idx] > 1;
            if (!enGrupo) {
                // Ace, Jack, Queen, King valen 10 puntos
                if (idx == 0 || idx == 10 || idx == 11 || idx == 12) {
                    puntos += 10;
                } else {
                    puntos += idx + 1;
                }
            }
        }
        resultado += "\nPuntos:\n" + puntos;

        return resultado;
    }

}
