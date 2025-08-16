
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Vista extends JFrame {
    private JPanel datosProductos, productos,MedioPago,EmergentePago;
    private Container contenedor;
    public JComboBox modoDePago;
    public JButton pagar;
    public String ListaPago[]={};
    private JScrollPane miScroll;
    private GridLayout grid;
    private FlowLayout miflow;
    
    public Vista(){
        super("****** Tienda Mini ******");
        contenedor=getContentPane();
    }
    
}
