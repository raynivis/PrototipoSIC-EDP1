package Relatorio;

import Estrutura.NoRB;
import Individuo.Cidadao;
import Individuo.Rg;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

/**
 *
 * @author maymi
 */
public class ArvoreRBRelatorio {
    private NoRB raiz;
    
    public ArvoreRBRelatorio() {
        this.raiz = null;
    }

    // Inserir um novo nó na árvore Red-Black
    public void inserirRBTRapido(Cidadao cidadao) {
        NoRB novoNo = new NoRB(cidadao);
        NoRB y = null;
        NoRB x = raiz;
        while (x != null) {
            y = x;
            if (cidadao.getNome().compareTo(x.getCidadao().getNome()) < 0) {
                x = x.getEsquerdo();
            } else if (cidadao.getNome().compareTo(x.getCidadao().getNome()) > 0) {
                x = x.getDireito();
            }
        }
        novoNo.setPai(y);
        if (y == null) {
            raiz = novoNo;
        } else if (cidadao.getNome().compareTo(y.getCidadao().getNome()) < 0) {
            y.setEsquerdo(novoNo);
        } else {
            y.setDireito(novoNo);
        }
    }

    public void imprimir(Document document) {
        emOrdem(raiz, document);
    }

    private void emOrdem(NoRB raiz, Document document) {
        if (raiz != null) {
            emOrdem(raiz.getEsquerdo(), document);
            try {
                document.add(new Paragraph("   Nome: " + raiz.getCidadao().getNome()));
                document.add(new Paragraph("   CPF: " + raiz.getCidadao().getCpf()));
                document.add(new Paragraph("   Data de Nascimento: " + raiz.getCidadao().getDatanasc()));

                for (Rg r : raiz.getCidadao().getRgGerais()) {
                    document.add(new Paragraph("   RG: " + r.getRg() + " " + r.getEstadoRG()));
                }

                document.add(new Paragraph("   Cidade: " + raiz.getCidadao().getOrigem().getCidade() +
                        ", Estado: " + raiz.getCidadao().getOrigem().getEstado()));

                document.add(new Paragraph(""));
            } catch (Exception e) {
                System.err.println("Erro ao adicionar dados ao PDF: " + e.getMessage());
            }
            emOrdem(raiz.getDireito(), document);
        }
    }


    public NoRB getRaiz() {
        return raiz;
    }
}
