package vista;


import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Vista extends JFrame {
    private JPanel datosProductos,productos,MedioPago,EmergentePago;
    private Container contenedor;
    public JComboBox modoDePago;
    public JButton pagar;
    public String ListaPago[]={"Hola","Mundo"};
    private JScrollPane miScroll, miScrollproductos;
    private GridLayout grid,grid2;
    private FlowLayout miflow;
    public JTable tabla,tablaProducto;
    private DefaultTableModel campos,camposProducto;
    
    public Vista(){
        super("****** Tienda Mini ******");
        contenedor=getContentPane();
        miflow=new FlowLayout();
        
        contenedor.setLayout(miflow);
        
        
        datosProductos = new JPanel();
        grid2=new GridLayout(2,1,2,3);
        campos=new DefaultTableModel();
        campos.addColumn("NombreProducto");
        campos.addColumn("CantidadProducto");
        campos.addColumn("PrecioTotal");
        tabla = new JTable(campos);
        miScroll=new JScrollPane(tabla);
        miScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        datosProductos.add(miScroll);
        datosProductos.setLayout(grid2);
        
        MedioPago = new JPanel();
        modoDePago = new JComboBox(ListaPago);
        pagar = new JButton("Pagar");
        MedioPago.add(modoDePago);
        MedioPago.setLayout(grid2);
        MedioPago.add(pagar);
        
        productos = new JPanel();
        grid = new GridLayout(1,1,2,3);
        camposProducto = new DefaultTableModel();
        camposProducto.addColumn("ID");
        camposProducto.addColumn("NombreProducto");
        camposProducto.addColumn("PrecioUND");
        camposProducto.addColumn("CantidadStock");
        tablaProducto = new JTable(camposProducto);
        productos.add(miScrollproductos);
        productos.setLayout(grid);
        
        contenedor.add(datosProductos);
        contenedor.add(MedioPago);
        contenedor.add(productos);
        
        contenedor.setVisible(true);
        contenedor.setSize(526,720);
        contenedor.setLocation(300, 500);
    }  
}
