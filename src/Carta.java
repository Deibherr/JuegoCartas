import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Carta {
    /* Atributo privado para almacenar el numero de la carta
     * 1= As de Trebol, 14= As de Pica, 27= As de Corazon, 40= As de Diamante,
     * 2= 2 de Trebol, 15= 2 de Pica, 28= 2 de Corazon, 41= 2 de Diamante,
     * 3= 3 de Trebol, 16= 3 de Pica, 29= 3 de Corazon, 42= 3 de Diamante,
     * ...
     * 10= 10 de Trebol, 23= 10 de Pica, 36= 10 de Corazon, 49= 10 de Diamante,
     * 11= J de Trebol, 24= J de Pica, 37= J de Corazon, 50= J de Diamante,
     * 12= Q de Trebol, 25= Q de Pica, 38= Q de Corazon, 51= Q de Diamante,
     * 13= K de Trebol, 26= K de Pica, 39= K de Corazon, 52= K de Diamante
     */
    private int index;

    public Carta(Random r) {
        // El numero de la carta es generado aleatoriamente
     index = r.nextInt(52) + 1;
    }
    
    public Carta(int index) {
        this.index = index;
    }

    public JLabel mostrar(JPanel pnl, int x, int y) {

        // Muestra la carta en la ventana
        String archivoImagen = "deck/CARTA" + index + ".JPG";
        ImageIcon imgCarta = new ImageIcon(getClass().getResource(archivoImagen));
        JLabel lblCarta = new JLabel();
        lblCarta.setIcon(imgCarta);
        lblCarta.setBounds(x, y, imgCarta.getIconWidth(), imgCarta.getIconHeight());
        pnl.add(lblCarta);
        
        // mouseover information
        lblCarta.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                JOptionPane.showMessageDialog(null, getNombre() + " de " + getPinta());
            }
        });

        return lblCarta;
    }

    public Pinta getPinta() {
        /* Obtiene la pinta que corresponde a la carta,
         * basado en el rango en que se ubica el índice
         */
        if (index <= 13)
            return Pinta.TREBOL;
        else if (index <= 26)
            return Pinta.PICA;
        else if (index <= 39)
            return Pinta.CORAZON;
        else
            return Pinta.DIAMANTE;
    }

    // obtiene el nombre de la carta
    public NombreCarta getNombre() {
        int numero = index % 13;
        if (numero == 0) {
            numero = 13;
        }
        return NombreCarta.values()[numero - 1];
    }

}

