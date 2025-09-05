package vista;

import java.awt.Container;
import java.awt.PopupMenu;
import java.io.IOException;
import javax.swing.JFrame;
import vista.metodosDePagos;
public class useVista extends metodosDePagos{

    public static void main(String[] args) throws IOException {
        //Vista v = new Vista();
        //metodosDePagos v = new metodosDePagos();
        //Login v = new Login();
        GestionDeUsuario v = new GestionDeUsuario();
        //Registro v = new Registro();
        //VistaRegistrar v = new VistaRegistrar();
        //v.listarMetodoApplePay();
        v.setSize(674,720);
        v.setVisible(true);
        v.setLocationRelativeTo(null);
        v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
