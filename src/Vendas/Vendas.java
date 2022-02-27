package Vendas;


import Produtos.Produtos;

public class Vendas extends Produtos {

    //atributos
    private String dataVenda;
    private String produtoVendido;
    private int qtdVendida;
    private double valorTotal;
    
    
    //Setters
    public void setDataVenda(String dataVenda) {
        this.dataVenda = dataVenda;
    }
    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
    public void setQtdVendida(int qtdVendida) {
        this.qtdVendida = qtdVendida;
    }
    public void setProdutoVendido(String produtoVendido) {
        this.produtoVendido = produtoVendido;
    }
    
    //Getters
    public double getValorTotal() {
        return valorTotal;
    }
    public String getDataVenda() {
        return dataVenda;
    }
    public String getProdutoVendido() {
        return produtoVendido;
    }
    public int getQtdVendida() {
        return qtdVendida;
    }
    //construtor
    public Vendas(){

    }
    public Vendas(String dataVenda, String produtoVendido, int qtdVendida, double valorTotal){
        this.dataVenda = dataVenda;
        this.produtoVendido = produtoVendido;
        this.qtdVendida = qtdVendida;
        this.valorTotal = valorTotal;

    }

    public String toString(){
        System.out.print("Data da venda          Produto           Quantidade           Valor Total (R$)\n");
        return String.format(dataVenda+"             "+produtoVendido+"              "+qtdVendida+"                      "+valorTotal+"\n");
    }

    public int compareTo(String inicio) {
        return 0;
    }
    

    

    

    

    
    
}
