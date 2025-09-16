package controlador;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

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

    }

}
