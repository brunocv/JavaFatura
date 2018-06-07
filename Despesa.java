import java.io.Serializable;
//classe abstrata não dá para criar instâncias

public abstract class Despesa implements Serializable{
    private double deducao;
    private double maximo;
    private String code;
    static final long serialVersionUID = 6L;
    
    public Despesa(double deducao,double maximo, String code){
        this.deducao=deducao;
        this.maximo=maximo;
        this.code = code;
    }

    public Despesa(Despesa d){
        this.deducao=d.getDeducao();
        this.maximo=d.getMaximo();
        this.code = d.getCode();
    }
    
    public double getDeducao(){
        return this.deducao;
    }

    public void setDeducao(double d){
        this.deducao=d;
    }

    public double getMaximo(){
        return this.maximo;
    }

    public void setMaximo(double m){
        this.maximo=m;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }
        
    public String toString(){
        StringBuilder sb =new StringBuilder();

        sb.append("Deducao: ");
        sb.append(this.deducao + "\n");
        sb.append("Maximo: ");
        sb.append(this.maximo + "\n");
        sb.append("Código: ");
        sb.append(this.code + "\n");

        return sb.toString();
    }

    public boolean equals(Object o){
        if(this==o)
            return true;
        if(o==null || o.getClass()!=this.getClass())
            return false;

        Despesa d=(Despesa) o;
        return(this.deducao== d.getDeducao() 
              && this.maximo == d.getMaximo()
              && this.code.equals(d.getCode()));
    }
    
    //as outras classes tem de ter o clone definido e o clone que é chamado é dessa classe
    public abstract Despesa clone();
}