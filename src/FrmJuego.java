import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
//import javax.swing.WindowConstants;

public class FrmJuego extends JFrame {

    private JPanel pnlJugador1, pnlJugador2;
    private Jugador jugador1, jugador2;
    JTabbedPane tpJugadores;
    
    // Constructor para inicializar la ventana del juego
    public FrmJuego() {
        setTitle("Juego de Cartas");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // esto centra la ventana en la pantalla
        setLayout(null);

        JButton btnRepartir = new JButton("Repartir");
        btnRepartir.setBounds(10, 10, 100, 25);
        getContentPane().add(btnRepartir);

        JButton btnVerificar = new JButton("Verificar");
        btnVerificar.setBounds(120, 10, 100, 25);
        getContentPane().add(btnVerificar);

        pnlJugador1 = new JPanel();
        pnlJugador1.setBackground(new Color(50, 255, 0));
        pnlJugador1.setLayout(null);
        pnlJugador2 = new JPanel();
        pnlJugador2.setBackground(new Color(0, 255, 255));
        pnlJugador2.setLayout(null);

        tpJugadores = new JTabbedPane();
        tpJugadores.addTab("Deiber Herrera", pnlJugador1);
        tpJugadores.addTab("Alexander Gamarra", pnlJugador2);
        tpJugadores.setBounds(10, 40, 550, 200);
        getContentPane().add(tpJugadores);

        //agregar eventos a los botones
        btnRepartir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para repartir cartas a los jugadores
                repartir();
            }
        });

        btnVerificar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                verificar();
            }

        });

        // crear las instancias de los jugadores
        jugador1 = new Jugador();
        jugador2 = new Jugador();

    }
        // Método para repartir cartas a los jugadores
        private void repartir() {
            jugador1.repartir();
            jugador2.repartir();

            jugador1.mostrar(pnlJugador1);
            jugador2.mostrar(pnlJugador2);

        }

        // Método para verificar las manos de los jugadores
        private void verificar() {
            switch (tpJugadores.getSelectedIndex()) {
                case 0:
                    JOptionPane.showMessageDialog(null, jugador1.getGrupos());
                    break;
                case 1:
                    JOptionPane.showMessageDialog(null, jugador2.getGrupos());
                    break;           }
           
        }
     

}