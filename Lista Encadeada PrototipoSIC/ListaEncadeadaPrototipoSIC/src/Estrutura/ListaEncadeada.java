package Estrutura;

import Individuo.Cidadao;
import Individuo.Rg;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ray, Gustavo, Ligeiro
 */
public class ListaEncadeada {
    private No topo; /*O no de topo aqui, ele vai apontar pro resto da estrutura*/
    private static int quantidadeCidadao;
    public ListaEncadeada() {
        this.topo = null; /*iniciando a lista vazia*/
        quantidadeCidadao = 0;
    }
    
    
    public void adicionarLista(Cidadao novoCidadao) {
        
        /*Verificando se o cpf esta cadastrado*/
        Cidadao cidadaoExistente = buscarCidadao(novoCidadao.getCpf()); 
        /*talvez essa verificacao fique por arquivo, ja que vai demorar muito para 
        ele buscar e esse caso so acontece em ufs diferentes*/
        
        /*Se retornar nulo o cidadoexistente ele nao existe, logo eh so inserir o novo rg no noh dele*/
        if(cidadaoExistente != null) {
            cidadaoExistente.getRgGerais().add(novoCidadao.getRgGerais().get(0));            
        } else {     
            No novo = new No(novoCidadao);
            if(topo == null){
                topo = novo;
            } else {
                novo.prox = topo;
                topo = novo;
            }      
            quantidadeCidadao++;
        }
        
    }
    
    public void adicionarNoInicio(Cidadao novoCidadao) {
        No novo = new No(novoCidadao);
        if(topo == null){
            topo = novo;
        } else {
            novo.prox = topo;
            topo = novo;
        }      
        quantidadeCidadao++;
    }
    
    
    public Cidadao buscarCidadao( String cpf) { /*Da para usar a busca aqui de cpf, so chamar na interface*/
        if(topo == null){           
            return null;          
        }  
        
        No i = topo;
        while (i != null) {
            if(i.getCidadao().getCpf().equals(cpf)) {
                return i.getCidadao();
            }
            i = i.prox;
        }
        return null;
    }
    
     public void imprimirLista() { /*Para testes*/
        No i = topo;
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
    
    public List<Cidadao> getCidadaos() {
        List<Cidadao> cidadaos = new ArrayList<>();
        No atual = topo;
        while (atual != null) {
            cidadaos.add(atual.getCidadao());
            atual = atual.prox;
        }
        return cidadaos;
    }

    public No getTopo() {
        return topo;
    }

    public static int getQuantidadeCidadao() {
        return quantidadeCidadao;
    }       
     
}
