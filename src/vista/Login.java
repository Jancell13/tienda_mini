package vista;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class Login extends JFrame {
    
    Validaciones validar = new Validaciones();
    public Container container;
    private JPanel logoPrime, formuSegun;
    private JPanel containerLogo, containerFormu;
    private FlowLayout miflow;
    public JTextField inputCorreo;
    public JPasswordField inputContraseña;
    private JLabel bienvenidos, login, logo;//titulos
    private TitledBorder campoCorreo, campoContra;
    public JButton ingresar;

    public Login() throws IOException {
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
        login = new JLabel("Inicio de Sesión");
        login.setFont(new Font("Times New Roman", Font.PLAIN, 30));

        // Campos
        campoCorreo = new TitledBorder("Correo Electronico");
        inputCorreo = new JTextField(15);
        inputCorreo.setOpaque(false);
        inputCorreo.setBorder(campoCorreo);

        campoContra = new TitledBorder("Contraseña");
        inputContraseña = new JPasswordField(15);
        inputContraseña.setOpaque(false);
        inputContraseña.setBorder(campoContra);

        // Botón
        ingresar = new JButton("Iniciar Sesion");

        // Panel que agrupa título, campos y botón en columna
        containerFormu = new JPanel(new GridLayout(0, 1, 10, 10));
        containerFormu.add(login);
        containerFormu.add(inputCorreo);
        containerFormu.add(inputContraseña);
        containerFormu.add(ingresar);
        
        ingresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validarFormularioLOGIN();
            }
        });
        
        // Agrego containerFormu dentro de auxFormu
        auxFormu.add(containerFormu);

        // Con GridBagLayout se centra auxFormu en formuSegun
        formuSegun.add(auxFormu, new java.awt.GridBagConstraints());

        container.add(logoPrime);
        container.add(formuSegun);
    }
    private boolean validarFormularioLOGIN(){
        //inputCorreo, inputContraseña;
        String correo = inputCorreo.getText();
        String contraseña = inputContraseña.getText();
        
        if(!Validaciones.validarCorreo(correo)){
            JOptionPane.showMessageDialog(this,"Correo no valido");
            return false;
        }
        if(!Validaciones.validarContraseña(contraseña)){
            JOptionPane.showMessageDialog(this,"Contraseña no valida");
            return false;
        }
        return true;
    }
}
