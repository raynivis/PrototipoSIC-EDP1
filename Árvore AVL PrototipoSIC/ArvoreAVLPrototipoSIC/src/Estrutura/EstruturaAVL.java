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
    private int contaCidadao;
    private int RSE; /* contador de rotações simples para a esquerda */
    private int RSD; /* contador de rotações simples para a direita */
    private int RDE; /* contador de rotações duplas para a esquerda */
    private int RDD; /* contador de rotações duplas para a direita */
    
    public EstruturaAVL() {
        this.raiz = null;
        contaCidadao = 0;
        RSE = 0;
        RSD = 0;
        RDE = 0;
        RDD = 0;
    }

    
    public int alturaNo(NoAVL no) { /* retorna a altura do nó caso exista */
        if(no == null) {
            return 0;
        }
        return no.getAltura();
    }
    
    public int fatorBalanceamento(NoAVL no) { /* calculo do FB do no com suas árvores esquerda e direita */
        if (no != null) {
            return alturaNo(no.direito) - alturaNo(no.esquerdo);
        }
        return 0;
    }
    
    /* métodos para os 4 tipos de rotação da arvore AVL */
    public NoAVL simplesEsquerda(NoAVL raiz) {
        NoAVL x = raiz.getDireito();
        NoAVL filhox = x.getEsquerdo();

        x.setEsquerdo(raiz);
        raiz.setDireito(filhox);

        // Atualiza as alturas
        raiz.setAltura(Math.max(alturaNo(raiz.getEsquerdo()), alturaNo(raiz.getDireito())) + 1);
        x.setAltura(Math.max(alturaNo(x.getEsquerdo()), alturaNo(x.getDireito())) + 1);

        return x;
    }

    public NoAVL simplesDireita(NoAVL raiz) {

        NoAVL x = raiz.getEsquerdo();
        if (x == null) {
            return raiz; 
        }

        NoAVL filhox = x.getDireito();

        x.setDireito(raiz);
        raiz.setEsquerdo(filhox);

        // Atualiza as alturas
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
            RSD++;
        }else if(fb == -2 && fatorBalanceamento(raiz.esquerdo) == 1) {
            raiz = duplaDireita(raiz);
            RDD++;
        }else if(fb == 2 && fatorBalanceamento(raiz.direito) == -1) {
            raiz = simplesEsquerda(raiz);
            RSE++;
        }else if(fb == 2 && fatorBalanceamento(raiz.direito) == 1){
            raiz = duplaEsquerda(raiz);
            RDE++;
        }
        return raiz;
    }
    
    public Cidadao buscarAVL(String cpf) {
        return buscarAVL(raiz, cpf);
    }

    private Cidadao buscarAVL(NoAVL no, String cpf) {
        if (no == null) {
            return null; 
        }
        if (no.getCidadao().getCpf().equals(cpf)) {
            return no.getCidadao(); 
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
            contaCidadao++;
            return new NoAVL(cidadao);
        }else {    
            if(buscarAVL(raiz, cidadao.getCpf()) == null) {
                    if (cidadao.getCpf().compareTo(raiz.getCidadao().getCpf()) <= 0) {
                        raiz.esquerdo = inserirAVL(raiz.esquerdo, cidadao);
                    } else {
                        raiz.direito = inserirAVL(raiz.direito, cidadao);
                    }
                    raiz.setAltura(Math.max(alturaNo(raiz.getEsquerdo()), alturaNo(raiz.getDireito())) + 1);
                    raiz = balanceia(raiz);
                return raiz;
            } else {
                Cidadao cidadaoExistente = buscarAVL(cidadao.getCpf());
                if(cidadaoExistente != null) {
                    boolean substituir = false;
                    for(Rg r : cidadaoExistente.getRgGerais()){                           
                        if(cidadao.getRgGerais().get(0).getEstadoRG().equals(r.getEstadoRG())){
                            r.setRg(cidadao.getRgGerais().get(0).getRg());                
                            substituir = true;
                        }
                    }
                    if(substituir == false)
                        cidadaoExistente.getRgGerais().add(cidadao.getRgGerais().get(0));
                }    
                return raiz;
            }
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
        qtdCidadaoERotacao();
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
    
    private void qtdCidadaoERotacao() {
            System.out.println("\nQuantidade de pessoas: " + contaCidadao
                    + "\nQuantidade de rotacoes: " +
                    "\n  Simples esquerda: " + RSE +
                    "\n  Simples direita: " + RSD +
                    "\n  Dupla Esquerda: " + RDE +
                    "\n  Dupla Direita: " + RDD);   
    }
    
}    
