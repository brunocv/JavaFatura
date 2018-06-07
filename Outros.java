import java.io.Serializable;

public class Outros extends Despesa implements Serializable{
    private static final double deducao=0;
    private static final double maximo=999999999;
    private static final String code="000";
    static final long serialVersionUID = 13L;
   
    public Outros (double d, double m, String c){    
        super (d,m,c);
    }
    public Outros (){
        super(deducao,maximo, code);
    }

    public Outros (Outros c){
        super(c);
    }

    public Despesa clone(){
        return new Outros(this);
    }
}
