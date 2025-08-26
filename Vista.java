package vista;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class Vista extends JFrame implements ActionListener{
    private JPanel producMetodo,productoCompra,medioPago,medioPagoBtn,productos;
    private Container contenedor;
    public JComboBox modoDePago;
    public JButton pagar, registrar, invi;
    public String ListaPago[]={"Hola","Mundo"};
    private JScrollPane miScroll, miScrollproductoCompra;
    private FlowLayout miflow;
    public JTable tabla,tablaProducto;
    private DefaultTableModel campos,camposProducto;
    private TitledBorder titulo,titulo2,titulo3;
    
    public Vista(){
        super("______Tienda Mini______");
        contenedor=getContentPane();
        miflow = new FlowLayout();
        contenedor.setLayout(miflow);
        
        //contenedor para los productoCompra y el botón de metodo de pago
        producMetodo = new JPanel(new GridLayout(1, 2));
        producMetodo.setPreferredSize(new Dimension(650, 200));
        
        //panel para los productoCompra, viene con scroll, tabla
        productoCompra = new JPanel(new GridLayout(1, 2,2,3));
        campos=new DefaultTableModel();
        campos.addColumn("NombreProducto");
        campos.addColumn("CantidadProducto");
        campos.addColumn("PrecioTotal");
        tabla = new JTable(campos);
        tabla.getTableHeader().setReorderingAllowed(false);
        miScrollproductoCompra=new JScrollPane(tabla);
        miScrollproductoCompra.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        productoCompra.add(miScrollproductoCompra);
        titulo=new TitledBorder("Datos de los productos Comprados");
        titulo.setTitleColor(Color.BLUE);
        productoCompra.setBorder(titulo);
        
        //panel para el medioPagoBtn de metodo de pago, BORDERLAYOUT para darle una ubicacion al norte
        medioPago = new JPanel(new BorderLayout());
        
        medioPagoBtn = new JPanel(new GridLayout(2,2));
        modoDePago = new JComboBox(ListaPago);
        pagar = new JButton("Pagar");
        pagar.addActionListener(this);
        invi = new JButton("Pagar");
        invi.setVisible(false);
        registrar = new JButton("Registrar");
        registrar.addActionListener(this);
        medioPagoBtn.add(modoDePago);
        medioPagoBtn.add(pagar);
        medioPagoBtn.add(invi);
        medioPagoBtn.add(registrar);
        medioPago.add(medioPagoBtn, BorderLayout.NORTH);
        titulo3=new TitledBorder("Medios de pago");
        titulo3.setTitleColor(Color.BLUE);
        medioPago.setBorder(titulo3);
        
        //agrego los JPanel de productoCompra y medioPago
        producMetodo.add(productoCompra);
        producMetodo.add(medioPago);
        
        //panel de abajo que va a contener todos lo productos con su nombre,id,cantiStock y demas
        productos = new JPanel(new BorderLayout());
        productos.setPreferredSize(new Dimension(651, 450));
        camposProducto = new DefaultTableModel();
        camposProducto.addColumn("ID");
        camposProducto.addColumn("NombreProducto");
        camposProducto.addColumn("PrecioUND");
        camposProducto.addColumn("CantidadStock");
        tablaProducto = new JTable(camposProducto);
        //comando para evitar que muevan el healer con el mouse
        tablaProducto.getTableHeader().setReorderingAllowed(false);
        miScroll=new JScrollPane(tablaProducto);
        miScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        miScroll.setPreferredSize(new Dimension(641, 450));
        productos.add(miScroll,BorderLayout.WEST);
        titulo2=new TitledBorder("Datos de los productos");
        titulo2.setTitleColor(Color.BLUE);
        productos.setBorder(titulo2);
        
        //agrego los todos los componentes al contenedor
        contenedor.add(producMetodo);
        contenedor.add(productos);
    }  

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
