package vista;

import java.awt.Container;
import java.awt.PopupMenu;
import javax.swing.JFrame;
import vista.metodosDePagos;
public class useVista extends metodosDePagos{

    public static void main(String[] args) {
        Vista v = new Vista();
        //metodosDePagos v = new metodosDePagos();
        //v.listarMetodoCredito();
        v.setSize(674,720);
        v.setVisible(true);
        v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
