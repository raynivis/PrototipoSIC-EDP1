package Relatorio;

import Estrutura.NoB;
import Individuo.Cidadao;
import Individuo.Rg;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;

/**
 *
 * @author Gustavo
 */
public class ArvoreBRelatorio {
    private NoB raiz;
    private int t; // Grau mínimo da árvore B

    public ArvoreBRelatorio(int grau) {
        this.raiz = null;
        this.t = grau;
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
            while (i >= 0 && cidadao.getNome().compareTo(no.getChaves().get(i).getNome()) < 0) {
                no.getChaves().set(i + 1, no.getChaves().get(i));
                i--;
            }
            no.getChaves().set(i + 1, cidadao);
        } else {
            while (i >= 0 && cidadao.getNome().compareTo(no.getChaves().get(i).getNome()) < 0) {
                i--;
            }
            i++;
            if (no.getFilhos().get(i).getChaves().size() == 2 * t - 1) {
                dividirFilho(no, i, no.getFilhos().get(i));
                if (cidadao.getNome().compareTo(no.getChaves().get(i).getNome()) > 0) {
                    i++;
                }
            }
            inserirNaoCheio(no.getFilhos().get(i), cidadao);
        }
    }

    public void imprimir(Document document) {
        emOrdem(raiz, document);
    }

    private void emOrdem(NoB no, Document document) {
        if (no != null) {
            int i;
            for (i = 0; i < no.getChaves().size(); i++) {
                if (!no.isFolha()) {
                    emOrdem(no.getFilhos().get(i), document);
                }
                try {
                    Cidadao cidadao = no.getChaves().get(i);
                    document.add(new Paragraph("   Nome: " + cidadao.getNome()));
                    document.add(new Paragraph("   CPF: " + cidadao.getCpf()));
                    document.add(new Paragraph("   Data de Nascimento: " + cidadao.getDatanasc()));

                    for (Rg r : cidadao.getRgGerais()) {
                        document.add(new Paragraph("   RG: " + r.getRg() + " " + r.getEstadoRG()));
                    }

                    document.add(new Paragraph("   Cidade: " + cidadao.getOrigem().getCidade() +
                            ", Estado: " + cidadao.getOrigem().getEstado()));
                    document.add(new Paragraph(""));
                } catch (Exception e) {
                    System.err.println("Erro ao adicionar dados ao PDF: " + e.getMessage());
                }
            }
            if (!no.isFolha()) {
                emOrdem(no.getFilhos().get(i), document);
            }
        }
    }

    public NoB getRaiz() {
        return raiz;
    }
}
