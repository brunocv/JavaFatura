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

public class Register extends JFrame {
    private JLabel label;
    private JButton createInd;
    private JButton createemp;
    private JButton exit;
    private JTextField morada;
    private JTextField nome;
    private JTextField password;
    private JTextField email;
    private JTextField NIF;
    
    
    public Register (Users d) {
        setLayout(new FlowLayout());
        
        label = new JLabel ("Escolha o tipo de contribuinte:");
        add(label);
        
        createInd = new JButton("Individual");
        add(createInd);
        
        createemp = new JButton("Empresa");
        add(createemp);
        
        exit = new JButton("Exit");
        add(exit);
        
        event e = new event(d);
        exit.addActionListener(e);
        
        event2 e2 = new event2(d);
        createInd.addActionListener(e2);
        
        event3 e3 = new event3(d);
        createemp.addActionListener(e3);
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
        private Users b;
        event2(Users dados){
            super();
            this.b=dados;
        }
        public void actionPerformed (ActionEvent e2) {
            try{
                b.save(b);
            }
            catch(IOException e1) {
                e1.printStackTrace();
            }
            RegInd cc = new RegInd (b);
            //String alg [] = new String[1];
            //cc.main(alg);
            cc.registarInd(b);
        }
    }
    
    public class event3 implements ActionListener {
        private Users b;
        event3(Users dados){
            super();
            this.b=dados;
        }
        public void actionPerformed (ActionEvent e3) {
            try{
                b.save(b);
            }
            catch(IOException e1) {
                e1.printStackTrace();
            }
            RegEmp cc = new RegEmp(b);
            //String alg [] = new String[1];
            //cc.main(alg);
            cc.registarEmp(b);
        }
    }
    
    public static void registar(Users b) {
        Register gui = new Register(b);
        gui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gui.setSize(300,100);
        gui.setVisible(true);
        gui.setTitle("JavaFaturas");
    }
    /*
    public static void main (String args[]){
        Users b = new Users();
        try {
            b=b.carrega();
        } catch (FileNotFoundException e) {
            try{
                b.guarda(b);
            }
            catch(IOException e1) {
            e.printStackTrace();
        }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        registar(b);
    }*/
}
