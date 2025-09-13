package vista;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

public class metodosDePagos extends JFrame  {
    //numero de tarjeta, fecha de vencimiento, cvv //tarjeta de debito //4,2
    //Boton(Continue)
    //nombre titular,numero de tarjeta, fecha de vencimiento, cvv //tarjeta de credito
    public Container container;
    private JLabel titleDebito,titleFechaDebito,titleCvvDebito;
    private JTextField numTarjeDebito,fechaTarjeDebito,numCvvDebito;
    private JLabel titleNombreCredito,titleCredito,titleFechaCredito,titleCvvCredito,titleMontoCredi;
    public JLabel montoCredito;
    public JTextField nombreCredito,numTarjeCredito,fechaTarjeCredito,numCvvCredito;
    private JLabel titleNomTrans,titleNumCuenTra,titleNomBanTra,titleNumContaTra,titleMontoTrans,codigoConsig;
    private JTextField correoPaypal;
    private JLabel titleConsig,titleCorreoPaypal,codigoEfectivo;
    private JLabel titleEfectivo;
    private JTextField nomTrans,numCuentaTra,nomBanTra,numContaTra,montoTrans;
    public JPanel tarjetaCredito,tarjetaDebito,transferencia,consignacion,efectivo,paypal,bitcoin,applePay,googlePay;
    private JPanel codigoQr,codigoQrApple;
    private TitledBorder titulo,titulo2,titulo3,titulo4,titulo5,titulo6,titulo7,titulo8;
    private JButton confirCredi,confirDebito, confirTransfer;
    private FlowLayout miflow;
    
