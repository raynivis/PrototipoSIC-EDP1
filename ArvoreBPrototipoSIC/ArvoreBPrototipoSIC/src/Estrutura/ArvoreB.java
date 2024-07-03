package Estrutura;

import Individuo.Cidadao;
import java.util.Comparator;
import java.util.function.Consumer;

public class ArvoreB {
    private NoB raiz;
    private int t; // Grau mínimo (define a ordem da árvore B)

    public ArvoreB(int t) {
        this.raiz = null;
        this.t = t;
    }

    public NoB getRaiz() {
        return raiz;
    }

    public Cidadao buscar(String cpf) {
        return (raiz == null) ? null : buscar(raiz, cpf);
    }

    private Cidadao buscar(NoB no, String cpf) {
        int i = 0;
        while (i < no.getChaves().size() && cpf.compareTo(no.getChaves().get(i).getCpf()) > 0) {
            i++;
        }

        if (i < no.getChaves().size() && no.getChaves().get(i).getCpf().equals(cpf)) {
            return no.getChaves().get(i);
        }

        if (no.isFolha()) {
            return null;
        } else {
            return buscar(no.getFilhos().get(i), cpf);
        }
    }

    public void inserir(Cidadao cidadao) {
        if (raiz == null) {
            raiz = new NoB(t, true);
            raiz.getChaves().add(cidadao);
        } else {
            if (raiz.getChaves().size() == 2 * t - 1) {
                NoB s = new NoB(t, false);
                s.getFilhos().add(raiz);
                dividirFilho(s, 0, raiz);
                inserirNaoCheio(s, cidadao);
                raiz = s;
            } else {
                inserirNaoCheio(raiz, cidadao);
            }
        }
    }

    private void dividirFilho(NoB pai, int i, NoB cheio) {
        NoB novoNo = new NoB(cheio.getT(), cheio.isFolha());
        for (int j = 0; j < t - 1; j++) {
            novoNo.getChaves().add(cheio.getChaves().remove(t));
        }

        if (!cheio.isFolha()) {
            for (int j = 0; j < t; j++) {
                novoNo.getFilhos().add(cheio.getFilhos().remove(t));
            }
        }

        pai.getFilhos().add(i + 1, novoNo);
        pai.getChaves().add(i, cheio.getChaves().remove(t - 1));
    }

    private void inserirNaoCheio(NoB no, Cidadao cidadao) {
        int i = no.getChaves().size() - 1;

        if (no.isFolha()) {
            no.getChaves().add(null);
            while (i >= 0 && cidadao.getCpf().compareTo(no.getChaves().get(i).getCpf()) < 0) {
                no.getChaves().set(i + 1, no.getChaves().get(i));
                i--;
            }
            no.getChaves().set(i + 1, cidadao);
        } else {
            while (i >= 0 && cidadao.getCpf().compareTo(no.getChaves().get(i).getCpf()) < 0) {
                i--;
            }
            i++;
            if (no.getFilhos().get(i).getChaves().size() == 2 * t - 1) {
                dividirFilho(no, i, no.getFilhos().get(i));
                if (cidadao.getCpf().compareTo(no.getChaves().get(i).getCpf()) > 0) {
                    i++;
                }
            }
            inserirNaoCheio(no.getFilhos().get(i), cidadao);
        }
    }

    public void imprimir() {
        imprimir(raiz, 0);
    }

    private void imprimir(NoB no, int nivel) {
        if (no != null) {
            System.out.println("Nivel " + nivel);
            for (Cidadao cidadao : no.getChaves()) {
                System.out.println("  CPF: " + cidadao.getCpf() + ", Nome: " + cidadao.getNome());
            }
            for (NoB filho : no.getFilhos()) {
                imprimir(filho, nivel + 1);
            }
        }
    }
    
    public void emOrdem(Consumer<Cidadao> consumer) {
        if (raiz != null) {
            emOrdem(raiz, consumer);
        }
    }

    private void emOrdem(NoB no, Consumer<Cidadao> consumer) {
        int i;
        for (i = 0; i < no.getChaves().size(); i++) {
            if (!no.isFolha()) {
                emOrdem(no.getFilhos().get(i), consumer);
            }
            consumer.accept(no.getChaves().get(i));
        }
        if (!no.isFolha()) {
            emOrdem(no.getFilhos().get(i), consumer);
        }
    }
}
