import java.io.Serializable;

public class Restauracao extends Despesa implements Serializable {
    private static final double deducao=0.115; //0.15*0.23;
    private static final double maximo=250;
    private static final String code="101";
    static final long serialVersionUID = 7L;
   
    public Restauracao (double d, double m, String c){    
        super (d,m,c);
    }
    public Restauracao (){
        super(deducao,maximo, code);
    }

    public Restauracao (Restauracao c){
        super(c);
    }

    public Despesa clone(){
        return new Restauracao(this);
    }
}

