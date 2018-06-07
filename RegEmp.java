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

public class RegEmp extends JFrame {
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JLabel message;
    private JLabel despesal;
    private JButton exit;
    private JButton createcon;
    private JTextField morada;
    private JTextField nome;
    private JTextField password;
    private JTextField email;
    private JTextField NIF;
    private JTextField fator;
    private JTextField atividades;
    
    
    public RegEmp (Users b) {
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
        
        label6 = new JLabel ("Fator de dedução:");
        add(label6);
        
        fator = new JTextField(15);
        add(fator);
        
        label7 = new JLabel ("Atividades:");
        add(label7);
        
        atividades = new JTextField(40);
        add(atividades);
        //_______________________________________________________________________________________________________________________________________________________________________________________
        createcon = new JButton("Submeter");
        add(createcon);
        
        exit = new JButton("Exit");
        add(exit);
        
        message = new JLabel("");
        add(message);
        
        despesal = new JLabel ("Nota: as 'Atividades' podem ser: 'Saude', 'Restauracao', 'Educacao', 'Habitacao', 'Despesas Gerais', 'IVA' ou 'Outros'");
        add(despesal);
        
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
            double fat;
            if (fator.getText().equals("")) fat = 0;
            else fat = Double.parseDouble(fator.getText());
            String atv = atividades.getText();
            List<String>atividade = new ArrayList<>(); // atividades.getText().toList();
            List<Fatura>faturas= new ArrayList<>();
            Empresa c = new Empresa(NIFInput,emailInput,nomeInput,moradaInput,passwordInput,faturas,fat, atividade);
            c.atividadesFromString(atv);
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
    
    public static void registarEmp(Users b) {
        RegEmp gui = new RegEmp(b);
        gui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gui.setSize(830,200);
        gui.setVisible(true);
        gui.setTitle("JavaFaturas");   
    }
}
