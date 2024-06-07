package Estrutura;
import Individuo.Cidadao;

/**
 *
 * @author maymi
 */
public class NoAVL {
    private Cidadao cidadao;
    NoAVL esquerdo;
    NoAVL direito;
    private int altura;

    public NoAVL(Cidadao cidadao) {
        this.cidadao = cidadao;
        this.esquerdo = null;
        this.direito = null;
        this.altura = 1;
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

    public int getAltura() {
        return altura;
    }

    public void setAltura(int alutra) {
        this.altura = alutra;
    }
}
