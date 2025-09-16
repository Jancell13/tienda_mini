package controlador;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import modelo.Usuario;
import modelo.UsuarioDao;
import vista.Login;
import vista.Registro;
import vista.Validaciones;
import vista.Vista;

public class ControladorL implements ActionListener {
    public UsuarioDao udao = new UsuarioDao();
    public Usuario u = new Usuario();
    public Login viewLogin = new Login();

    public ControladorL(Login l) throws IOException {
        this.viewLogin = l;
        this.viewLogin.ingresar.addActionListener(this);
        this.viewLogin.registrar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                formuRegistro();
            }
        });
        this.viewLogin.registrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == viewLogin.ingresar) {

            String correo = viewLogin.inputCorreo.getText();
            String contraseña = viewLogin.inputContraseña.getPassword().toString();

            if (!Validaciones.validarCorreo(correo)) {
                JOptionPane.showMessageDialog(this.viewLogin, "Campo de Correo no valido, ejemplo:\nmicorreo@algo.com");
                return;
            } else if (!Validaciones.validarContraseña(contraseña)) {
                JOptionPane.showMessageDialog(this.viewLogin, "Campo Contraseña no valida");
                return;
            } else {
                List<Usuario> user = udao.getUsuarioPorCorreo(viewLogin.inputCorreo.getText());
                Object[] object = new Object[3];
                for (int indice = 0; indice < user.size(); indice++) {

                    object[0] = user.get(indice).getEmail();
                    object[1] = user.get(indice).getPassword();
                    object[2] = user.get(indice).getRol();
                }
                if (viewLogin.inputCorreo.getText().equals(object[0])
                        && String.valueOf(viewLogin.inputContraseña.getPassword()).equals(object[1])) {
                    validarRol(Integer.parseInt(object[2].toString()));
                } else {
                    JOptionPane.showMessageDialog(this.viewLogin, "Usuario o contraseña incorrectos");
                }
            }
        }
    }

    private void validarRol(int rol) {
        Vista view = new Vista();
        new Controlador(view);
        if (rol == 2) {
            view.registrar.setVisible(false);
        }
        view.setSize(674, 720);
        view.setVisible(true);
        view.setLocationRelativeTo(null);
        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        view.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                limpiarCampos();
                viewLogin.setVisible(true);
            }
        });
        viewLogin.setVisible(false);
    }

    public void limpiarCampos() {
        viewLogin.inputCorreo.setText("");
        viewLogin.inputContraseña.setText("");
    }

    private void formuRegistro() {
        try {
            Registro viewR = new Registro();
            new ControladorR(viewR);
            viewR.setSize(590, 450);
            viewR.setVisible(true);
            viewR.setLocationRelativeTo(null);
            viewR.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            viewR.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent e) {
                    limpiarCampos();
                    viewLogin.setVisible(true);
                }
            });
            viewLogin.setVisible(false);
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }
}
