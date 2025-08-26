package vista;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

public class metodosDePagos extends JFrame {
    //numero de tarjeta, fecha de vencimiento, cvv //tarjeta de debito //4,2
    //Boton(Continue)
    //nombre titular,numero de tarjeta, fecha de vencimiento, cvv //tarjeta de credito
    public Container container;
    private JLabel titleDebito,titleFechaDebito,titleCvvDebito;
    private JTextField numTarjeDebito,fechaTarjeDebito,numCvvDebito;
    private JLabel titleNombreCredito,titleCredito,titleFechaCredito,titleCvvCredito,titleMontoCredi,montoCredito;
    private JTextField nombreCredito,numTarjeCredito,fechaTarjeCredito,numCvvCredito;
    private JLabel titleNomTrans,titleNumCuenTra,titleNomBanTra,titleNumContaTra,titleMontoTrans;
    private JTextField nomTrans,numCuentaTra,nomBanTra,numContaTra,montoTrans;
    public JPanel tarjetaCredito,tarjetaDebito,transferencia;
    private TitledBorder titulo,titulo2,titulo3;
    private JButton confirCredi,confirDebito, confirTransfer;
    private FlowLayout miflow;
    public metodosDePagos(){
        super("Metodos de pago");
        //agrego el conteiner con flowLayaout
        container = getContentPane();
        miflow = new FlowLayout();
        container.setLayout(miflow);
        
        //Agrego la tarjeta de debito
        tarjetaDebito = new JPanel(new GridLayout(4,2,2,2));
        titulo = new TitledBorder("Tarjeta de Debito");
        tarjetaDebito.setBorder(titulo);
        
        titleDebito = new JLabel("Numero de Debito");//titulo
        numTarjeDebito = new JTextField(10);//agregar o escribir 
        
        titleFechaDebito = new JLabel("Fecha Vencimiento");
        fechaTarjeDebito = new JTextField(10);
        
        titleCvvDebito = new JLabel("CVV");
        numCvvDebito = new JTextField(10);
        
        confirDebito = new JButton("Continue");
        
        tarjetaDebito.add(titleDebito);
        tarjetaDebito.add(numTarjeDebito);
        tarjetaDebito.add(titleFechaDebito);
        tarjetaDebito.add(fechaTarjeDebito);
        tarjetaDebito.add(titleCvvDebito);
        tarjetaDebito.add(numCvvDebito);
        tarjetaDebito.add(confirDebito);
        
        //Agrego la tarjeta de credito
        tarjetaCredito = new JPanel(new GridLayout(6,2,2,2));
        titulo2 = new TitledBorder("Tarjeta de Credito");
        tarjetaCredito.setBorder(titulo2);
        
        titleNombreCredito = new JLabel("Nombre del Titular");
        nombreCredito = new JTextField(10);
        
        titleCredito = new JLabel("Numero de Tarjeta");//titulo
        numTarjeCredito = new JTextField(10);//agregar o escribir 
        
        titleFechaCredito = new JLabel("Fecha Vencimiento");
        fechaTarjeCredito = new JTextField(10);
        
        titleCvvCredito = new JLabel("CVV");
        numCvvCredito = new JTextField(10);
        
        titleMontoCredi = new JLabel("Monto a pagar");
        montoCredito = new JLabel();
        montoCredito.setHorizontalAlignment(SwingConstants.RIGHT);//POSICIONAR EL TEXTO A LA DERECHA
        
        confirCredi = new JButton("Continuar");
        
        
        tarjetaCredito.add(titleNombreCredito);
        tarjetaCredito.add(nombreCredito);
        tarjetaCredito.add(titleCredito);
        tarjetaCredito.add(numTarjeCredito);
        tarjetaCredito.add(titleFechaCredito);
        tarjetaCredito.add(fechaTarjeCredito);
        tarjetaCredito.add(titleCvvCredito);
        tarjetaCredito.add(numCvvCredito);
        tarjetaCredito.add(titleMontoCredi);
        tarjetaCredito.add(montoCredito);
        tarjetaCredito.add(confirCredi);
        
        //agregar metodo Transferencia
        //nombre destinatario,numero de cuenta,nombre del banco,numero de contacto
        transferencia = new JPanel(new GridLayout(0,2,2,2));
        titulo3 = new TitledBorder("Consignacion Bancaria");
        transferencia.setBorder(titulo3);
        
        titleNomTrans = new JLabel("Nombre del Destinatario");
        nomTrans = new JTextField(10);
        
        titleNumCuenTra = new JLabel("Numero de cuenta");
        numCuentaTra = new JTextField(10);
        
        titleNomBanTra = new JLabel("Nombre de banco");
        nomBanTra = new JTextField(10);
        
        titleNumContaTra = new JLabel("Numero de contacto");
        numContaTra = new JTextField(10);
        
        titleMontoTrans = new JLabel("Monto a Transferir");
        montoTrans = new JTextField(10);
        
        confirTransfer = new JButton("Continuar");
        
        transferencia.add(titleNomTrans);
        transferencia.add(nomTrans);
        transferencia.add(titleNumCuenTra);
        transferencia.add(numCuentaTra);
        transferencia.add(titleNomBanTra);
        transferencia.add(nomBanTra);
        transferencia.add(titleNumContaTra);
        transferencia.add(numContaTra);
        transferencia.add(titleMontoTrans);
        transferencia.add(montoTrans);
        transferencia.add(confirTransfer);
        
        //
        
    }
    public void listarMetodoCredito(){
        container.add(tarjetaCredito);
    }
    public void listarMetodoDebito(){
        container.add(tarjetaDebito);
    }
    public void listarMetodoTransferencia(){
        container.add(transferencia);
    }
    
}
