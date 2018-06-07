import java.io.Serializable;

public class Educacao extends Despesa implements Serializable{
    private static final double deducao=0.30;
    private static final double maximo=800;
    private static final String code="001";
    static final long serialVersionUID = 9L;
   
    public Educacao (double d, double m, String c){    
        super (d,m,c);
    }
    public Educacao (){
        super(deducao,maximo, code);
    }

    public Educacao (Educacao c){
        super(c);
    }

    public Despesa clone(){
        return new Educacao(this);
    }
}

