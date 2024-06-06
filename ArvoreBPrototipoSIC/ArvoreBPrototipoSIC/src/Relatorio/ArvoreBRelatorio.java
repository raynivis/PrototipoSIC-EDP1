package Relatorio;
import Individuo.Cidadao;
import Individuo.Rg;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
/**
 *
 * @author Gustavo
 */
public class ArvoreBRelatorio {
    private NoArvoreBRelatorio raiz;

    public ArvoreBRelatorio(int grau) {
        raiz = new NoArvoreBRelatorio(grau);
    }

    public void inserir(Cidadao cidadao) {
        raiz.inserir(cidadao);
    }

    public void imprimir(Document document) {
        raiz.imprimir(document);
    }

    public NoArvoreBRelatorio getRaiz() {
        return raiz;
    }
    
    
}
