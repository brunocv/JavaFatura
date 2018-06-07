import java.io.Serializable;
// agua, luz, comida, roupa, supermercado, combustivel
public class DespesasGerais extends Despesa implements Serializable{
    private static final double deducao=0.35; 
    private static final double maximo=250;
    private static final String code="100";
    static final long serialVersionUID = 10L;
   
    public DespesasGerais (double d, double m, String c){    
        super (d,m,c);
    }
    public DespesasGerais (){
        super(deducao,maximo, code);
    }

    public DespesasGerais (DespesasGerais c){
        super(c);
    }

    public Despesa clone(){
        return new DespesasGerais(this);
    }
}
