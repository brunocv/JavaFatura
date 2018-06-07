import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Login extends JFrame{
    private JLabel label;
    private JLabel label2;
    private JTextField NIF;
    private JTextField PIN;
    private JLabel message;
    private JButton buttonconfirm;
    private JButton exit;
    
    public Login (Users b) {
        setLayout(new FlowLayout());
        
        label = new JLabel ("NIF:");
        add(label);
        
        NIF = new JTextField(15);
        add(NIF);
        
        label2 = new JLabel ("Password:");
        add(label2);
        
        PIN = new JTextField(15);
        add(PIN);
        
        buttonconfirm = new JButton("Confirmar");
        add(buttonconfirm);
        
        exit = new JButton("Exit");
        add(exit);
        
        message = new JLabel("");
        add(message);
        
        //evento para criar o exit
        event e = new event(b);
        exit.addActionListener(e);
        
        //evento para criar conta
        event2 e2 = new event2(b);
        buttonconfirm.addActionListener(e2);
        
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
        public void actionPerformed (ActionEvent e2) {
            String NIFinput=NIF.getText();
            String PINinput = PIN.getText();
            Info u = d.getUser(NIFinput);
            if (u == null) message.setText("Utilizador inexistente");
            String NIFex = u.getNIF();
            String PINex=u.getPassword();
            if((NIFinput.equals(NIFex))&&(PINinput.equals(PINex))) {
                Decision cc = new Decision(d,u);
                cc.decisao(d,u);
            }
            else 
                message.setText("Acesso Negado"); 
        }
    }
    
    public static void entrar(Users b) {
        Login gui = new Login(b);
        gui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gui.setSize(215,200);
        gui.setVisible(true);
        gui.setTitle("JavaFaturas");
    }
}

