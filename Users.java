import java.util.Set;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.Iterator;
import java.io.Serializable;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Comparator;

public class Users implements Serializable{
    private Map<String, Info> users; // NIF, User
    static final long serialVersionUID = 1L;
    
    public Users() {
        this.users = new HashMap<>();
    }

    public Users(Map<String, Info> users) {
        this.users = users.values().stream().collect(Collectors.toMap((e) -> e.getNIF(),(e) -> e.clone()));
    }

    public Users(Users h) {
       setUsers(h.getUsers());
    }
    
    public Map<String,Info> getUsers() {
        return this.users.values().stream().collect(Collectors.toMap((e) -> e.getNIF(),(e) -> e.clone())); 
    }

    public void setUsers(Map<String, Info> users) {
        this.users = new HashMap<>();
        for(Map.Entry<String, Info> e : users.entrySet()) {
            this.users.put(e.getKey(), e.getValue());
        } 
    }
    
    public void apagaUser(Info u) {
        this.users.remove(u);
    }
    
    public void validaFatura(Fatura f) {
    
        String Nif_emitente = f.getNIF_emitente();
        String Nif_cliente = f.getNIF_cliente();
            
        List<Fatura> l = this.users.get(Nif_cliente).getValid();
        List<Fatura> t = this.users.get(Nif_cliente).getNotValid();
        int i = 0;
        for (Fatura j : t) {
            if (i == 0) l.add(f.clone());
            else l.add(j.clone());
            i++;
        }
            
        this.users.get(Nif_cliente).setFaturas(l);
        Info empresa = this.users.get(Nif_emitente);
        List<Fatura> paraEmp = new ArrayList<>();
        for (Fatura r : empresa.getFaturas()) {
            if (r.specialEquals(f)) 
                paraEmp.add(f);
            else paraEmp.add(r);
        }
        this.users.get(Nif_emitente).setFaturas(paraEmp);  
    }
    
    public boolean existeUser (String cod) {
        return this.users.containsKey(cod);
    } 

    public int quantos() {
        return this.users.size();
    }

    public Info getUser(String cod) {
        if (this.users.containsKey(cod))
            return this.users.get(cod).clone();
        else return new Individual(); // tem que ser EXCEPTION
    }

    public void adiciona(Info h) throws UserExisteException{
        if (this.users.containsKey(h.getNIF()))
             throw new UserExisteException("Contribuinte " + h.getNIF() + " já existe.");
        this.users.put(h.getNIF(), h.clone());
    }

    public List<Info> getUsers_List() {
        return this.users.values().stream().collect(Collectors.toList());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        List<Info> l = this.getUsers_List();
        
        for (Info h : l) {
            sb.append(h.toString() + "\n");
        }
        return sb.toString();
    }
    
    public Users clone() {
        return new Users(this);
    }
    
    // equals
    public boolean equals(Object o){
        if(o==this) return true;
        if((o==null) || (o.getClass() != this.getClass()))
            return false;
    
        Users c = (Users) o;
        return (this.getUsers().equals(c.getUsers()));
    }
    
    /**
     * Guarda num ficheiro um objecto da classe Users
     */
    public static void save(Users b)throws IOException{
        String fileName= "Dados.ser";
        FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(b);
        oos.close();
    }
    
    /**
     * Carrega de um ficheiro um objecto da classe Users 
     */
    public static Users load() throws IOException,ClassNotFoundException,FileNotFoundException{
        String fileName= "Dados.ser";
        FileInputStream fin = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fin);
        Users b= (Users) ois.readObject();
        ois.close();
        return b;
    }
    
    public void adicionaFact(Fatura f) {
        if (this.users.containsKey(f.getNIF_emitente())) this.users.get(f.getNIF_emitente()).adicionaF(f);
        if (this.users.containsKey(f.getNIF_cliente())) this.users.get(f.getNIF_cliente()).adicionaF(f); // nao sei qual o metodo ainda
    }
    
    public double deducaoSolo(String NIF) {
        double x = 0;
        Info aux = this.getUser(NIF);
        
        if (aux.getClass().equals(Individual.class)) {
            Individual ind = (Individual) aux;
            x += ind.valorDeduzido();
        }
        else x = 0;
        return x;
    }
    
    public double deducaoAgregado(String NIF) {
        double x = 0;
        Info aux = this.getUser(NIF);
        List<String> agregado = new ArrayList<>();
        if (aux.getClass().equals(Individual.class)) {
            Individual ind = (Individual) aux;
            x += ind.valorDeduzido();
            agregado = ind.getNIFAgregados();
            for (String s : agregado) {
                x += this.deducaoSolo(s); 
            }
        }
        else x = 0;
        return x;
    }
    
    public List<Info> top10() {
        List<Info> l = new ArrayList<>();
        Map<String, Info> m = this.getUsers(); 
        for (Map.Entry<String, Info> entrada : m.entrySet()) {
            if (entrada.getValue().getNIF().equals("admin") == false) {
                l.add(entrada.getValue());
                l.sort(new Comparator<Info>() {
                    public int compare(Info m1, Info m2) {
                        if(m1.totalGasto() == m2.totalGasto()){
                            return 0;
                        }
                        else if (m1.totalGasto() > m2.totalGasto())
                             return -1;
                        else return 1;
                    }
                });
            }
        }
        return l.subList(0,10);
    }
    
    public List<Empresa> topX(int X) {
        List<Empresa> l = new ArrayList<>();
        Map<String, Info> m = this.getUsers(); 
        for (Map.Entry<String, Info> entrada : m.entrySet()) {
            if (entrada.getValue().getClass().equals(Empresa.class)) {
                Empresa e = (Empresa) entrada.getValue(); 
                l.add(e);
                l.sort(new Comparator<Empresa>() {
                    public int compare(Empresa m1, Empresa m2) {
                        if(m1.totalFaturado() == m2.totalFaturado()){
                            return 0;
                        }
                        else if (m1.totalFaturado() > m2.totalFaturado())
                            return -1;
                        else return 1;
                    }
                });
            }
        }
        return l.subList(0,X);
    }
}
