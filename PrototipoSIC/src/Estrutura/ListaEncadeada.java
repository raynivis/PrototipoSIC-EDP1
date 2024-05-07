/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estrutura;

import Individuo.Cidadao;
import Individuo.Rg;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ra
 */
public class ListaEncadeada {
    private No cabeca; /*O no de cabeça aqui, ele vai apontar pro resto da estrutura*/

    public ListaEncadeada() {
        this.cabeca = null; /*iniciando a lista vazia*/
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

            if (cabeca == null) {
                cabeca = novo;
            }
            else {
                No i = cabeca;
                while (i.prox != null) {
                    i = i.prox;
                }
                i.prox = novo;
            }
        }
    }
    
    public Cidadao buscarCidadao(String cpf) { /*Da para usar a busca aqui de cpf, so chamar na interface*/
        if(cabeca == null){
            return null;
        }
        
        No i = cabeca;
        while (i.prox != null) {
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
    
    public List<Cidadao> getCidadaos() {
        List<Cidadao> cidadaos = new ArrayList<>();
        No atual = cabeca;
        while (atual != null) {
            cidadaos.add(atual.getCidadao());
            atual = atual.prox;
        }
        return cidadaos;
    }

    public No getCabeca() {
        return cabeca;
    }
    
     
}
