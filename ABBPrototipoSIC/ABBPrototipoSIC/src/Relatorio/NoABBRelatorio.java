package Relatorio;

import Individuo.Cidadao;

/**
 *
 * @author Gustavo
 */
public class NoABBRelatorio {  
    private Cidadao chave;
    public NoABBRelatorio esquerda;
    public NoABBRelatorio direita;

    public NoABBRelatorio(Cidadao chave) {
        this.chave = chave;
        this.esquerda = null;
        this.direita = null;
    }

    public Cidadao getCidadao() {
        return chave;
    }

    public void setCidadao(Cidadao chave) {
        this.chave = chave;
    }

    public NoABBRelatorio getEsquerda() {
        return esquerda;
    }

    public NoABBRelatorio getDireita() {
        return direita;
    }
    
    

}
