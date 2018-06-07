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
import java.util.stream.Collectors;
import java.time.LocalDate;

public class EscolherIntervalo extends JFrame {
    private JLabel label;
    private JLabel label2;
    private JLabel label3;
    private JButton exit;
    private JButton cfm;
    private String aux;
    private JTextArea ta; // Text area
    private JScrollPane sbrText; // Scroll pane for text area
    
    private JLabel dayL1;
    private JLabel monthL1;
    private JLabel yearL1;
    private JTextField year1;
    private JTextField month1;
    private JTextField day1;
    private JLabel dayL2;
    private JLabel monthL2;
    private JLabel yearL2;
    private JTextField year2;
    private JTextField month2;
    private JTextField day2;
    
    
    
    public EscolherIntervalo (Users d, Info u) {
        
        setLayout(new FlowLayout());
        
        label = new JLabel ("<html><b>*** Faturas ***</b></html>");
        add(label);
        // Data inicial
        dayL1 = new JLabel ("Dia inicial:");
        add(dayL1);
        
        day1 = new JTextField(2);
        add(day1);
        
        monthL1 = new JLabel ("Mês inicial:");
        add(monthL1);
        
        month1 = new JTextField(2);
        add(month1);
        
        yearL1 = new JLabel ("Ano inicial:");
        add(yearL1);
        
        year1 = new JTextField(4);
        add(year1);
        
        // Data Final
        dayL2 = new JLabel ("Dia final:");
        add(dayL2);
        
        day2 = new JTextField(2);
        add(day2);
        
        monthL2 = new JLabel ("Mês final:");
        add(monthL2);
        
        month2 = new JTextField(2);
        add(month2);
        
        yearL2 = new JLabel ("Ano final:");
        add(yearL2);
        
        year2 = new JTextField(4);
        add(year2);
        
        exit = new JButton("Exit");
        add(exit);
        
        event e = new event(d);
        exit.addActionListener(e);
        
        cfm = new JButton("Confirmar");
        add(cfm);
        
        event2 e2 = new event2(d,u);
        cfm.addActionListener(e2);
        pack();
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
        private  Users d;
        private Info u;
        event2(Users dados, Info u){
            super();
            this.d=dados;
            this.u = u;
        }
        public void actionPerformed (ActionEvent e) {
            try{
                d.save(d);
            }
            catch(IOException e1) {
                e1.printStackTrace();
            }
            LocalDate in = LocalDate.of((Integer.parseInt(year1.getText())),(Integer.parseInt(month1.getText())),(Integer.parseInt(day1.getText())));
            LocalDate fim = LocalDate.of((Integer.parseInt(year2.getText())),(Integer.parseInt(month2.getText())),(Integer.parseInt(day2.getText())));
            
            VerFaturasIntervalo cc = new VerFaturasIntervalo(d,u,in,fim);
            cc.visualizar_intervalo(d,u,in,fim);
        }
    }
    
    public static void escolher_intervalo(Users b, Info u) {
        EscolherIntervalo gui = new EscolherIntervalo(b,u);
        gui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gui.setSize(500,200);
        gui.setVisible(true);
        gui.setTitle("JavaFaturas");
    }
}
