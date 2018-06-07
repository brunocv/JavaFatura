import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;

public class MainMenu extends JFrame{
    private JLabel label;
    private JButton buttoncreate;
    private JButton buttonopen;
    private JButton exit;
    private ImageIcon image1;
    private JLabel description;
    
    
    public MainMenu (Users b) {
        
        setLayout(new FlowLayout());
        
        image1 = new ImageIcon(getClass().getResource("JavaFaturas.png"));
        Image image = image1.getImage();  
        Image newimg = image.getScaledInstance(600, 350,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        image1 = new ImageIcon(newimg); 
        description = new JLabel (image1);
        add(description);
        
        
        buttoncreate = new JButton("Register");
        buttoncreate.setSize(80, 30);
        buttoncreate.setLocation(50, 50);
        add(buttoncreate);
        
        buttonopen = new JButton("Login");
        buttonopen.setSize(80, 30);
        buttonopen.setLocation(50, 50);
        add(buttonopen);
        
        exit = new JButton("Exit");
        add(exit);
        
        event e = new event(b);
        exit.addActionListener(e);
        
        event2 e2 = new event2(b);
        buttoncreate.addActionListener(e2);
        
        event3 e3 = new event3(b);
        buttonopen.addActionListener(e3);
    }
    
    public class event implements ActionListener {
        private Users d;
        event (Users dados) {
            super();
            this.d = dados;
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
        event2 (Users dados) {
            super();
            this.d = dados;
        }
        public void actionPerformed (ActionEvent e2) {
            try {
                d.save(d);
            }
            catch(IOException e1) {
                e1.printStackTrace();
            }
            Register cc = new Register (d);
            //String alg [] = new String[1];
            //cc.main(alg);
            cc.registar(d);
        }
    }
    
    public class event3 implements ActionListener {
        private Users d;
        event3 (Users dados) {
            super();
            this.d = dados;
        }
        public void actionPerformed (ActionEvent e3) {
            try {
                d.save(d);
            }
            catch(IOException e1) {
                e1.printStackTrace();
            }
            Login ac = new Login (d);
            ac.entrar(d);
            //String alg [] = new String[1];
            //ac.main(alg);
        }
    }
    
    public static void run (Users b){
        MainMenu gui = new MainMenu(b);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setSize(610,480);
        gui.setVisible(true);
        gui.setTitle("JavaFaturas");
    }
    
    public static void main (String args[]){
        Users b = new Users();
        try {
            b=b.load();
        } catch (FileNotFoundException e) {
            try{
                b.save(b);
            }
            catch(IOException e1) {
            e.printStackTrace();
        }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        run(b);
    }
}
