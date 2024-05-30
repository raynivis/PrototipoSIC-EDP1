package Estrutura;
import Individuo.Cidadao;

/**
 *
 * @author ra
 */
public class NoArvore {
    private NoArvore[] filhos;
    private Cidadao cidadao;

    public NoArvore() {
        this.filhos = new NoArvore[10]; // Vetor para dígitos 0-9
        this.cidadao = null;
    }

    public NoArvore[] getFilhos() {
        return filhos;
    }

    public Cidadao getCidadao() {
        return cidadao;
    }

    public void setCidadao(Cidadao cidadao) {
        this.cidadao = cidadao;
    }

    public NoArvore obterOuCriarFilho(int digito) {
        if (filhos[digito] == null) {
            filhos[digito] = new NoArvore();
        }
        return filhos[digito];
    }
}

