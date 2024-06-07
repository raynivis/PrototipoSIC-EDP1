package Estrutura;

import Individuo.Cidadao;


/**
 *
 * @author ray, Gustavo, Ligeiro
 */
public class ListaEncadeada {
    private No topo; /*O no de cabeça aqui, ele vai apontar pro resto da estrutura*/
    private int quantidadeCidadao;
    public ListaEncadeada() {
        this.topo = null; /*iniciando a lista vazia*/
        this.quantidadeCidadao = 0;
    }
    
    
    public boolean adicionarLista(Cidadao novoCidadao) {
        
        /*Verificando se o cpf esta cadastrado*/
        Cidadao cidadaoExistente = buscarCidadao(novoCidadao.getCpf()); 
        /*talvez essa verificacao fique por arquivo, ja que vai demorar muito para 
        ele buscar e esse caso so acontece em ufs diferentes*/
        
        /*Se retornar nulo o cidadoexistente ele nao existe, logo eh so inserir o novo rg no noh dele*/
        if(cidadaoExistente != null) {
            boolean substituir = false;
            for(Rg r : cidadaoExistente.getRgGerais()){                           
                if(novoCidadao.getRgGerais().get(0).getEstadoRG().equals(r.getEstadoRG())){ /*Se o Rg representa da mesma UF, ele é subsituido*/
                    r.setRg(novoCidadao.getRgGerais().get(0).getRg());                
                    substituir = true;
                }
            }
            if(substituir == false)
                cidadaoExistente.getRgGerais().add(novoCidadao.getRgGerais().get(0));              
            return false;
        } else {     
            No novo = new No(novoCidadao);
            if(topo == null){
                topo = novo;
            } else {
                novo.prox = topo;
                topo = novo;
            }      
            quantidadeCidadao++;
            return true;
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
    
    
    public Cidadao buscarCidadao(String cpf) { /*Da para usar a busca aqui de cpf, so chamar na interface*/
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

    public No getTopo() {
        return topo;
    }

    public int getQuantidadeCidadao() {
        return quantidadeCidadao;
    }       
     
}
