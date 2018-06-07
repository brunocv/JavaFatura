import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.PrintStream;
import java.io.IOException;
import java.io.File;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.StandardOpenOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.FileNotFoundException;

public class RegInd extends JFrame {
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JLabel label8;
    private JLabel label9;
    private JLabel message;
    private JLabel message2;
    private JButton exit;
    private JButton createcon;
    private JTextField morada;
    private JTextField nome;
    private JTextField password;
    private JTextField email;
    private JTextField NIF;
    private JTextField agregado;
    private JTextField NIFs;
    private JTextField coeficiente;
    private JTextField codes;
    
    
    public RegInd (Users b) {
        setLayout(new FlowLayout());
        
        label1 = new JLabel ("NIF:");
        add(label1);
        
        NIF = new JTextField(15);
        add(NIF);
        
        label2 = new JLabel ("E-mail:");
        add(label2);
        
        email = new JTextField(15);
        add(email);
        
        label3 = new JLabel ("Nome:");
        add(label3);
        
        nome = new JTextField(15);
        add(nome);
        
        label4 = new JLabel ("Morada:");
        add(label4);
        
        morada = new JTextField(15);
        add(morada);
        
        label5 = new JLabel ("Password:");
        add(label5);
        
        password = new JTextField(15);
        add(password);
        
        label6 = new JLabel ("Pessoas no agregado:");
        add(label6);
        
        agregado = new JTextField(15);
        add(agregado);
        
        label7 = new JLabel ("NIF's:");
        add(label7);
        
        NIFs = new JTextField(40);
        add(NIFs);
        
        label8 = new JLabel ("Coeficiente:");
        add(label8);
        
        coeficiente = new JTextField(15);
        add(coeficiente);
        
        label9 = new JLabel ("Códigos:");
        add(label9);
        
        codes = new JTextField(40);
        add(codes);
        //_______________________________________________________________________________________________________________________________________________________________________________________
        createcon = new JButton("Submeter");
        add(createcon);
        
        exit = new JButton("Exit");
        add(exit);
        
        message2 = new JLabel("Códigos: Saude = 010 ; Habitacao = 011 ; Restauracao 101 ; Educacao = 001 ; IVA = 110 ; Despesas gerais = 100 ; Outros = 000");
        add(message2);
        
        message = new JLabel("");
        add(message);
        
        //evento para criar o exit
        event e = new event(b);
        exit.addActionListener(e);
        
        //evento para criar conta
        event2 e2 = new event2(b);
        createcon.addActionListener(e2);
        
        }
    
    public class event implements ActionListener {
        private  Users d;
        event(Users dados){
            super();
            this.d=dados;
        }
        public void actionPerformed (ActionEvent e) {
            try{
                d.save(d);
            }
            catch(IOException e1) {
                e1.printStackTrace();
            }
            System.exit(0);
        }
    }
    
    public class event2 implements ActionListener {
        private Users d;
        event2(Users dados){
            super();
            this.d=dados;
        }
        public void actionPerformed (ActionEvent e2) {
            String NIFInput = NIF.getText();
            String emailInput = email.getText();
            String nomeInput = nome.getText();
            String moradaInput= morada.getText();
            String passwordInput = password.getText();
            String nifsagregados = NIFs.getText();
            String codesInput = codes.getText();
            int agreg;
            if (agregado.getText().equals("")) agreg = 0;
            else agreg = Integer.parseInt(agregado.getText());
            double coef;
            if (coeficiente.getText().equals("")) coef = 0;
            else coef = Double.parseDouble(coeficiente.getText());
            List<String>agregadoNIF1 = new ArrayList<>(); // NIFs.getText().toList();
            List<String>codigosAE1= new ArrayList<>(); //codes.getText().toList();
            List<Fatura>faturas= new ArrayList<>();
            Individual c = new Individual(NIFInput,emailInput,nomeInput,moradaInput,passwordInput,faturas,agreg,agregadoNIF1,coef,codigosAE1);
            c.NIFsAgregadoFromString(nifsagregados);
            c.codesFromString(codesInput);
            try {
                d.adiciona(c);
            }
            catch(UserExisteException ex){
                message.setText(ex.toString());
            }
            try{
                d.save(d);
             }
            catch(IOException e){
                e.printStackTrace();
            }
            System.exit(0);
        }
    }
    
    public static void registarInd(Users b) {
        RegInd gui = new RegInd(b);
        gui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gui.setSize(830,200);
        gui.setVisible(true);
        gui.setTitle("JavaFaturas");   
    }
}
