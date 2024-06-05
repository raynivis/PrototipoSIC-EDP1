package Relatorio;
import Individuo.Cidadao;
import Individuo.Rg;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
/**
 *
 * @author Gustavo
 */
public class ABBRelatorio {
    private NoABBRelatorio raiz;

    public ABBRelatorio() {
        this.raiz = null;
    }

    public void inserir(Cidadao novo) {
        raiz = inserir(raiz, novo);
    }

    private NoABBRelatorio inserir(NoABBRelatorio no, Cidadao novo) {
        if (no == null) {
            return new NoABBRelatorio(novo);
        } else if (novo.getNome().compareTo(no.getCidadao().getNome()) < 0) {
            no.esquerda = inserir(no.esquerda, novo);
        } else if (novo.getNome().compareTo(no.getCidadao().getNome()) > 0) {
            no.direita = inserir(no.direita, novo);
        }
        return no;
    }

    public void imprimir(Document document) {
        emOrdem(raiz, document);
    }

    private void emOrdem(NoABBRelatorio no, Document document) {
        if (no != null) {
            emOrdem(no.esquerda, document);
            try {
                document.add(new Paragraph("   Nome: " + no.getCidadao().getNome()));
                document.add(new Paragraph("   CPF: " + no.getCidadao().getCpf()));
                document.add(new Paragraph("   Data de Nascimento: " + no.getCidadao().getDatanasc()));

                for (Rg r : no.getCidadao().getRgGerais()) {
                    document.add(new Paragraph("   RG: " + r.getRg() + " " + r.getEstadoRG()));
                }

                document.add(new Paragraph("   Cidade: " + no.getCidadao().getOrigem().getCidade() +
                        ", Estado: " + no.getCidadao().getOrigem().getEstado()));

                document.add(new Paragraph(""));
            } catch (Exception e) {
                System.err.println("Erro ao adicionar dados ao PDF: " + e.getMessage());
            }
            emOrdem(no.direita, document);
        }
    }

    public NoABBRelatorio getRaiz() {
        return raiz;
    }
    
    
}
