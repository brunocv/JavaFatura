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

public class Decision extends JFrame {
    private JLabel label;
    private JButton createInd;
    private JButton createemp;
    private JButton validar;
    private JButton exit;
    private JButton top10;
    private JButton topX;
    private JTextField morada;
    private JTextField nome;
    private JTextField password;
    private JTextField email;
    private JTextField NIF;
    
    
    public Decision (Users d, Info u) {
        setLayout(new FlowLayout());
        
        label = new JLabel ("Escolha a opção:");
        add(label);
        if (u.getNIF().equals("admin")) {
            top10 = new JButton("Top 10 utilizadores que mais gastam");
            add(top10);
        
            event5 e5 = new event5(d);
            top10.addActionListener(e5);
            
            topX = new JButton("Top N empresas que mais faturam");
            add(topX);
        
            event6 e6 = new event6(d);
            topX.addActionListener(e6);
        
            exit = new JButton("Exit");
            add(exit);
        
            event e = new event(d);
            exit.addActionListener(e);
        
        }
        else {
            createInd = new JButton("Ver Faturas");
            add(createInd);
        
            if (u.getClass().equals(Empresa.class)) {
                createemp = new JButton("Registar Fatura");
                add(createemp);
            }
        
            if (u.getClass().equals(Individual.class)) {
                validar = new JButton("Validar Fatura");
                add(validar);
            }
            exit = new JButton("Exit");
            add(exit);
        
            event e = new event(d);
            exit.addActionListener(e);
        
            event2 e2 = new event2(d,u);
            createInd.addActionListener(e2);
            
            if (u.getClass().equals(Empresa.class)) {
                event3 e3 = new event3(d,u);
                createemp.addActionListener(e3);
            }
        
            if (u.getClass().equals(Individual.class)) {
                event4 e4 = new event4(d,u);
                validar.addActionListener(e4);
            }
    }
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
        private Info u;
        event2(Users dados, Info u){
            super();
            this.b=dados;
            this.u = u;
        }
        public void actionPerformed (ActionEvent e2) {
            try{
                b.save(b);
            }
            catch(IOException e1) {
                e1.printStackTrace();
            }
            VerFaturas cc = new VerFaturas(b,u);
            cc.visualizar(b,u);
            
        }
    }
    
    public class event3 implements ActionListener {
        private Users b;
        private Info u;
        event3(Users dados, Info u){
            super();
            this.b=dados;
            this.u = u;
        }
        public void actionPerformed (ActionEvent e3) {
            try{
                b.save(b);
            }
            catch(IOException e1) {
                e1.printStackTrace();
            }
            CriarFatura cc = new CriarFatura(b,u);
            cc.newFatura(b,u);
        }
    }
    
    public class event4 implements ActionListener {
        private Users b;
        private Info u;
        event4(Users dados, Info u){
            super();
            this.b=dados;
            this.u = u;
        }
        public void actionPerformed (ActionEvent e) {
            try{
                b.save(b);
            }
            catch(IOException e1) {
                e1.printStackTrace();
            }
            ValidarFatura cc = new ValidarFatura(b,u);
            cc.validar(b,u);
        }
    }
    
    public class event5 implements ActionListener {
        private Users b;
        event5(Users dados){
            super();
            this.b=dados;
        }
        public void actionPerformed (ActionEvent e) {
            try{
                b.save(b);
            }
            catch(IOException e1) {
                e1.printStackTrace();
            }
            top10 cc = new top10(b);
            cc.visualizar_top10(b);
        }
    }
    
    public class event6 implements ActionListener {
        private Users b;
        event6(Users dados){
            super();
            this.b=dados;
        }
        public void actionPerformed (ActionEvent e) {
            try{
                b.save(b);
            }
            catch(IOException e1) {
                e1.printStackTrace();
            }
            EscolherX cc = new EscolherX(b);
            cc.escolher_Dim(b);
        }
    }
    
    public static void decisao(Users b, Info u) {
        Decision gui = new Decision(b,u);
        gui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gui.setSize(300,150);
        gui.setVisible(true);
        gui.setTitle("JavaFaturas");
    }
}
