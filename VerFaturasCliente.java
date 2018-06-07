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

public class VerFaturasCliente extends JFrame {
    private JLabel label;
    private JLabel label2;
    private JLabel label3;
    private JButton exit;
    private String aux;
    private JTextArea ta; // Text area
    private JScrollPane sbrText; // Scroll pane for text area
    
    
    public VerFaturasCliente (Users d, Info u) {
        
        setLayout(new FlowLayout());
        
        label = new JLabel ("<html><b>*** Faturas ***</b></html>");
        add(label);
        
        aux = "";
        if (d.existeUser(u.getNIF())) {
            Empresa em = (Empresa) u;
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, List<Fatura>> entrada : em.porClienteValor().entrySet()) {
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
    
    public static void visualizar_cliente(Users b, Info u) {
        VerFaturasCliente gui = new VerFaturasCliente(b,u);
        gui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gui.setSize(600,900);
        gui.setVisible(true);
        gui.setTitle("JavaFaturas");
    }
}
