package Estrutura;
import Individuo.Cidadao;

/**
 *
 * @author maymi
 */
public class NoAVL {
    private Cidadao cidadao;
    private NoAVL esquerdo;
    private NoAVL direito;
    private int fb;

    public NoAVL(Cidadao cidadao) {
        this.cidadao = cidadao;
        this.esquerdo = null;
        this.direito = null;
        this.fb = 0;
    }

    public Cidadao getCidadao() {
        return cidadao;
    }

    public void setCidadao(Cidadao chave) {
        this.cidadao = chave;
    }

    public NoAVL getEsquerdo() {
        return esquerdo;
    }

    public void setEsquerdo(NoAVL esquerdo) {
        this.esquerdo = esquerdo;
    }

    public NoAVL getDireito() {
        return direito;
    }

    public void setDireito(NoAVL direito) {
        this.direito = direito;
    }

    public int getFb() {
        return fb;
    }

    public void setFb(int fb) {
        this.fb = fb;
    }
}
