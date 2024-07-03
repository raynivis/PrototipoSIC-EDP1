package Relatorio;

import Estrutura.EstruturaAVL;
import Estrutura.NoAVL;
import Individuo.Cidadao;
import Individuo.Rg;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

/**
 *
 * @author maymi
 */
public class AVLparaRelatorio {
    private NoAVL raiz;
    EstruturaAVL avl = new EstruturaAVL();
    private static boolean status;
    
    public AVLparaRelatorio() {
        this.raiz = null;
    }
    
    public void inserir(Cidadao cidadaoRelat) {
        raiz = inserirRelatorioAVL(raiz, cidadaoRelat);
    }

    private NoAVL inserirRelatorioAVL(NoAVL no, Cidadao cidadaoRelat) { /* insere*/
        if(no == null) {
            status = true;
            return new NoAVL(cidadaoRelat);
        }else {    
            if (cidadaoRelat.getNome().compareTo(no.getCidadao().getNome()) <= 0) { /* se o nome for menor ou igual */
                no.setEsquerdo(inserirRelatorioAVL(no.getEsquerdo(), cidadaoRelat));
                if(status == true) {
                    switch (no.getFb()) {
                        case 1 : 
                            no.setFb(0);
                            status = false;
                            break; 
                        case 0 : 
                            no.setFb(-1); 
                            break;
                        case -1 : 
                            no = avl.rotacaoDireita(no);
                            break;
                    }
                }
            } else {
                no.setDireito(inserirRelatorioAVL(no.getDireito(), cidadaoRelat));
                if(status == true) {
                    switch (no.getFb()) {
                        case -1:
                            no.setFb(0);
                            status = false;
                            break;
                        case 0:
                            no.setFb(1); 
                            break;
                        case 1:
                            no = avl.rotacaoEsquerda(no);
                            break;
                    }
                }
            }
            return no;
        }
    }
    
    public void imprimir(Document document) {
        emOrdem(raiz, document);
    }

    private void emOrdem(NoAVL raiz, Document document) {
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

    public NoAVL getRaiz() {
        return raiz;
    }
}
