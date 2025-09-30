package controlador;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JOptionPane;

import modelo.Usuario;
import modelo.UsuarioDao;
import vista.Registro;

public class ControladorR implements ActionListener {
    public UsuarioDao udao = new UsuarioDao();
    public Usuario u = new Usuario();
    public Registro viewR = new Registro();

    public ControladorR(Registro r) throws IOException {
        this.viewR = r;
        this.viewR.registro.addActionListener(this);
        this.viewR.iniciarSesion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                viewR.dispose();
            }
        });
        this.viewR.iniciarSesion.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewR.registro) {

            if (!viewR.inputDocumento.getText().isEmpty() && !viewR.inputNombre.getText().isEmpty()
                    && !viewR.inputApellido.getText().isEmpty()
                    && !viewR.inputCorreo.getText().isEmpty() && viewR.inputContraseña.getPassword().length > 0) {
                setAdd();
            } else {
                JOptionPane.showMessageDialog(viewR, "faltan campos por diligenciar");
            }
            limpiarCampos();
        }
    }

    public void setAdd() {
        int resultado;
        int documento = Integer.parseInt(viewR.inputDocumento.getText());
        String nombre = viewR.inputNombre.getText();
        String apellido = viewR.inputApellido.getText();
        String correo = viewR.inputCorreo.getText();
        String contraseña = new String(viewR.inputContraseña.getPassword());

        u.setDocumento(documento);
        u.setNombre(nombre);
        u.setApellido(apellido);
        u.setEmail(correo);
        u.setPassword(contraseña);

        resultado = udao.setAgregar(u);

        if (resultado == 1) {
            JOptionPane.showMessageDialog(viewR, "usuarios registrado exitosamente");
        } else {
            JOptionPane.showMessageDialog(viewR, "Error al registrar al usuario " + JOptionPane.ERROR_MESSAGE);
        }
    }

    public void limpiarCampos() {
        viewR.inputNombre.setText("");
        viewR.inputApellido.setText("");
        viewR.inputDocumento.setText("");
        viewR.inputCorreo.setText("");
        viewR.inputContraseña.setText("");
    }

}
