import java.time.LocalDate;
import java.io.Serializable;

public class Fatura implements Serializable {
    private String NIF_emitente; // NIF tem 9 numeros
    private String nome_emitente;
    private LocalDate data;
    private LocalDate alteracao;
    private String NIF_cliente;
    private String descricao;
    private Despesa naturezaDespesa;
    private double valor;
    private boolean valida;
    static final long serialVersionUID = 5L;
    
    //construtor vazio

    public Fatura () {
        this.NIF_emitente ="";
        this.nome_emitente ="";
        this.data=LocalDate.of(0,1,1);
        this.alteracao=LocalDate.of(0,1,1);
        this.NIF_cliente ="";
        this.descricao ="";
        this.naturezaDespesa = new Outros();
        this.valida = false;
        this.valor =0;
    }
    
    //construtor inicializado

    public Fatura (String NIFempresa,String nomeEmpresa, LocalDate data,LocalDate d2,String NIFpessoa,String descricao,Despesa naturezaDespesa,double valor, boolean valida){
        this.NIF_emitente =NIFempresa;
        this.nome_emitente =nomeEmpresa;
        this.data =data;
        this.alteracao = d2;
        this.NIF_cliente =NIFpessoa;
        this.descricao =descricao;
        this.naturezaDespesa =naturezaDespesa;
        this.valor =valor;
        this.valida = valida;
    }
    
    //construtor cópia

    public Fatura (Fatura novaFatura){
        this.NIF_emitente =novaFatura.getNIF_emitente();
        this.nome_emitente=novaFatura.getNome_emitente();
        this.data =novaFatura.getData();
        this.alteracao = novaFatura.getAlteracao();
        this.NIF_cliente =novaFatura.getNIF_cliente();
        this.descricao =novaFatura.getDescricao();
        this.naturezaDespesa =novaFatura.getNaturezaDespesa();
        this.valor =novaFatura.getValor();
        this.valida = novaFatura.getValida();
    }
    
    // Getters
    
    public String getNIF_emitente() {
        return this.NIF_emitente;
    }

    public String getNome_emitente() {
        return this.nome_emitente;
    }

    public LocalDate getData() {
        return this.data;
    }
    
    public LocalDate getAlteracao() {
        return this.alteracao;
    }
    
    public String getNIF_cliente() {
        return this.NIF_cliente;
    }
    
    public String getDescricao() {
        return this.descricao;
    }
    
    public Despesa getNaturezaDespesa() {
        return this.naturezaDespesa;
    }
    
    public double getValor() {
        return this.valor;
    }
    
    public boolean getValida() {
        return this.valida;
    }

    //Setters
    public void setNIF_emitente(String novoNIFempresa) {
        this.NIF_emitente =novoNIFempresa;
    }

    public void setNome_emitente(String novoNomeEmpresa) {
        this.nome_emitente=novoNomeEmpresa;
    }

    public void setData(LocalDate novaData) {
        this.data=novaData;
    }
    
    public void setAlteracao(LocalDate novaData) {
        this.alteracao=novaData;
    }

    public void setNIF_cliente(String novoNIFpessoa) {
        this.NIF_cliente =novoNIFpessoa;
    }
    
    public void setDescricao(String novaDescricao) {
        this.descricao=novaDescricao;
    }
    
    public void setNaturezaDespesa (Despesa novaNaturezaDespesa) {
        this.naturezaDespesa = novaNaturezaDespesa;
    }
    
    public void setValor(double novoValor) {
        this.valor=novoValor;
    }
    
    public void setValida(boolean val) {
        this.valida = val;
    }

    
    //toString
    @Override
    public String toString() {
    
        StringBuilder sb = new StringBuilder();
        sb.append("NIF da empresa: ");
        sb.append(this.NIF_emitente + " \n");
        sb.append("Nome da empresa: ");
        sb.append(this.nome_emitente + " \n");
        sb.append("Data emissao: ");
        sb.append(this.data + " \n");
        sb.append("Última alteração: ");
        sb.append(this.alteracao + " \n");
        sb.append("NIF do cliente: ");
        sb.append(this.NIF_cliente + " \n");
        sb.append("Descricao: ");
        sb.append(this.descricao + " \n");
        sb.append("Natureza da despesa: ");
        sb.append(this.naturezaDespesa.getClass().toString() + " \n");
        sb.append("Valor da despesa: ");
        sb.append(this.valor+ "€\n");
        sb.append("Valida: ");
        sb.append(this.valida + "\n");

        return sb.toString();

    }
   
    public boolean equals(Object o){
    if(o==this) return true;
    if((o==null) || (o.getClass() != this.getClass()))
        return false;
    
    Fatura c = (Fatura) o;
    return (this.getNIF_emitente().equals(c.getNIF_emitente()) 
        && this.getNome_emitente().equals(c.getNome_emitente()) 
        && this.getData().equals(c.getData())
        && this.getAlteracao().equals(c.getAlteracao())
        && this.getNIF_cliente().equals(c.getNIF_cliente()) 
        && this.getDescricao().equals(c.getDescricao()) 
        && this.getNaturezaDespesa().equals(c.getNaturezaDespesa()) 
        && this.getValor() == c.getValor()
        && this.getValida() == c.getValida());
    }
    
    public boolean specialEquals(Object o){
    if(o==this) return true;
    if((o==null) || (o.getClass() != this.getClass()))
        return false;
    
    Fatura c = (Fatura) o;
    return (this.getNIF_emitente().equals(c.getNIF_emitente()) 
        && this.getNome_emitente().equals(c.getNome_emitente()) 
        && this.getData().equals(c.getData())
        && this.getNIF_cliente().equals(c.getNIF_cliente()) 
        && this.getDescricao().equals(c.getDescricao()) 
        && this.getValor() == c.getValor());
    }
    
    //Clone
    public Fatura clone(){
        return new Fatura(this);    
    }
    
    public double valorDeducao() {
        return this.valor * this.naturezaDespesa.getDeducao();
    }
    
    public String getCodigoDespesa() {
        return this.naturezaDespesa.getCode();
    }
    
    public Despesa toDespesa(String s) {
        if (s.toLowerCase().contains("saude")) return new Saude();
        else if (s.toLowerCase().contains("restauracao")) return new Restauracao();
        else if (s.toLowerCase().contains("habitacao")) return new Habitacao();
        else if (s.toLowerCase().contains("educacao")) return new Educacao();
        else if (s.toLowerCase().contains("gerais")) return new DespesasGerais();
        else return new Outros();
        
    }
    
    public void muda_Despesa(Despesa d) {
        this.setNaturezaDespesa(d);
        this.setAlteracao(LocalDate.now());
    }

    public String specialToString() {
    
        StringBuilder sb = new StringBuilder();
        
        sb.append("NIF da empresa: ");
        sb.append(this.NIF_emitente + " \n");
        sb.append("Nome da empresa: ");
        sb.append(this.nome_emitente + " \n");
        sb.append("Data emissao: ");
        sb.append(this.data + " \n");
        sb.append("Última alteração: ");
        sb.append(this.alteracao + " \n");
        sb.append("NIF do cliente: ");
        sb.append(this.NIF_cliente + " \n");
        sb.append("Descricao: ");
        sb.append(this.descricao + " \n");
        sb.append("Natureza da despesa: ");
        sb.append(this.naturezaDespesa.getClass().toString() + " \n");
        sb.append("Valor da despesa: ");
        sb.append(this.valor+ "€\n");
        sb.append("Valida: ");
        sb.append(this.valida + "\n\n");
        sb.append("---------------------------------------------------\n");
        return sb.toString();

    }
}