    public metodosDePagos(){
        super("Metodos de pago");
        //agrego el conteiner con flowLayaout
        container = getContentPane();
        miflow = new FlowLayout();
        container.setLayout(miflow);
        //Google Play
    }
    public void listarMetodoCredito(){
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
        confirCredi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validarCamposCredito();
            }
        });
        
        
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
        
        container.add(tarjetaCredito);
    }
    public void listarMetodoDebito(){
        //Agrego la tarjeta de debito
        tarjetaDebito = new JPanel(new GridLayout(0,2,2,2));
        titulo = new TitledBorder("Tarjeta de Debito");
        tarjetaDebito.setBorder(titulo);
        
        titleDebito = new JLabel("Numero de Debito");//titulo
        numTarjeDebito = new JTextField(10);//agregar o escribir 
        
        titleFechaDebito = new JLabel("Fecha Vencimiento");
        fechaTarjeDebito = new JTextField(10);
        
        titleCvvDebito = new JLabel("CVV");
        numCvvDebito = new JTextField(10);
        
        titleMontoCredi = new JLabel("Monto a pagar");
        montoCredito = new JLabel();
        montoCredito.setHorizontalAlignment(SwingConstants.RIGHT);//
        
        confirDebito = new JButton("Continue");
        
        tarjetaDebito.add(titleDebito);
        tarjetaDebito.add(numTarjeDebito);
        tarjetaDebito.add(titleFechaDebito);
        tarjetaDebito.add(fechaTarjeDebito);
        tarjetaDebito.add(titleCvvDebito);
        tarjetaDebito.add(numCvvDebito);
        tarjetaDebito.add(titleMontoCredi);
        tarjetaDebito.add(montoCredito);
        tarjetaDebito.add(confirDebito);
        container.add(tarjetaDebito);
    }
    public void listarMetodoTransferencia(){
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
        container.add(transferencia);
    }
    public void listarMetodoConsignacion(){
        //consignación
        consignacion = new JPanel(new GridLayout(0,2,2,2));
        titulo4 = new TitledBorder("Consignacion");
        consignacion.setBorder(titulo4);
        
        titleConsig = new JLabel("Codigo");
        codigoConsig = new JLabel();
        codigoConsig.setEnabled(false);
        codigoConsig.setHorizontalAlignment(SwingConstants.RIGHT);
        
        titleMontoCredi = new JLabel("Monto a pagar");
        montoCredito = new JLabel();
        montoCredito.setHorizontalAlignment(SwingConstants.RIGHT);
        
        consignacion.add(titleConsig);
        consignacion.add(codigoConsig);
        consignacion.add(titleMontoCredi);
        consignacion.add(montoCredito);
        container.add(consignacion);
    }
    public void listarMetodoEfectivo(){
        //Efectivo
        efectivo = new JPanel(new GridLayout(0,2,2,2));
        titulo5 = new TitledBorder("Efectivo");
        efectivo.setBorder(titulo5);
        
        titleEfectivo = new JLabel("Codigo Efecty");
        codigoEfectivo = new JLabel();
        codigoEfectivo.setEnabled(false);
        codigoEfectivo.setHorizontalAlignment(SwingConstants.RIGHT);
        
        titleMontoCredi = new JLabel("Monto a pagar");
        montoCredito = new JLabel();
        montoCredito.setHorizontalAlignment(SwingConstants.RIGHT);
        
        efectivo.add(titleEfectivo);
        efectivo.add(codigoEfectivo);
        efectivo.add(titleMontoCredi);
        efectivo.add(montoCredito);
        container.add(efectivo);
    }
    public void listarMetodoPaypal(){
        //paypal       -       correo
        paypal = new JPanel(new GridLayout(0,2,2,2));
        titulo6 = new TitledBorder("Paypal");
        paypal.setBorder(titulo6);
        
        titleCorreoPaypal = new JLabel("Correo de Paypal");
        correoPaypal = new JTextField(15);
        
        titleMontoCredi = new JLabel("Monto a pagar");
        montoCredito = new JLabel();
        montoCredito.setHorizontalAlignment(SwingConstants.RIGHT);
        
        paypal.add(titleCorreoPaypal);
        paypal.add(correoPaypal);
        paypal.add(titleMontoCredi);
        paypal.add(montoCredito);
        container.add(paypal);
    }
    public void listarMetodoBitcoin(){
        //bitcoin  -  codigo QR
        JLabel pagarParaComprar = new JLabel("Escanear");
        bitcoin = new JPanel(new FlowLayout());
        titulo7 = new TitledBorder("Bitcoin");
        codigoQr = new JPanel(new GridBagLayout());
        ImageIcon icono = new ImageIcon("src/imgs/codigoQr.png");
        JLabel etiquetaImagen = new JLabel(icono);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 5, 5, 5);
        pagarParaComprar.setFont(new Font("Times New Roman", Font.BOLD, 30));
        codigoQr.add(pagarParaComprar,gbc);
        codigoQr.add(etiquetaImagen);
        codigoQr.revalidate();
        codigoQr.repaint();
        bitcoin.setBorder(titulo7);
        bitcoin.add(codigoQr);
        container.add(bitcoin);
    }
    public void listarMetodoApplePay(){
        //Apple Play    - FACE ID
        
        applePay = new JPanel(new FlowLayout());
        titulo8 = new TitledBorder("ApplePay");
        codigoQrApple = new JPanel(new GridBagLayout());
        ImageIcon iconoFace = new ImageIcon("src/imgs/faceID.png");
        JLabel etiquetaFace = new JLabel(iconoFace);
        GridBagConstraints rgb = new GridBagConstraints();
        rgb.gridx = 0;
        rgb.gridy = 1;
        rgb.insets = new Insets(5, 5, 5, 5);
        codigoQrApple.add(etiquetaFace);
        codigoQrApple.revalidate();
        codigoQrApple.repaint();
        applePay.setBorder(titulo8);
        applePay.add(codigoQrApple);
        container.add(applePay);
    }
    public boolean validarCamposCredito() {
        /*
        */
        String nombre = nombreCredito.getText();
        String numeroCuenta = numTarjeCredito.getText();
        String fechaTarje = fechaTarjeCredito.getText();
        String cvv = numCvvCredito.getText();
        //fechaTarjeCredito
        
        if(!Validaciones.validarLetras(nombre)){
            JOptionPane.showMessageDialog(this,"Nombre titular invalido");
            return false;
        }
        if(!Validaciones.validarNumeroCuenta(numeroCuenta)){
            JOptionPane.showMessageDialog(this,"Numero de cuenta invalido");
            return false;
        }
        if(!Validaciones.validarFecha(fechaTarje)){
            JOptionPane.showMessageDialog(this,"Fecha invalida");
            return false;
        }
        if(!Validaciones.validarCVV(cvv)){
            JOptionPane.showMessageDialog(this,"Numero de CVV invalido");
            return false;
        }
        return true;
    }
    public boolean validarCamposDebito() {
        String numeroCuenta = numTarjeDebito.getText();
        String fecha = fechaTarjeDebito.getText();
        String cvv = numCvvDebito.getText();
        
        if(!Validaciones.validarNumeroCuenta(numeroCuenta)){
            JOptionPane.showMessageDialog(this,"Numero de cuenta invalido");
            return false;
        }
        if(!Validaciones.validarFecha(fecha)){
            JOptionPane.showMessageDialog(this,"Fecha invalida");
            return false;
        }
        if(!Validaciones.validarCVV(cvv)){
            JOptionPane.showMessageDialog(this,"Numero de CVV invalido");
            return false;
        }
        return true;
    }
    public boolean validarCamposTransferencia() {
        /*
        */
        String nombreDestinatario = nomTrans.getText();
        String numeroCuenta = numCuentaTra.getText();
        String nombreBanco = nomBanTra.getText();
        String contacto = numContaTra.getText();
        String monto = montoTrans.getText();
        
        if(!Validaciones.validarLetras(nombreDestinatario)){
            JOptionPane.showMessageDialog(this,"Nombre del destinatario invalido");
            return false;
        }
        if(!Validaciones.validarNumeroCuenta(numeroCuenta)){
            JOptionPane.showMessageDialog(this,"Numero de cuenta invalida");
            return false;
        }
        if(!Validaciones.validarLetras(nombreBanco)){
            JOptionPane.showMessageDialog(this,"Nombre del banco invalido");
            return false;
        }
        if(!Validaciones.validarTelefonos(contacto)){
            JOptionPane.showMessageDialog(this,"Numero de contacto invalido");
            return false;
        }
        if(!Validaciones.validarNumeros(monto)){
            JOptionPane.showMessageDialog(this,"Monto a transferir invalido");
            return false;
        }
        return true;
    }
    public boolean validarCamposPaypal() {
        String correoPay = correoPaypal.getText();
        
        if(!Validaciones.validarCorreo(correoPay)){
            JOptionPane.showMessageDialog(this,"Correo de Paypal invalido");
            return false;
        }
        return true;
    }
    public void mostrarComoModal(JFrame parent) {
        JDialog dialog = new JDialog(parent, "Métodos de Pago", true); // true = modal
        dialog.setContentPane(this.getContentPane());
        dialog.setSize(this.getSize());
        dialog.setLocationRelativeTo(parent);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
    }
}
