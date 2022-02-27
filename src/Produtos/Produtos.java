package Produtos;

import java.util.InputMismatchException;

public class Produtos  {

    //atributos
    protected int codigo;
    protected String nome;
    protected double valor;
    protected int qtdEstoque;

    //Setters
    public void setCodigo(int codigo){
        this.codigo = codigo;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public void setValor(double valor){
        this.valor = valor;
    }
    public void setQtdEstoque(int qtdEstoque){
        if(qtdEstoque <= 0){
            throw new InputMismatchException("O estoque não poode ser menor ou igual á 0");
        }
        this.qtdEstoque = qtdEstoque;
    }

    //Getters 
    public int getCodigo(){
        return codigo;
    }
    public String getNome(){
        return nome;
    }
    public double getValor(){
        return valor;
    }
    public int getQtdEstoque(){
        return qtdEstoque;
    }

    //construtores
    
    public Produtos(int codigo, String nome, double valor, int qtdEstoque){
        this.codigo = codigo;
        this.nome = nome;
        this.valor = valor;
        this.qtdEstoque = qtdEstoque;
    }

    public Produtos() {
    }
    //Método
    public String toString(){

        return String.format(codigo+"                     "+nome+"             "+valor+"               "+qtdEstoque+"\n");
    }
    
    
}
