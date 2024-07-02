package Estrutura;
import Individuo.Cidadao;

/**
 *
 * @author maymi
 */
public class NoRB {
    private Cidadao cidadao;
    private NoRB esquerdo;
    private NoRB direito;
    private NoRB pai;
    private boolean isRed;

    public NoRB(Cidadao cidadao) {
        this.cidadao = cidadao;
        this.esquerdo = null;
        this.direito = null;
        this.pai = null;
        this.isRed = true; // New nodes are red by default
    }

    public Cidadao getCidadao() {
        return cidadao;
    }

    public void setCidadao(Cidadao cidadao) {
        this.cidadao = cidadao;
    }

    public NoRB getEsquerdo() {
        return esquerdo;
    }

    public void setEsquerdo(NoRB esquerdo) {
        this.esquerdo = esquerdo;
    }

    public NoRB getDireito() {
        return direito;
    }

    public void setDireito(NoRB direito) {
        this.direito = direito;
    }

    public NoRB getPai() {
        return pai;
    }

    public void setPai(NoRB pai) {
        this.pai = pai;
    }

    public boolean isRed() {
        return isRed;
    }

    public void setRed(boolean isRed) {
        this.isRed = isRed;
    }
}
