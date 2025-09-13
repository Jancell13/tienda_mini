import javax.swing.*;
import java.awt.*;

public class ModalComoJFrame {
    public static void main(String[] args) {
        JFrame framePrincipal = new JFrame("Ventana Principal");
        framePrincipal.setSize(400, 300);
        framePrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        framePrincipal.setLocationRelativeTo(null);

        JButton boton = new JButton("Abrir ventana modal");
        boton.addActionListener(e -> abrirVentanaModal(framePrincipal));

        framePrincipal.add(boton, BorderLayout.CENTER);
        framePrincipal.setVisible(true);
    }
    
    private static void abrirVentanaModal(JFrame padre) {
        // JDialog modal con aspecto de JFrame
        JDialog dialogo = new JDialog(padre, "Ventana Modal (parece JFrame)", true);
        dialogo.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialogo.setSize(300, 200);
        dialogo.setLocationRelativeTo(padre);

        // Contenido de la ventana
        JPanel panel = new JPanel();
        panel.add(new JLabel("Esta es una ventana modal"));
        JButton cerrar = new JButton("Cerrar");
        cerrar.addActionListener(ev -> dialogo.dispose());
        panel.add(cerrar);

        dialogo.setContentPane(panel);

        // Hacer que se vea como JFrame
        dialogo.setResizable(true);  // permitir redimensionar
        dialogo.setVisible(true);
    }
}
