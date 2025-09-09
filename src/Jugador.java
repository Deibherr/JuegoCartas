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
        
        /*
         * SISTEMA DE PUNTUACIÓN:
         * - A, J, Q, K valen 10 puntos cada uno
         * - Del 2 al 9 valen su valor numérico (2=2 puntos, 3=3 puntos, etc.)
         * 
         * REGLAS PARA GRUPOS:
         * - Pares: 2 cartas del mismo nombre
         * - Ternas: 3 cartas del mismo nombre  
         * - Cuartetos: 4 cartas del mismo nombre
         * - Escaleras: 2 o más cartas consecutivas de la misma pinta
         */
        
        // 1. Detectar pares, ternas y cuartetos por nombre
        int[] contadores = new int[NombreCarta.values().length];
        for (Carta carta : cartas) {
            contadores[carta.getNombre().ordinal()]++;
        }
        for (int i = 0; i < contadores.length; i++) {
            switch (contadores[i]) {
                case 2:
                    resultado += "Par de " + NombreCarta.values()[i] + "\n";
                    break;
                case 3:
                    resultado += "Terna de " + NombreCarta.values()[i] + "\n";
                    break;
                case 4:
                    resultado += "Cuarteto de " + NombreCarta.values()[i] + "\n";
                    break;
            }
        }

        // 2. Detectar escaleras por pinta (2 o más cartas consecutivas de la misma pinta)
        for (Pinta pinta : Pinta.values()) {
            int[] valores = new int[13];
            for (Carta carta : cartas) {
                if (carta.getPinta() == pinta) {
                    valores[carta.getNombre().ordinal()] = 1;
                }
            }
            // Buscar secuencias de 2 o más cartas consecutivas de la misma pinta
            int inicio = -1, fin = -1, count = 0;
            for (int i = 0; i < valores.length; i++) {
                if (valores[i] == 1) {
                    if (inicio == -1) inicio = i;
                    fin = i;
                    count++;
                } else {
                    if (count >= 2) {
                        resultado += "Escalera de " + pinta + " de " +
                            NombreCarta.values()[inicio] + " a " + NombreCarta.values()[fin] + "\n";
                    }
                    inicio = -1; fin = -1; count = 0;
                }
            }
            if (count >= 2) {
                resultado += "Escalera de " + pinta + " de " +
                    NombreCarta.values()[inicio] + " a " + NombreCarta.values()[fin] + "\n";
            }
        }

        // 3. Cartas que sobran (no están en pares, ternas, cuartetos ni escaleras)
        resultado += "\nSobran:\n";
        for (Carta carta : cartas) {
            int idx = carta.getNombre().ordinal();
            boolean enGrupo = contadores[idx] > 1; // Considera pares, ternas y cuartetos
            boolean enEscalera = estaEnEscalera(carta, cartas);
            if (!enGrupo && !enEscalera) {
                resultado += NombreCarta.values()[idx] + " de " + carta.getPinta() + "\n";
            }
        }

        // 4. Calcular puntos de las cartas que sobran (no están en pares, ternas, cuartetos ni escaleras)
        int puntos = 0;
        for (Carta carta : cartas) {
            int idx = carta.getNombre().ordinal();
            boolean enGrupo = contadores[idx] > 1; // Considera pares, ternas y cuartetos
            boolean enEscalera = estaEnEscalera(carta, cartas);
            if (!enGrupo && !enEscalera) {
                // Aplicar sistema de puntuación: A, J, Q, K = 10 puntos, 2-9 = valor numérico
                if (idx == 0 || idx == 10 || idx == 11 || idx == 12) {
                    // AS (idx=0), JACK (idx=10), QUEEN (idx=11), KING (idx=12) valen 10 puntos
                    puntos += 10;
                } else {
                    // Del 2 al 9 valen su valor numérico (idx+1 porque el array empieza en 0)
                    puntos += idx + 1;
                }
            }
        }
        resultado += "\nPuntos:\n" + puntos;

        return resultado;
    }
    
    /**
     * Verifica si una carta está en una escalera (2 o más cartas consecutivas de la misma pinta)
     */
    private boolean estaEnEscalera(Carta carta, Carta[] cartas) {
        Pinta pinta = carta.getPinta();
        int[] valores = new int[13];
        
        // Marcar las cartas de la misma pinta
        for (Carta c : cartas) {
            if (c.getPinta() == pinta) {
                valores[c.getNombre().ordinal()] = 1;
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
                    // Verificar si la carta está en esta secuencia
                    int cartaIdx = carta.getNombre().ordinal();
                    if (cartaIdx >= inicio && cartaIdx <= fin) {
                        return true;
                    }
                }
                inicio = -1; fin = -1; count = 0;
            }
        }
        
        // Verificar la última secuencia si termina al final del array
        if (count >= 2) {
            int cartaIdx = carta.getNombre().ordinal();
            if (cartaIdx >= inicio && cartaIdx <= fin) {
                return true;
            }
        }
        
        return false;
    }

}
