package Relatorio;
import Individuo.Cidadao;
import Individuo.Rg;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
/**
 *
 * @author nivis
 */
public class ListaRelatorio {
    private NoRlt cabeca; /*cabeca da lista*/

    public ListaRelatorio() {
        this.cabeca = null; /*iniciando a lista vazia*/
    }
    
    public void inserirNaLista(Cidadao novo) {
       NoRlt novoNo =  new NoRlt(novo);
       if( cabeca == null || novo.getNome().compareTo(cabeca.getCidadao().getNome()) < 0) {
           novoNo.prox = cabeca;
           cabeca = novoNo;
       } else {
           NoRlt i = cabeca;
           while(i.prox != null && novo.getNome().compareTo(i.prox.getCidadao().getNome()) > 0) {
            i = i.prox;
           }
           novoNo.prox = i.prox;
           i.prox = novoNo;
       }
    }
    
    public void imprimirLista(Document document) {
        try {
            NoRlt i = cabeca;

            while (i != null) {
                document.add(new Paragraph("   Nome: " + i.getCidadao().getNome()));
                document.add(new Paragraph("   CPF: " + i.getCidadao().getCpf()));
                document.add(new Paragraph("   Data de Nascimento: " + i.getCidadao().getDatanasc()));

                for (Rg r : i.getCidadao().getRgGerais()) {
                    document.add(new Paragraph("   RG: " + r.getRg() + " " + r.getEstadoRG()));
                }

                document.add(new Paragraph("   Cidade: " + i.getCidadao().getOrigem().getCidade() +
                        ", Estado: " + i.getCidadao().getOrigem().getEstado()));

                i = i.prox;
                document.add(new Paragraph(""));
            }
            
        } catch (Exception e) {
            System.err.println("Erro ao adicionar dados ao PDF: " + e.getMessage());
        }
        
    }
    
    public NoRlt getCabeca() {
        return cabeca;
    }

    
    
    
}
