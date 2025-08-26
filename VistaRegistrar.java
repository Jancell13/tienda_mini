package vista;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class VistaRegistrar extends JFrame {
    private JPanel datos,detalles;
    private Container contenedor;
    private JLabel lid,lnombre,lprecio,lsalario;
    public JButton buscar,eliminar,actualizar,registrar,enviar,limpiar;
    public JTextField tid,tnombre,tprecio,tcantidad;
    public JTable tabla;
    private GridLayout grid,grid2;
    private FlowLayout miflow;
    private JScrollPane miscroll;
    private TitledBorder titulo,titulo2;
    private DefaultTableModel modelo;
    public VistaRegistrar() {
        super("Tienda Mini");
        contenedor=getContentPane();
        miflow=new FlowLayout();
        contenedor.setLayout(miflow);
        titulo=new TitledBorder("Productos");
        titulo.setTitleColor(Color.BLUE);
        datos=new JPanel();
        datos.setBorder(titulo);
        grid=new GridLayout(6,3,2,3);
        datos.setLayout(grid);
        //etiqueta
        lid=new JLabel("Id");
        lnombre=new JLabel("Nombre");
        lprecio=new JLabel("Apellido");
        lsalario=new JLabel("Salario");
        //campos de texto
        tid=new JTextField(10);
        tid.setForeground(Color.BLUE);
        tid.setFont(new Font("Time new roman", Font.BOLD, 15));
        
        tnombre=new JTextField(10);
        tnombre.setForeground(Color.BLUE);
        tnombre.setFont(new Font("Time new roman", Font.BOLD, 15));
        
        tprecio=new JTextField(10);
        tprecio.setForeground(Color.BLUE);
        tprecio.setFont(new Font("Time new roman", Font.BOLD, 15));
        
        tcantidad=new JTextField(10);
        tcantidad.setForeground(Color.BLUE);
        tcantidad.setFont(new Font("Time new roman", Font.BOLD, 15));
        
        //botones
        buscar=new JButton("Buscar");
        eliminar=new JButton("Eliminar");
        actualizar=new JButton("actualizar");
        registrar=new JButton("Registrar");
        limpiar=new JButton("Limpiar");
        
        
        datos.add(lid);
        datos.add(tid);
        datos.add(buscar);
        
        datos.add(lnombre);
        datos.add(tnombre);
        datos.add(eliminar);
        
        datos.add(lprecio);
        datos.add(tprecio);
        datos.add(actualizar);
        
        datos.add(lsalario);
        datos.add(tcantidad);
        datos.add(registrar);
        
        JButton v = new JButton("a");
        JButton v2 = new JButton("a");
        datos.add(v).setVisible(false);
        datos.add(v2).setVisible(false);
        datos.add(limpiar);
        
        //
        modelo=new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("PRECIO");
        modelo.addColumn("CANTIDAD");
        
        detalles=new JPanel();
        grid2=new GridLayout(1,1,2,3);
        
        tabla=new JTable(modelo);
        miscroll=new JScrollPane(tabla);
        miscroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        miscroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        detalles.add(miscroll);
        titulo2=new TitledBorder("Datos de los productos");
        titulo2.setTitleColor(Color.BLUE);
        detalles.setBorder(titulo2);
        detalles.setLayout(grid2);
        
        contenedor.add(datos);
        contenedor.add(detalles);
        
        contenedor.setBackground(Color.lightGray);
    }
    
}
