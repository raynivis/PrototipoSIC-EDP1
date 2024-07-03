package Estrutura;
import Individuo.Cidadao;

/**
 *
 * @author Gustavo Cortez
 */
public class NoABB {
    public Cidadao cidadao;
    public NoABB esquerda;
    public NoABB direita;

    public NoABB(Cidadao cidadao) {
        this.cidadao = cidadao;
        this.esquerda = null;
        this.direita = null;
    }

    public Cidadao getCidadao() {
        return cidadao;
    }

    public NoABB getEsquerda() {
        return esquerda;
    }

    public NoABB getDireita() {
        return direita;
    }

    public void setCidadao(Cidadao cidadao) {
        this.cidadao = cidadao;
    }

    public void setEsquerda(NoABB esquerda) {
        this.esquerda = esquerda;
    }

    public void setDireita(NoABB direita) {
        this.direita = direita;
    }
    
    
    
    
}
