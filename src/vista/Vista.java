package vista;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Vista extends JFrame {
    private JFrame frame;
    private JPanel datosProductos,productos,MedioPago;
    private Container contenedor, contenedor2;
    public JComboBox modoDePago;
    public JButton pagar, registrar;
    public String ListaPago[]={"Hola","Mundo"};
    private JScrollPane miScroll, miScrollproductos;
    private FlowLayout miflow;
    public JTable tabla,tablaProducto;
    private DefaultTableModel campos,camposProducto;
    
    public Vista(){
        super("****** Tienda Mini ******");
        frame = new JFrame();
        miflow = new FlowLayout();
        frame.setLayout(miflow);
        contenedor= new JPanel(new GridLayout(1,2,1,1));
        contenedor2 = new JPanel(new BorderLayout());
        
        datosProductos = new JPanel();
        campos=new DefaultTableModel();
        campos.addColumn("NombreProducto");
        campos.addColumn("CantidadProducto");
        campos.addColumn("PrecioTotal");
        tabla = new JTable(campos);
        miScroll=new JScrollPane(tabla);
        miScroll.setPreferredSize(new java.awt.Dimension(320, 200));
        miScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        datosProductos.add(miScroll);
        
        MedioPago = new JPanel();
        modoDePago = new JComboBox(ListaPago);
        pagar = new JButton("Pagar");
        registrar = new JButton("Registrar");
        MedioPago.add(modoDePago,BorderLayout.NORTH);
        MedioPago.add(pagar,BorderLayout.SOUTH);
        contenedor2.add(MedioPago);
        
        productos = new JPanel();
        camposProducto = new DefaultTableModel();
        camposProducto.addColumn("ID");
        camposProducto.addColumn("NombreProducto");
        camposProducto.addColumn("PrecioUND");
        camposProducto.addColumn("CantidadStock");
        tablaProducto = new JTable(camposProducto);
        miScrollproductos=new JScrollPane(tablaProducto);
        miScrollproductos.setPreferredSize(new java.awt.Dimension(450, 420));
        miScrollproductos.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        productos.add(miScrollproductos);
        
        

        contenedor.add(datosProductos);
        contenedor.add(contenedor2);
        contenedor.setLayout(new FlowLayout());
        frame.add(contenedor);
        frame.add(productos);
        frame.setLocationRelativeTo(null);
        frame.setSize(650,720);
        frame.setVisible(true);
        frame.setResizable(false);
    }  
}
