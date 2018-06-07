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

public class EscolherX extends JFrame {
    private JLabel label;
    private JLabel label2;
    private JLabel label3;
    private JButton exit;
    private JButton cfm;
    private JLabel dimL;
    private JTextField dim;
    
    public EscolherX (Users d) {
        
        setLayout(new FlowLayout());
        
        label = new JLabel ("<html><b>Indique a dimensão:</b></html>");
        add(label);
        
        dimL = new JLabel ("Dimensão:");
        add(dimL);
        
        dim = new JTextField(3);
        add(dim);
        
        exit = new JButton("Exit");
        add(exit);
        
        event e = new event(d);
        exit.addActionListener(e);
        
        cfm = new JButton("Confirmar");
        add(cfm);
        
        event2 e2 = new event2(d);
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
        event2(Users dados){
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
            Integer x = Integer.parseInt(dim.getText());
            
            topX cc = new topX(d,x);
            cc.visualizar_topX(d,x);
        }
    }
    
    public static void escolher_Dim(Users b) {
        EscolherX gui = new EscolherX(b);
        gui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gui.setSize(500,200);
        gui.setVisible(true);
        gui.setTitle("JavaFaturas");
    }
}
