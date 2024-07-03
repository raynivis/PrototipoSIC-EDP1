package Estrutura;

import Individuo.Cidadao;
import java.util.ArrayList;
import java.util.List;

public class NoB {
    private List<Cidadao> chaves;
    private List<NoB> filhos;
    private boolean folha;
    private int t; // Grau mínimo (define a ordem da árvore B)

    public NoB(int t, boolean folha) {
        this.t = t;
        this.folha = folha;
        this.chaves = new ArrayList<>();
        this.filhos = new ArrayList<>();
    }

    public List<Cidadao> getChaves() {
        return chaves;
    }

    public void setChaves(List<Cidadao> chaves) {
        this.chaves = chaves;
    }

    public List<NoB> getFilhos() {
        return filhos;
    }

    public void setFilhos(List<NoB> filhos) {
        this.filhos = filhos;
    }

    public boolean isFolha() {
        return folha;
    }

    public void setFolha(boolean folha) {
        this.folha = folha;
    }

    public int getT() {
        return t;
    }

    public void setT(int t) {
        this.t = t;
    }
}
