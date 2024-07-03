package Relatorio;
import Estrutura.NoABB;
import Individuo.Cidadao;
import Individuo.Rg;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
/**
 *
 * @author Gustavo
 */
public class ABBRelatorio {
    private NoABB raiz;

    public ABBRelatorio() {
        this.raiz = null;
    }

    public NoABB getRaiz() {
        return raiz;
    }

    public void inserir(Cidadao cidadaoRelat) {
        raiz = inserirRelatorioAVL(raiz, cidadaoRelat);
    }

    private NoABB inserirRelatorioAVL(NoABB no, Cidadao cidadaoRelat) { /* insere*/
        if(no == null) {
            return new NoABB(cidadaoRelat);
        }else {    
            if (cidadaoRelat.getNome().compareTo(no.getCidadao().getNome()) < 0) { /* se o nome for menor ou igual */
                no.setEsquerda(inserirRelatorioAVL(no.getEsquerda(), cidadaoRelat));
                
            } else {
                no.setDireita(inserirRelatorioAVL(no.getDireita(), cidadaoRelat));
                
            }
            return no;
        }
    }

    public void imprimir(Document document) {
        emOrdem(raiz, document);
    }

    private void emOrdem(NoABB no, Document document) {
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
    
}
