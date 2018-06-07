import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.io.Serializable;

public class Individual extends Info implements Serializable{
    
    private int agregado;
    private List <String> NIFagregados; // agregados * 9
    private double coeficiente; // 1 single 2 casado
    private List <String> codes; // para os quais tem possibilidade de deduzir as depesas
    static final long serialVersionUID = 3L;
    //construtor vazio
    public Individual () {
        super("n/a","n/a","n/a","n/a","n/a", new ArrayList <Fatura>());
        this.agregado =0;
        this.coeficiente = 0.0;
        this.NIFagregados = new ArrayList <String>();
        this.codes=new ArrayList <String>();
    }
    //construtor inicializado
    
    public Individual (String NIF,String email,String name,String address,String password, List<Fatura> faturas,int agregado,List<String> NIFagregados,double coeficiente,List <String> codes) {
        super(NIF, email, name, address, password, faturas);
        this.agregado =agregado;
        this.coeficiente = coeficiente;
        setNIFagregados(NIFagregados);
        setCodes(codes);
    }

    //construtor cópia
    
    public Individual (Individual newIndividual){
        super(newIndividual);
        this.agregado = newIndividual.getAgregado();
        setNIFagregados(newIndividual.getNIFAgregados());
        this.coeficiente = newIndividual.getCoeficiente();
        setCodes(newIndividual.getCodes());

    }

    // Getters
    public int getAgregado() {
        return this.agregado;
    }
    
    public List<String> getNIFAgregados() {
        List<String> res = new ArrayList<>();
        for(String e : this.NIFagregados) {
            res.add(e);
        }
        return res;
    }
    
    public List<String> getCodes() {
        List<String> res = new ArrayList<>();
        for(String e : this.codes) {
            res.add(e);
        }
        return res;
    }
    
    public double getCoeficiente() {
        return this.coeficiente;
    }
    //Setters

    public void setAgregado(int newAgregado) {
       this.agregado=newAgregado;
    }

    public void setNIFagregados(List<String> newNIFagregados) {
       this.NIFagregados = new ArrayList<>();
       for(String e : newNIFagregados) {
           this.NIFagregados.add(e);
       }
    }

    public void setCodes(List<String> newCodes) {
       this.codes = new ArrayList<>();
        for(String e : newCodes) {
            this.codes.add(e);
        }
    }
    
    public void setCoeficiente(double coeficiente) {
        this.coeficiente = coeficiente;
    }


    // toString
    @Override
    public String toString() {
        
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("Pessoas no agregado: ");
        sb.append(this.agregado + "\n");
        sb.append("NIF das pessoas no agregado: \n");
        for(String s : this.NIFagregados) 
            sb.append(s + "\n");
        sb.append("Coeficiente de dedução: ");
        sb.append(this.coeficiente + "\n");
        sb.append("Códigos possíveis de dedução: ");
        for(String t : this.codes) 
            sb.append(t + " ; ");
        sb.append("\n");
        
        return sb.toString();
    }


    // equals
    public boolean equals(Object o){
    if(o==this) return true;
    if((o==null) || (o.getClass() != this.getClass()))
        return false;
    
    Individual c = (Individual) o;
    return (super.equals(c) 
           && this.getAgregado() == c.getAgregado() 
           && c.getNIFAgregados().equals(this.getNIFAgregados())
           && this.getCoeficiente() == c.getCoeficiente()
           && c.getCodes().equals(this.getCodes()));
    }
    // clone
    
    public Individual clone(){
        return new Individual(this);    
    }
    
    public void NIFsAgregadoFromString(String s) {
        String[] result = s.split("\\s");
        for (int x=0; x<result.length; x++)
            this.NIFagregados.add(result[x]);
    }
    
    public void codesFromString(String s) {
        String[] result = s.split("\\s");
        for (int x=0; x<result.length; x++)
            this.codes.add(result[x]);
    }

    public double valorDeduzido() {
        double x = 0;
        for (Fatura f : this.getFaturas()) {
            if (f.getValida() == true && this.getCodes().contains(f.getCodigoDespesa())) 
                x += f.valorDeducao();
        }
        return x;
    }
}
 
