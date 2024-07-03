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
    private static boolean status;
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
        status = false;
    }
     
    /* métodos para os 4 tipos de rotação da arvore AVL */
    public NoAVL rotacaoEsquerda(NoAVL raiz) {
        NoAVL b = raiz.getDireito();
        if(b.getFb() == -1) { /* rotação simples */
            raiz.setDireito(b.getEsquerdo());
            b.setEsquerdo(raiz);
            raiz.setFb(0);
            b.setFb(0);
            raiz = b;
            RSE++;
        } else { /* rotação dupla */
            NoAVL c = b.getEsquerdo();
            if (c == null) {
                return raiz;  
            }
            b.setEsquerdo(c.getDireito());
            c.setDireito(b);
            raiz.setDireito(c.getEsquerdo());
            c.setEsquerdo(raiz);
            switch (c.getFb()) {
                case 1:
                    raiz.setFb(-1);
                    b.setFb(0);
                    break;
                case -1:
                    raiz.setFb(0);
                    b.setFb(1);
                    break;
                default:
                    raiz.setFb(0);
                    b.setFb(0);
                    break;
            }
            c.setFb(0);
            raiz = c;
            RDE++;
        }
        status = false;
        return raiz;
    }

    public NoAVL rotacaoDireita(NoAVL raiz) {
        NoAVL b = raiz.getEsquerdo();
        if (b.getFb() == -1) { /* rotação simples */
            raiz.setEsquerdo(b.getDireito());
            b.setDireito(raiz);
            raiz.setFb(0);
            b.setFb(0);
            raiz = b;
            RSD++;
        } else { /* rotação dupla */
            NoAVL c = b.getDireito();
            if (c == null) {
                return raiz;  
            }
            b.setDireito(c.getEsquerdo());
            c.setEsquerdo(b);
            raiz.setEsquerdo(c.getDireito());
            c.setDireito(raiz);
            switch (c.getFb()) {
                case -1:
                    raiz.setFb(1);
                    b.setFb(0);
                    break;
                case 1:
                    raiz.setFb(0);
                    b.setFb(-1);
                    break;
                default:
                    raiz.setFb(0);
                    b.setFb(0);
                    break;
            }
            c.setFb(0);
            raiz = c;
            RDD++;
        }
        status = false;
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
            return buscarAVL(no.getEsquerdo(), cpf); 
        } else {
            return buscarAVL(no.getDireito(), cpf);
        }
    }
    
    public void inserirAVL(Cidadao cidadao) {
        raiz = inserirAVL(raiz, cidadao);
    }
    
    private NoAVL inserirAVL(NoAVL raiz, Cidadao cidadao) {
        if(raiz == null) {
            contaCidadao++;
            status = true;
            return new NoAVL(cidadao);
        }else {    
            if (cidadao.getCpf().compareTo(raiz.getCidadao().getCpf()) < 0) {
                raiz.setEsquerdo(inserirAVL(raiz.getEsquerdo(), cidadao));
                if(status == true) {
                    switch (raiz.getFb()) {
                        case 1 : 
                            raiz.setFb(0);
                            status = false;
                            break; 
                        case 0 : 
                            raiz.setFb(-1); 
                            break;
                        case -1 : 
                            raiz = rotacaoDireita(raiz);
                            break;
                    }
                }
            } else if(cidadao.getCpf().compareTo(raiz.getCidadao().getCpf()) > 0) {
                raiz.setDireito(inserirAVL(raiz.getDireito(), cidadao));
                if(status == true) {
                    switch (raiz.getFb()) {
                        case -1:
                            raiz.setFb(0);
                            status = false;
                            break;
                        case 0:
                            raiz.setFb(1); 
                            break;
                        case 1:
                            raiz = rotacaoEsquerda(raiz);
                            break;
                    }
                }
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
            preOrdem(raiz.getEsquerdo(), consumer);
            preOrdem(raiz.getDireito(), consumer);
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
