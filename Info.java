import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.Collections;

public abstract class Info implements Serializable{
    
    private String NIF; 
    private String email;
    private String name;
    private String address;
    private String password;
    private List<Fatura> faturas;
    static final long serialVersionUID = 2L;

    //construtor inicializado

    public Info (String NIF,String email,String name,String address,String password, List<Fatura> faturas){

        this.NIF =NIF;
        this.email =email;
        this.name =name;
        this.address =address;
        this.password =password;
        setFaturas(faturas);
    }
    
    //construtor cópia

    public Info (Info newData){

        this.NIF =newData.getNIF();
        this.email =newData.getEmail();
        this.name =newData.getName();
        this.address =newData.getAddress();
        this.password =newData.getPassword();
        setFaturas(newData.getFaturas());
    }
    
    // Getters
    public String getNIF() {
        return this.NIF;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getAddress() {
        return this.address;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public List<Fatura> getFaturas() {
        List<Fatura> res = new ArrayList<>();
        for(Fatura e : this.faturas) {
            res.add(e.clone());
        }
        return res;
    }

     //Setters
    
    public void setNIF(String newNIF) {
        this.NIF=newNIF;
    }

    public void setEmail(String newEmail) {
        this.email=newEmail;
    }

    public void setName(String newName) {
        this.name=newName;
    }

    public void setAddress(String newAddress) {
        this.address=newAddress;
    }
    
    public void setPassword(String newPassword) {
        this.password=newPassword;
    }
    
    public void setFaturas(List<Fatura> f) {
        this.faturas = new ArrayList<>();
        for(Fatura e : f) {
            this.faturas.add(e.clone());
        }
    }
    
    //toString
    @Override
    public String toString() {
    
        StringBuilder sb = new StringBuilder();
        
        sb.append("\nNIF: ");
        sb.append(this.NIF + "\n");
        sb.append("Email: ");
        sb.append(this.email + "\n");
        sb.append("Name: ");
        sb.append(this.name + "\n");
        sb.append("Address: ");
        sb.append(this.address + "\n");
        sb.append("Password: ");
        sb.append(this.password + "\n");
        sb.append("Faturas: ");
        sb.append(this.faturas.toString() + "\n");

        return sb.toString();

    }
    
    public String faturasToString() {
        StringBuilder sb = new StringBuilder();
        if (this.faturas.equals(Collections.<Object>emptyList())) return "Não possui faturas em seu nome.";
        else
            for(Fatura f : this.faturas) 
                    sb.append(f.toString() + "\n");
        
        return sb.toString();
    }
    
    //Equals
    
    public boolean equals(Object o){
    if(o==this) return true;
    if((o==null) || (o.getClass() != this.getClass()))
        return false;
    
    Info c = (Info) o;
    return (this.NIF.equals(c.getNIF()) 
            && this.email.equals(c.getEmail()) 
            && this.name.equals(c.getName()) 
            && this.address.equals(c.getAddress()) 
            && this.password.equals(c.getPassword())
            && this.faturas.equals(c.getFaturas()));
    }
    
    //Clone
    public abstract Info clone();
    
    
    public void adicionaF(Fatura f) {
        this.faturas.add(f);
    }
    
    public double totalDeduzido() {
        double res = 0;
        for (Fatura f : this.faturas) {
            res += f.valorDeducao();
        }
        return res;
    }
    
    public double totalGasto() {
        double res = 0;
        for (Fatura f : this.faturas) {
            if (f.getNIF_cliente().equals(this.getNIF()))
                res += f.getValor();
        }
        return res;
    }
    
    public String factsSpecToString() {
        StringBuilder sb = new StringBuilder();
        for(Fatura f : this.faturas) {
            sb.append(f.specialToString());
            sb.append("\n");
        }
        return sb.toString();
    }
  
    public List<Fatura> getNotValid() {
        List<Fatura> res = new ArrayList<>();
        for (Fatura f : this.getFaturas())
            if (f.getValida() == false)
                res.add(f.clone());
        return res;
    }
    
    public List<Fatura> getValid() {
        List<Fatura> res = new ArrayList<>();
        for (Fatura f : this.getFaturas())
            if (f.getValida() == true)
                res.add(f.clone());
        return res;
    }
}

