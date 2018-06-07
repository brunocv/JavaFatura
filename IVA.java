import java.io.Serializable;

public class IVA extends Despesa implements Serializable{
    private static final double deducao=0.23;
    private static final double maximo=250;
    private static final String code="110";
    static final long serialVersionUID = 12L;
   
    public IVA (double d, double m, String c){    
        super (d,m,c);
    }
    public IVA (){
        super(deducao,maximo, code);
    }

    public IVA (IVA c){
        super(c);
    }

    public Despesa clone(){
        return new IVA(this);
    }
}

