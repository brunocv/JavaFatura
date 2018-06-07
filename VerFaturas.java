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

public class VerFaturas extends JFrame {
    private JLabel label;
    private JLabel label2;
    private JLabel label3;
    private JButton exit;
    private String aux;
    
    private JButton sortDate;
    private JButton sortVal;
    private JButton mapa;
    private JButton intervalo;

    private JTextArea ta; // Text area
    private JScrollPane sbrText; // Scroll pane for text area
    
    
    public VerFaturas (Users d, Info u) {
        
        setLayout(new FlowLayout());
        
        label = new JLabel ("<html><b>*** Faturas ***</b></html>");
        add(label);
        
        if (u.getClass().equals(Individual.class)) {
            Individual ind = (Individual) u;
            label2 = new JLabel ("<html><b>Montante dedução acumulado pelo utilizador:</b> " + d.deducaoSolo(ind.getNIF()) + "</html>");
            add(label2);
            label3 = new JLabel ("<html><b>Montante dedução acumulado pelo utilizador e agregado:</b> " + d.deducaoAgregado(ind.getNIF()) + "</html>");
            add(label3);
        }
        
        aux = "";
        if (d.existeUser(u.getNIF()))
            aux = u.factsSpecToString();
             
        ta = new JTextArea(aux, 75, 30);
        ta.setEditable(false);
        
        //sbrText = new JScrollPane(ta,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        sbrText = new JScrollPane(ta);
        sbrText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(sbrText);
        
        exit = new JButton("Exit");
        add(exit);
        
        event e = new event(d);
        exit.addActionListener(e);
        if (u.getClass().equals(Empresa.class)) {
            
            sortDate = new JButton ("Ordenar por Data");
            add(sortDate);
            event2 e2 = new event2(d,u);
            sortDate.addActionListener(e2);
            
            sortVal = new JButton ("Ordenar por Valor");
            add(sortVal);
            event3 e3 = new event3(d,u);
            sortVal.addActionListener(e3);
            
            mapa = new JButton ("Ordenar por Cliente");
            add(mapa);
            event4 e4 = new event4(d,u);
            mapa.addActionListener(e4);
            
            intervalo = new JButton ("Inserir intervalo");
            add(intervalo);
            event5 e5 = new event5(d,u);
            intervalo.addActionListener(e5);
        }
        
        
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
            VerFaturasData cc = new VerFaturasData(d,u);
            cc.visualizar_data(d,u);
        }
    }
    
    public class event3 implements ActionListener {
        private  Users d;
        private Info u;
        event3(Users dados, Info u){
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
            VerFaturasValor cc = new VerFaturasValor(d,u);
            cc.visualizar_valor(d,u);
        }
    }
    
    public class event4 implements ActionListener {
        private  Users d;
        private Info u;
        event4(Users dados, Info u){
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
            VerFaturasCliente cc = new VerFaturasCliente(d,u);
            cc.visualizar_cliente(d,u);
        }
    }
    
    public class event5 implements ActionListener {
        private  Users d;
        private Info u;
        event5(Users dados, Info u){
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
            EscolherIntervalo cc = new EscolherIntervalo(d,u);
            cc.escolher_intervalo(d,u);
        }
    }
    
    public static void visualizar(Users b, Info u) {
        VerFaturas gui = new VerFaturas(b,u);
        gui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gui.setSize(1400,900);
        gui.setVisible(true);
        gui.setTitle("JavaFaturas");
    }
}
