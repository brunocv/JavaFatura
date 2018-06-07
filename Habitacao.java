import java.io.Serializable;

public class Habitacao extends Despesa implements Serializable{
    private static final double deducao=0.15;
    private static final double maximo=502;
    private static final String code="011";
    static final long serialVersionUID = 8L;
   
    public Habitacao (double d, double m, String c){    
        super (d,m,c);
    }
    public Habitacao (){
        super(deducao,maximo, code);
    }

    public Habitacao (Habitacao c){
        super(c);
    }

    public Despesa clone(){
        return new Habitacao(this);
    }
}