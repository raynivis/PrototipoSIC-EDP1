package Relatorio;

import Individuo.Cidadao;

/**
 *
 * @author nivis
 */
public class NoRlt {  
    private Cidadao chave;
    public NoRlt prox;

    public NoRlt(Cidadao chave) {
        this.chave = chave;
        this.prox = null;
    }

    public void setChave(Cidadao chave) {
        this.chave = chave;
    }
     
    public Cidadao getCidadao() {
        return chave;
    }

}
