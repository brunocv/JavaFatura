import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;
import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.Comparator;

public class Empresa extends Info implements Serializable{
    
    private double fator;
    private List <String> atividades; // para os quais tem possibilidade de deduzir as depesas
    static final long serialVersionUID = 4L;
    //construtor vazio
    public Empresa() {
        super("n/a","n/a","n/a","n/a","n/a", new ArrayList<Fatura>());
        this.fator = 0.0;
        this.atividades = new ArrayList <String> ();
    }

    //construtor parametrizado
    public Empresa(String NIF,String email,String name,String address,String password, List<Fatura> faturas,double fator, List<String> atividades){
        super(NIF, email, name, address, password, faturas);
        this.fator = fator;
        setAtividades(atividades);
    }

    //construtor cópia
    public Empresa(Empresa newEmpresa){
        super(newEmpresa);
        this.fator = newEmpresa.getFator();
        setAtividades(newEmpresa.getAtividades());
    }

    // Getters
    public double getFator(){
        return this.fator;
    }
    
    public List<String> getAtividades(){
        List<String> res = new ArrayList<>();
        for(String e : this.atividades) {
            res.add(e);
        }
        return res;
    }

    //Setters
    
    public void setFaTor(double newFator){
       this.fator = newFator;
    }

    public void setAtividades(List<String> newatividades){
        this.atividades = new ArrayList<>();
        for(String e : newatividades) {
            this.atividades.add(e);
        }
    }

    //toString
    @Override
    public String toString(){
        Iterator <String> it = this.atividades.iterator();
        StringBuilder sb = new StringBuilder();
        
        sb.append(super.toString());
        sb.append("Fator de deducao da empresa: ");
        sb.append(this.fator + "\n");
        sb.append("Atividades relativas a empresa: ");
        while(it.hasNext()) 
            sb.append(it.next() + " ; ");
        sb.append("\n");
        
        return sb.toString();
    }

    // equals
      public boolean equals(Object o){
        if(o==this) return true;

        if((o==null) || (o.getClass() != this.getClass()))
            return false;
        
        Empresa c = (Empresa) o;

        return (super.equals(c) 
                && this.getFator() == c.getFator() 
                && this.getAtividades().equals(c.getAtividades()));
    }

   // clone
    public Empresa clone(){
        return new Empresa(this);    
    }
    
    public List<Fatura> faturasPorValorDecrescente() {
        TreeSet<Fatura> aux = new TreeSet<Fatura>((e1,e2) -> (int)(e2.getValor() - e1.getValor()));
        
        for(Fatura e: this.getFaturas())
            aux.add(e.clone());
        List l = aux.stream().collect(Collectors.toList());
        
        return l;        
   }
    
    public List<Fatura> faturasPorDia() {
        TreeSet<Fatura> aux = new TreeSet<Fatura>((e1,e2) -> (int)(e2.getData().compareTo(e1.getData())));
        
        for(Fatura e: this.getFaturas())
            aux.add(e.clone());
        List l = aux.stream().collect(Collectors.toList());
        return l;
    }
    
    public double TotalFaturado(LocalDate inicio, LocalDate fim) {
        List<Double> l = this.getFaturas()
                .stream().filter(e -> e.getData().isAfter(inicio) && e.getData().isBefore(fim) && e.getValida() == true || e.getData().equals(inicio) || e.getData().equals(fim))
                .map(Fatura::getValor)
                .collect(Collectors.toList()); 
        int res = 0;
        
        for (Double d : l) 
            res+=d;
            
        return res;
    }
    
    public void atividadesFromString(String s) {
        String[] result = s.split("\\s");
        for (int x=0; x<result.length; x++)
            this.atividades.add(result[x]);
    }
    
    public String specialAtvToString() {
        StringBuilder sb = new StringBuilder();
        for (String s : this.atividades) 
            sb.append(s + " ");
        return sb.toString();
    }
    
    public Map<String, List<Fatura>> porCliente(LocalDate inicio, LocalDate fim) {
        List<Fatura> l = this.getFaturas()
                .stream().filter(e -> e.getData().isAfter(inicio) && e.getData().isBefore(fim) || e.getData().equals(inicio) || e.getData().equals(fim))
                .map(Fatura::clone)
                .collect(Collectors.toList());        
                               
        Map<String, List<Fatura>> m = l.stream()
        .collect(Collectors.groupingBy(Fatura::getNIF_cliente));
    return m;
    }
    
    public Map<String, List<Fatura>> porClienteValor() {
        List<Fatura> l = this.getFaturas()
                .stream().map(Fatura::clone)
                .collect(Collectors.toList());        
                               
        Map<String, List<Fatura>> m = l.stream()
            .collect(Collectors.groupingBy(Fatura::getNIF_cliente));
            
        for (Map.Entry<String, List<Fatura>> entrada : m.entrySet()) {
            entrada.getValue().sort(new Comparator<Fatura>() {
                public int compare(Fatura m1, Fatura m2) {
                    if(m1.getValor() == m2.getValor()){
                        return 0;
                    }
                    else if (m1.getValor() > m2.getValor())
                        return 1;
                    else return -1;
                }
            });
        }

        return m;
    }
    
    public double totalFaturado() {
        double res = 0;
        for (Fatura f : this.getFaturas()) {
            if (f.getNIF_emitente().equals(this.getNIF()))
                res += f.getValor();
        }
        return res;
    }
    
    public double totalDeduzido() {
        double res = 0;
        for (Fatura f : this.getFaturas()) {
            if (f.getNIF_emitente().equals(this.getNIF()))
                res += (f.getValor() - f.valorDeducao()) * this.getFator();
        }
        return res;
    }
    
}
