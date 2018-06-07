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

public class VerFaturasIntervalo extends JFrame {
    private JLabel label;
    private JLabel label2;
    private JLabel label3;
    private JButton exit;
    private String aux;
    private JTextArea ta; // Text area
    private JScrollPane sbrText; // Scroll pane for text area
    
    
    public VerFaturasIntervalo (Users d, Info u, LocalDate in, LocalDate fim) {
        
        setLayout(new FlowLayout());
        
        label = new JLabel ("<html><b>*** Faturas ***</b></html>");
        add(label);
        
        aux = "";
        if (d.existeUser(u.getNIF())) {
            Empresa em = (Empresa) u;
            label2 = new JLabel("<html><b>Total faturado no intervalo: </b>" + em.TotalFaturado(in,fim)+ "</html>");
            add(label2);
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, List<Fatura>> entrada : em.porCliente(in,fim).entrySet()) {
                sb.append("Cliente: " + entrada.getKey() + "\n\n");    
                for(Fatura f : entrada.getValue()) {
                    sb.append(f.specialToString());
                    sb.append("\n");
                }
            }
            aux = sb.toString();
        }
             
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
    
    public static void visualizar_intervalo(Users b, Info u, LocalDate in, LocalDate fim) {
        VerFaturasIntervalo gui = new VerFaturasIntervalo(b,u,in,fim);
        gui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gui.setSize(600,900);
        gui.setVisible(true);
        gui.setTitle("JavaFaturas");
    }
}

