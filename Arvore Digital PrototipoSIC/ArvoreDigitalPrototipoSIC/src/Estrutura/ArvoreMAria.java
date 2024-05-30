package Estrutura;

import Individuo.Cidadao;
import Individuo.Rg;
import java.util.List;

public class ArvoreMAria {
    private NoArvore raiz;

    public ArvoreMAria() {
        this.raiz = new NoArvore();
    }

    public NoArvore getRaiz() {
        return raiz;
    }

    public void adicionarCidadao(Cidadao cidadao) {
        NoArvore atual = raiz;
        String cpf = cidadao.getCpf();
        for (char digitoChar : cpf.toCharArray()) {
            int digito = Character.getNumericValue(digitoChar);
            atual = atual.obterOuCriarFilho(digito);
        }
        if (atual.getCidadao() != null) {
            // Cidadão já existe, adicionar RGs novos
            List<Rg> rgsExistentes = atual.getCidadao().getRgGerais();
            for (Rg novoRg : cidadao.getRgGerais()) {
                boolean rgExiste = false;
                for (Rg rgExistente : rgsExistentes) {
                    if (rgExistente.getRg().equals(novoRg.getRg()) && rgExistente.getEstadoRG().equals(novoRg.getEstadoRG())) {
                        rgExiste = true;
                        break;
                    }
                }
                if (!rgExiste) {
                    rgsExistentes.add(novoRg);
                }
            }
        } else {
            // Cidadão novo, adicionar na árvore
            atual.setCidadao(cidadao);
        }
    }

    public Cidadao buscarCidadao(String cpf) {
        NoArvore atual = raiz;
        for (char digitoChar : cpf.toCharArray()) {
            int digito = Character.getNumericValue(digitoChar);
            atual = atual.getFilhos()[digito];
            if (atual == null) {
                return null; // CPF não encontrado
            }
        }
        return atual.getCidadao();
    }

    public void imprimirArvore() {
        imprimirNo(raiz, "");
    }

    private void imprimirNo(NoArvore no, String prefixo) {
        if (no.getCidadao() != null) {
            Cidadao cidadao = no.getCidadao();
            System.out.println(prefixo + "CPF: " + cidadao.getCpf() + ", Nome: " + cidadao.getNome() + ", Data Nasc: " + cidadao.getDatanasc());
            System.out.println(prefixo + "RGs:");
            for (Rg rg : cidadao.getRgGerais()) {
                System.out.println(prefixo + "  RG: " + rg.getRg() + ", Estado: " + rg.getEstadoRG());
            }
            System.out.println(prefixo + "Naturalidade: " + cidadao.getOrigem().getCidade() + " - " + cidadao.getOrigem().getEstado());
        }
        for (int i = 0; i < 10; i++) {
            if (no.getFilhos()[i] != null) {
                imprimirNo(no.getFilhos()[i], prefixo + "  ");
            }
        }
    }
}
