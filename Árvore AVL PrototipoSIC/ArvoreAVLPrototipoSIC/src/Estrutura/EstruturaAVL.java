package Estrutura;

import Individuo.Cidadao;
import Individuo.Rg;
import java.util.function.Consumer;

/**
 *
 * @author maymi
 */
public class EstruturaAVL {
    private NoAVL raiz;

    public EstruturaAVL() {
        this.raiz = null;
    }
    
    public int alturaNo(NoAVL no) { /* retorna a altura do nó caso exista */
        if(no == null) {
            return 0;
        }
        return no.getAltura();
    }
    
    public int fatorBalanceamento(NoAVL no) { 
        if (no != null && no.direito != null && no.esquerdo != null) {
            return no.direito.getAltura() - no.esquerdo.getAltura();
        }
        return 0;
}
    
    /* métodos para os 4 tipos de rotação da arvore AVL */
    public NoAVL simplesEsquerda(NoAVL raiz) {
        NoAVL x, filhox;
        
        x = raiz.getDireito();
        filhox = x.getEsquerdo();
        
        x.setEsquerdo(raiz);
        raiz.setDireito(filhox);
        
        raiz.setAltura(Math.max(alturaNo(raiz.getEsquerdo()), alturaNo(raiz.getDireito())) + 1);
        x.setAltura(Math.max(alturaNo(x.getEsquerdo()), alturaNo(x.getDireito())) + 1);
        
        return x;
    }    

    public NoAVL simplesDireita(NoAVL raiz) {
        NoAVL x, filhox;
        
        x = raiz.getEsquerdo();
        filhox = x.getDireito();
        
        x.setDireito(raiz);
        raiz.setEsquerdo(filhox);
        
        raiz.setAltura(Math.max(alturaNo(raiz.getEsquerdo()), alturaNo(raiz.getDireito())) + 1);
        x.setAltura(Math.max(alturaNo(x.getEsquerdo()), alturaNo(x.getDireito())) + 1);
        
        return x;
    }
    
    public NoAVL duplaEsquerda(NoAVL raiz) {
        raiz.setDireito(simplesDireita(raiz.getDireito()));
        return simplesEsquerda(raiz);
    }
    
    public NoAVL duplaDireita(NoAVL raiz) {
        raiz.setEsquerdo(simplesEsquerda(raiz.getEsquerdo()));
        return simplesDireita(raiz);
    }
    
    /* balanceia a raiz depois de uma inserção a partir do fb do nó/raiz */
    public NoAVL balanceia(NoAVL raiz) {
        int fb = fatorBalanceamento(raiz);
        
        if(fb == -2 && fatorBalanceamento(raiz.esquerdo) == -1) {
            raiz = simplesDireita(raiz);
        }else if(fb == -2 && fatorBalanceamento(raiz.esquerdo) == 1) {
            raiz = duplaDireita(raiz);
        }else if(fb == 2 && fatorBalanceamento(raiz.direito) == -1) {
            raiz = simplesEsquerda(raiz);
        }else if(fb == 2 && fatorBalanceamento(raiz.direito) == 1){
            raiz = duplaEsquerda(raiz);
        }
        return raiz;
    }
    
    public Cidadao buscarAVL(String cpf) {
        return buscarAVL(raiz, cpf);
    }

    private Cidadao buscarAVL(NoAVL no, String cpf) {
        if (no == null || no.getCidadao().getCpf().equals(cpf)) {
            return (no != null) ? no.getCidadao() : null;
        }
        if (cpf.compareTo(no.getCidadao().getCpf()) < 0) {
            return buscarAVL(no.esquerdo, cpf);
        } else {
            return buscarAVL(no.direito, cpf);
        }
    }
    
    public void inserirAVL(Cidadao cidadao) {
        raiz = inserirAVL(raiz, cidadao);
    }
    
    private NoAVL inserirAVL(NoAVL raiz, Cidadao cidadao) {
        if(raiz == null) {
        return new NoAVL(cidadao);
    } else {
        if(buscarAVL(raiz, cidadao.getCpf()) == null) {
            if (cidadao.getCpf().compareTo(raiz.getCidadao().getCpf()) <= 0) {
                raiz.esquerdo = inserirAVL(raiz.esquerdo, cidadao);
            } else {
                raiz.direito = inserirAVL(raiz.direito, cidadao);
            }
            raiz.setAltura(Math.max(alturaNo(raiz.getEsquerdo()), alturaNo(raiz.getDireito())) + 1);
            raiz = balanceia(raiz);
        }
        return raiz;
    }
    }
    
    public void preOrdem(Consumer<NoAVL> consumer) {
        preOrdem(raiz, consumer);
    }

    private void preOrdem(NoAVL raiz, Consumer<NoAVL> consumer) {
        if (raiz != null) {
            consumer.accept(raiz);
            preOrdem(raiz.esquerdo, consumer);
            preOrdem(raiz.direito, consumer);
        }
    }

    public void imprimir() {
        imprimirAVL(raiz);
    }
    
    public void imprimirAVL(NoAVL raiz) {
        if (raiz != null) {
            Cidadao cidadao = raiz.getCidadao();
            imprimirAVL(raiz.getEsquerdo());
            System.out.println("  CPF: " + cidadao.getCpf() + ", Nome: " + cidadao.getNome() + ", Data Nasc: " + cidadao.getDatanasc());
            System.out.println("  RGs:");
            for (Rg rg : cidadao.getRgGerais()) {
                System.out.println("    RG: " + rg.getRg() + ", Estado: " + rg.getEstadoRG());
            }
            System.out.println("  Naturalidade: " + cidadao.getOrigem().getCidade() + " - " + cidadao.getOrigem().getEstado());
            imprimirAVL(raiz.getDireito());
        }
    }
    
    public NoAVL getRaiz() {
        return raiz;
    }
}    
