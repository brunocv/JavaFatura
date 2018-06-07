import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintStream;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

// SO AS EMPRESAS REGISTAM AS FATURAS

public class CriarFatura extends JFrame {
    private JLabel dayl;
    private JLabel monthl;
    private JLabel yearl;
    private JLabel nifeml;
    private JLabel nomeml;
    private JLabel nifcll;
    private JLabel descriçãol;
    private JLabel natl;
    private JLabel despesal;
    private JButton createfat;
    private JButton exit;
    private JTextField year;
    private JTextField month;
    private JTextField day;
    private JTextField nifem;
    private JTextField nomem;
    private JTextField nifcl;
    private JTextField descrição;
    private JTextField nat;
    private JTextField despesa;
    private boolean val;
    
    
    public CriarFatura (Users b, Info u) {
        setLayout(new FlowLayout());
        
        val = false;
        
        dayl = new JLabel ("Dia:");
        add(dayl);
        
        day = new JTextField(2);
        add(day);
        
        monthl = new JLabel ("Mês:");
        add(monthl);
        
        month = new JTextField(2);
        add(month);
        
        yearl = new JLabel ("Ano:");
        add(yearl);
        
        year = new JTextField(4);
        add(year);
        
        Empresa f = (Empresa) u;
        
        nifeml = new JLabel ("NIF do emissor:");
        add(nifeml);
        
        //nifem = new JTextField(12);
        nifem = new JTextField(f.getNIF());
        nifem.setEditable(false);
        add(nifem);
        
        nomeml = new JLabel ("Nome do emissor:");
        add(nomeml);
        
        //nomem = new JTextField(20);
        nomem = new JTextField(f.getName());
        nomem.setEditable(false);
        add(nomem);
        
        nifcll = new JLabel ("NIF do cliente:");
        add(nifcll);
        
        nifcl = new JTextField(10);
        add(nifcl);
        
        descriçãol = new JLabel ("Descrição (85 caracteres):");
        add(descriçãol);
        
        descrição = new JTextField(85);
        add(descrição);
        
        natl = new JLabel ("Natureza da Fatura:");
        add(natl);
        
        if (f.getAtividades().size() == 1) {
            nat = new JTextField(f.specialAtvToString());
            nat.setEditable(false);
            val = true;
        }
        else /*nat = new JTextField(28);*/ {
            nat = new JTextField(10);
            nat.setEditable(false);
        }
        add(nat);
        
        despesal = new JLabel ("Valor da Despesa:");
        add(despesal);
        
        despesa = new JTextField(15);
        add(despesa);
        
        despesal = new JLabel ("Nota: a 'Natureza da Despesa' pode ser: 'Saude', 'Restauracao', 'Educacao', 'Habitacao', 'Despesas Gerais', 'IVA' ou 'Outros'");
        add(despesal);
        
        createfat = new JButton("Adicionar Fatura");
        add(createfat);
        
        exit = new JButton("Exit");
        add(exit);
        
        event e = new event(b);
        exit.addActionListener(e);
        
        event2 e2 = new event2(b);
        createfat.addActionListener(e2);
        
    }
    
    public class event implements ActionListener {
        private  Users d;
        private Info u;
        event(Users dados){
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
            System.exit(0);
        }
    }
    
    
    public class event2 implements ActionListener {
        private Users d;
        private Info u;
        event2(Users dados){
            super();
            this.d=dados;
            this.u = u;
        }
        public void actionPerformed (ActionEvent e) {
            String nifem1=nifem.getText();
            String nomem1=nomem.getText();
            LocalDate dataem1 = LocalDate.of((Integer.parseInt(year.getText())),(Integer.parseInt(month.getText())),(Integer.parseInt(day.getText())));
            String nifcl1=nifcl.getText();
            String descricao1=descrição.getText();
            String nat1=nat.getText();
            double despesa1=Double.parseDouble(despesa.getText());
            Fatura novo = new Fatura(nifem1,nomem1,dataem1,dataem1,nifcl1,descricao1,toDespesa(nat1),despesa1,val);
            d.adicionaFact(novo);
            try{
                d.save(d);
             }
            catch(IOException e1){
                e1.printStackTrace();
            }
            System.exit(0);
        }
    }
    
    public Despesa toDespesa(String s) {
        // str1.toLowerCase().contains(str2.toLowerCase())
        /*if (s.equals("Saude")) return new Saude();
        else if (s.equals("Restauracao")) return new Restauracao();
        else if (s.equals("Habitacao")) return new Habitacao();
        else if (s.equals("Educacao")) return new Educacao();
        else if (s.equals("Despesas Gerais")) return new DespesasGerais();
        else if (s.equals("IVA")) return new IVA();*/
        if (s.toLowerCase().contains("saude")) return new Saude();
        else if (s.toLowerCase().contains("restauracao")) return new Restauracao();
        else if (s.toLowerCase().contains("habitacao")) return new Habitacao();
        else if (s.toLowerCase().contains("educacao")) return new Educacao();
        else if (s.toLowerCase().contains("gerais")) return new DespesasGerais();
        else return new Outros();
        
    }
    
    public static void newFatura(Users b, Info u) {
        CriarFatura gui = new CriarFatura(b,u);
        gui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gui.setSize(1127,200);
        gui.setVisible(true);
        gui.setTitle("JavaFaturas");  
    }
}
