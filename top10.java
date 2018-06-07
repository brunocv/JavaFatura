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

public class top10 extends JFrame {
    private JLabel label;
    private JLabel label2;
    private JLabel label3;
    private JButton exit;
    private String aux;
    private JTextArea ta; // Text area
    private JScrollPane sbrText; // Scroll pane for text area
    
    
    public top10 (Users d) {
        
        setLayout(new FlowLayout());
        
        label = new JLabel ("<html><b>Top 10 contribuintes que mais gastam</b></html>");
        add(label);
        
        aux = "";
        StringBuilder sb = new StringBuilder();
        for(Info u : d.top10()) {
            sb.append("NIF: " + u.getNIF() + " Valor gasto: " + u.totalGasto());
            sb.append("\n");
        }
        aux = sb.toString();
        
             
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
    
    public static void visualizar_top10(Users b) {
        top10 gui = new top10(b);
        gui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gui.setSize(600,900);
        gui.setVisible(true);
        gui.setTitle("JavaFaturas");
    }
}
