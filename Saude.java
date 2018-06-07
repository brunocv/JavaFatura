import java.io.Serializable;

public class Saude extends Despesa implements Serializable{
    private static final double deducao=0.15;
    private static final double maximo=1000;
    private static final String code="010";
    static final long serialVersionUID = 11L;
   
    public Saude (double d, double m, String c){    
        super (d,m,c);
    }
    public Saude (){
        super(deducao,maximo, code);
    }

    public Saude (Saude c){
        super(c);
    }

    public Despesa clone(){
        return new Saude(this);
    }
}

