/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estrutura;

import Individuo.Cidadao;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nivis
 */
public class TabelaHash {
    private ListaEncadeada[] tabela;
    private int quantidadetotalCidadao;

    public TabelaHash() {
        tabela = new ListaEncadeada[1000000]; /*Cpf vai pra chave ddd.ddd.dd*/
        quantidadetotalCidadao = 0;
        for(int i = 0; i < tabela.length; i++) {
            tabela[i] = new ListaEncadeada();
        }
    }
       
    public ListaEncadeada[] getTabela() {
        return tabela;
    }

    public void setTabela(ListaEncadeada[] tabela) {
        this.tabela = tabela;
    }

    public String getQuantidadetotalCidadao() {
        return "A Quantidade inserida de Cidadao eh " + quantidadetotalCidadao + ".";
    }
    
    public static int funcaoDispersao(String cpf){
        int digito = 100000; 
        int somar = 0; 
        for (int i = 0; i < cpf.length()-5; i++) {
            char c = cpf.charAt(i);
            int ascii = (int) c-48;
            somar += ascii*digito;           
            digito/=10;
        }
        return somar%1000000;        
    }
    
    public void inserirChave(Cidadao novoCidadao){
        int posicao = funcaoDispersao(novoCidadao.getCpf());
        boolean adicionado = tabela[posicao].adicionarLista(novoCidadao);
        if(adicionado)     
            quantidadetotalCidadao++;
    }
    
    public void inserirChaveRapido(Cidadao novoCidadao){
        int posicao = funcaoDispersao(novoCidadao.getCpf());
        tabela[posicao].adicionarNoInicio(novoCidadao);
        quantidadetotalCidadao++;          
    }
    
    
    public Cidadao buscarCidadao(String cpf){      
        int posicao = funcaoDispersao(cpf);
        
        No i = tabela[posicao].getTopo();
        while(i != null) {
            if(i.getCidadao().getCpf().equals(cpf)){
                return i.getCidadao();
            }
            i = i.prox;
        }       
               
        return null;              
    }
    
    public List<Cidadao> getCidadaos() {
        List<Cidadao> cidadaos = new ArrayList<>();
        for(int i = 0; i < tabela.length; i++) {
            if(tabela[i].getTopo() != null){
                No atual = tabela[i].getTopo();
                while (atual != null) {
                    cidadaos.add(atual.getCidadao());
                    atual = atual.prox;
                } 
            }
        }   
        return cidadaos;
    }
    
    public void imprimirColisoes(){
        for(int i = 0; i < tabela.length; i++) {
            if(tabela[i].getTopo() != null) {            
                System.out.println("A posicao " + i + " com " + tabela[i].getQuantidadeCidadao() + " elemento/s" );
            }
        }
        System.out.println(getQuantidadetotalCidadao());
    }
}
    

