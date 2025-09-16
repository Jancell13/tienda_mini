package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class Registro extends JFrame{
    //nombre, apellido, correo, documento,contraseña,
    public Container container;
    private JPanel logoPrime, formuSegun;
    private JPanel containerFormu;
    public JLabel  iniciarSesion;
    private FlowLayout miflow;
    private JPasswordField inputContraseña;
    public JTextField inputCorreo,inputNombre,inputApellido,inputDocumento;
    private JLabel bienvenidos, login, logo;//titulos
    private TitledBorder campoCorreo, campoContra,campoNombre,campoApellido,campoDocumento;
    public JButton registro;
    public Registro() throws IOException {
        super("---LOGIN TIENDA MINI ---- ");
        container = getContentPane();
        miflow = new FlowLayout();
        container.setLayout(new GridLayout());

        //primera parte Logo
        logoPrime = new JPanel(new BorderLayout());

        BufferedImage originalIcon = ImageIO.read(new File("src/imgs/logoTiendaMini.png"));
        Image icono = originalIcon.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon icono2 = new ImageIcon(icono);

        JLabel logo = new JLabel(icono2);

        logoPrime.add(logo, BorderLayout.CENTER);

        //segunda Parte formulario
        formuSegun = new JPanel(new GridBagLayout());
        formuSegun.setPreferredSize(new Dimension(330, 420));

        JPanel auxFormu = new JPanel(new FlowLayout(FlowLayout.CENTER));
        auxFormu.setPreferredSize(new Dimension(200, 420));

        // Título
        login = new JLabel("Registro de Usuario");
        login.setFont(new Font("Times New Roman", Font.PLAIN, 30));

        // Campos
        campoNombre = new TitledBorder("Nombres de Usuario*");
        inputNombre = new JTextField(15);
        inputNombre.setOpaque(false);
        inputNombre.setBorder(campoNombre);
        
        campoApellido = new TitledBorder("Apellidos de usuario*");
        inputApellido = new JTextField(15);
        inputApellido.setOpaque(false);
        inputApellido.setBorder(campoApellido);
        
        campoDocumento = new TitledBorder("Documento de usuario*");
        inputDocumento = new JTextField(15);
        inputDocumento.setOpaque(false);
        inputDocumento.setBorder(campoDocumento);
        
        campoCorreo = new TitledBorder("Correo Electronico");
        inputCorreo = new JTextField(15);
        inputCorreo.setOpaque(false);
        inputCorreo.setBorder(campoCorreo);

        campoContra = new TitledBorder("Contraseña");
        inputContraseña = new JPasswordField(15);
        inputContraseña.setOpaque(false);
        inputContraseña.setBorder(campoContra);
        
        // Botón
        registro = new JButton("Registrarse");
        registro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validarRegistro();
            }
        });
        iniciarSesion = new JLabel("¿Tienes una cuenta? iniciar Sesión");
        // Panel que agrupa título, campos y botón en columna
        containerFormu = new JPanel(new GridLayout(0, 1, 10, 10));
        containerFormu.add(login);
        containerFormu.add(inputDocumento);
        containerFormu.add(inputNombre);
        containerFormu.add(inputApellido);
        containerFormu.add(inputCorreo);
        containerFormu.add(inputContraseña);
        containerFormu.add(registro);
        containerFormu.add(iniciarSesion);

        // Agrego containerFormu dentro de auxFormu
        auxFormu.add(containerFormu);

        // Con GridBagLayout se centra auxFormu en formuSegun
        formuSegun.add(auxFormu, new java.awt.GridBagConstraints());

        container.add(logoPrime);
        container.add(formuSegun);
    }
    public boolean validarRegistro(){
        //inputContraseña inputCorreo,inputNombre,inputApellido,inputDocumento;
        String documento = inputDocumento.getText();
        String nombre = inputNombre.getText();
        String apellido = inputApellido.getText();
        String correo = inputCorreo.getText();
        String contraseña = inputContraseña.getText();
        
        if(!Validaciones.validarCedula(documento)){
            JOptionPane.showMessageDialog(this, "Documento solo debe tener numeros");
            return false;
        }
        if(!Validaciones.validarLetras(nombre)){
            JOptionPane.showMessageDialog(this, "El nombre solo debe contener letras");
            return false;
        }
        if(!Validaciones.validarLetras(apellido)){
            JOptionPane.showMessageDialog(this, "El Apellido solo debe contener letras");
            return false;
        }
        if(!Validaciones.validarCorreo(correo)){
            JOptionPane.showMessageDialog(this, "El Correo debe tener un punto y @");
            return false;
        }
        if(!Validaciones.validarContraseña(contraseña)){
            JOptionPane.showMessageDialog(this, "La contraseña debe ser mas de 8 digitos\n"
            + "tener almenos una mayuscula, un numero y una minuscula");
            return false;
        }
        return true;
    }
}
