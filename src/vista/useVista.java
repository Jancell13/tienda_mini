package vista;


import java.io.IOException;
import javax.swing.JFrame;
import vista.metodosDePagos;
import controlador.*;
public class useVista extends metodosDePagos{

    public static void main(String[] args) throws IOException {
        //Vista v = new Vista();
        //metodosDePagos v = new metodosDePagos();
        //Login v = new Login();
        //GestionDeUsuario v = new GestionDeUsuario();
        //Registro v = new Registro();
        VistaRegistrar v = new VistaRegistrar();
        //v.listarMetodoBitcoin();
        //Controlador c1 = new Controlador(v);
        //ControladorL c1 = new ControladorL(v);
        //ControlladorR c1 = new ControlladorR();
        v.setSize(674,720);
        v.setVisible(true);
        v.setLocationRelativeTo(null);
        v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
