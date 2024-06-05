package Estrutura;

import Individuo.Cidadao;
import Individuo.Rg;


/**
 *
 * @author ray, Gustavo, Ligeiro
 */
public class ListaEncadeada {
    private No cabeca; /*O no de cabeça aqui, ele vai apontar pro resto da estrutura*/
    private int quantidadeCidadao;
    public ListaEncadeada() {
        this.cabeca = null; /*iniciando a lista vazia*/
        this.quantidadeCidadao = 0;
    }
    
    
    public boolean adicionarLista(Cidadao novoCidadao) {
        
        /*Verificando se o cpf esta cadastrado*/
        Cidadao cidadaoExistente = buscarCidadao(novoCidadao.getCpf()); 
        /*talvez essa verificacao fique por arquivo, ja que vai demorar muito para 
        ele buscar e esse caso so acontece em ufs diferentes*/
        
        /*Se retornar nulo o cidadoexistente ele nao existe, logo eh so inserir o novo rg no noh dele*/
        if(cidadaoExistente != null) {
            cidadaoExistente.getRgGerais().add(novoCidadao.getRgGerais().get(0));    
            return false;
        } else {     
            No novo = new No(novoCidadao);
            if(cabeca == null){
                cabeca = novo;
            } else {
                novo.prox = cabeca;
                cabeca = novo;
            }      
            quantidadeCidadao++;
            return true;
        }
        
    }
    
    public void adicionarNoInicio(Cidadao novoCidadao) {
        No novo = new No(novoCidadao);
        if(cabeca == null){
            cabeca = novo;
        } else {
            novo.prox = cabeca;
            cabeca = novo;
        }      
        quantidadeCidadao++;
    }
    
    
    public Cidadao buscarCidadao(String cpf) { /*Da para usar a busca aqui de cpf, so chamar na interface*/
        if(cabeca == null){           
            return null;          
        }  
        
        No i = cabeca;
        while (i != null) {
            if(i.getCidadao().getCpf().equals(cpf)) {
                return i.getCidadao();
            }
            i = i.prox;
        }
        return null;
    }
    
     public void imprimirLista() { /*Para testes*/
        No i = cabeca;
        while (i != null) {
            System.out.println(i.getCidadao().getNome() );
            System.out.println(i.getCidadao().getCpf() );
            System.out.println(i.getCidadao().getDatanasc());
            for(Rg r : i.getCidadao().getRgGerais()){
                System.out.print(r.getRg() + " ");
                System.out.println(r.getEstadoRG());
            }
            System.out.print(i.getCidadao().getOrigem().getCidade() + " ");
            System.out.println(i.getCidadao().getOrigem().getEstado());
            i = i.prox;
            System.out.println();
        }
        System.out.println();
    }
    

    public No getCabeca() {
        return cabeca;
    }

    public int getQuantidadeCidadao() {
        return quantidadeCidadao;
    }       
     
}
