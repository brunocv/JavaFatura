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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ValidarFatura extends JFrame {
    private JLabel notificacao;
    
    private JLabel dayl;
    private JLabel monthl;
    private JLabel yearl;
    private JLabel hourl;
    private JLabel minutel;
    private JLabel nifeml;
    private JLabel nomeml;
    private JLabel nifcll;
    private JLabel descricaol;
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
    private JTextField descricao;
    private JTextField nat;
    private JTextField despesa;
    private boolean val;
    
    
    public ValidarFatura (Users b, Info u) {
        setLayout(new FlowLayout());
        
        if (u.getNotValid().size() == 0) {
            notificacao = new JLabel("Todas as faturas estão válidas.");
            add(notificacao);
        }
        else { 
            
            Fatura f = u.getNotValid().get(0);
            
            dayl = new JLabel ("Dia:");
            add(dayl);
        
            day = new JTextField("" + f.getData().getDayOfMonth());
            day.setEditable(false);
            add(day);
        
            monthl = new JLabel ("Mês:");
            add(monthl);
        
            month = new JTextField("" + f.getData().getMonthValue());
            month.setEditable(false);
            add(month);
        
            yearl = new JLabel ("Ano:");
            add(yearl);
        
            year = new JTextField("" + f.getData().getYear());
            year.setEditable(false);
            add(year);
        
            nifeml = new JLabel ("NIF do emissor:");
            add(nifeml);
        
            nifem = new JTextField(f.getNIF_emitente());
            nifem.setEditable(false);
            add(nifem);
        
            nomeml = new JLabel ("Nome do emissor:");
            add(nomeml);
        
            nomem = new JTextField(f.getNome_emitente());
            nomem.setEditable(false);
            add(nomem);
        
            nifcll = new JLabel ("NIF do cliente:");
            add(nifcll);
        
            nifcl = new JTextField(f.getNIF_cliente());
            nifcl.setEditable(false);
            add(nifcl);
        
            descricaol = new JLabel ("Descrição (85 caracteres):");
            add(descricaol);
        
            descricao = new JTextField(f.getDescricao());
            descricao.setEditable(false);
            add(descricao);
        
            natl = new JLabel ("Natureza da Fatura:");
            add(natl);
        
            Info uti = b.getUser(f.getNIF_emitente());
            Empresa em = (Empresa) uti;
            
            nat = new JTextField(em.specialAtvToString());
            add(nat);
        
            despesal = new JLabel ("Valor da Despesa:");
            add(despesal);
        
            despesa = new JTextField("" + f.getValor());
            despesa.setEditable(false);
            add(despesa);
        
            despesal = new JLabel ("Nota: escolha uma das atividade presente no campo a preencher.");
            add(despesal);
        
            createfat = new JButton("Validar Fatura");
            add(createfat);
            
            event2 e2 = new event2(b,u,f);
            createfat.addActionListener(e2);        
        }
        exit = new JButton("Exit");
        add(exit);
        
        event e = new event(b);
        exit.addActionListener(e);
        
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
        private Fatura f;
        event2(Users dados, Info u, Fatura f){
            super();
            this.d=dados;
            this.u = u;
            this.f = f;
        }
        public void actionPerformed (ActionEvent e) {
            String nat1=nat.getText(); 
            f.setNaturezaDespesa(toDespesa(nat1));
            f.setValida(true);
            f.setAlteracao(LocalDate.now());
            d.validaFatura(f);
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
        if (s.toLowerCase().contains("saude")) return new Saude();
        else if (s.toLowerCase().contains("restauracao")) return new Restauracao();
        else if (s.toLowerCase().contains("habitacao")) return new Habitacao();
        else if (s.toLowerCase().contains("educacao")) return new Educacao();
        else if (s.toLowerCase().contains("gerais")) return new DespesasGerais();
        else return new Outros();
        
    }
    
    public static void validar(Users b, Info u) {
        ValidarFatura gui = new ValidarFatura(b,u);
        gui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gui.setSize(1127,200);
        gui.setVisible(true);
        gui.setTitle("JavaFaturas");  
    }
}