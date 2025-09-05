package vista;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
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

public class GestionDeUsuario extends JFrame {
    private JPanel datos,detalles;
    private Container contenedor;
    private JLabel lid,lnombre,lapellido,ldocumento,lcorreo,lcontraseña,lmetodoPago;
    public JButton buscar,eliminar,actualizar,registrar,enviar,limpiar;
    public JTextField tid,tnombre,tapellido,tcorreo,tcontraseña,tdocumento;
    public JTable tabla;
    private GridLayout grid,grid2;
    private FlowLayout miflow;
    private JScrollPane miscroll;
    private TitledBorder titulo,titulo2;
    public JComboBox listaR;
    private DefaultTableModel modelo;

    public GestionDeUsuario(){
        super("-----TIENDA MINI-----");
        contenedor=getContentPane();
        miflow=new FlowLayout();
        contenedor.setLayout(miflow);
        titulo=new TitledBorder("Datos del Cliente");
        titulo.setTitleColor(Color.BLUE);
        datos=new JPanel();
        datos.setBorder(titulo);
        grid=new GridLayout(0,3,2,3);
        datos.setLayout(grid);
        //etiqueta
        lid=new JLabel("Id");
        ldocumento=new JLabel("Documento");
        lnombre=new JLabel("Nombre");
        lapellido=new JLabel("Apellido");
        lcorreo=new JLabel("Correo");
        lcontraseña=new JLabel("Contraseña");
        lmetodoPago=new JLabel("Rol");
        //campos de texto
        tid=new JTextField(20);
        tid.setForeground(Color.BLUE);
        
        tdocumento=new JTextField(10);
        tdocumento.setForeground(Color.BLUE);
        
        tnombre=new JTextField(10);
        tnombre.setForeground(Color.BLUE);
        
        tapellido=new JTextField(10);
        tapellido.setForeground(Color.BLUE);
        
        tcorreo=new JTextField(10);
        tcorreo.setForeground(Color.BLUE);
        
        tcontraseña=new JTextField(10);
        tcontraseña.setForeground(Color.BLUE);
        
        

        //botones
        buscar=new JButton("Buscar");
        eliminar=new JButton("Eliminar");
        actualizar=new JButton("Actualizar");
        registrar=new JButton("Registrar");
        limpiar=new JButton("Limpiar");
        
        listaR=new JComboBox();
        
        
        datos.add(lid);
        datos.add(tid);
        datos.add(buscar);
        
        datos.add(lnombre);
        datos.add(tnombre);
        datos.add(eliminar);
        
        datos.add(lapellido);
        datos.add(tapellido);
        datos.add(actualizar);
        
        datos.add(ldocumento);
        datos.add(tdocumento);
        datos.add(registrar);
        
        datos.add(lcorreo);
        datos.add(tcorreo);
        datos.add(limpiar);
        
        datos.add(lcontraseña);
        datos.add(tcontraseña);
        datos.add(new JLabel());
        datos.add(lmetodoPago);
        datos.add(listaR);
        
        //
        modelo=new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("DOCUMENTO");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("APELLIDO");
        modelo.addColumn("CORREO");
        modelo.addColumn("CONTRASEÑA");
        detalles=new JPanel();
        grid2=new GridLayout(1,1,2,3);
        
        tabla=new JTable(modelo);
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.getTableHeader().setResizingAllowed(false);
        miscroll=new JScrollPane(tabla);
        miscroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        miscroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        detalles.add(miscroll);
        detalles.setPreferredSize(new Dimension(640, 440));
        titulo2=new TitledBorder("Datos del trabajador");
        titulo2.setTitleColor(Color.BLUE);
        detalles.setBorder(titulo2);
        detalles.setLayout(grid2);
        
        contenedor.add(datos);
        contenedor.add(detalles);
        
        contenedor.setBackground(Color.lightGray);
        
    }
    public void validarGestionUsuario(String boton){
        //lid,lnombre,lapellido,ldocumento,lcorreo,lcontraseña,lmetodoPago;
        //buscar,eliminar,actualizar,registrar,enviar,limpiar;
    }
}
