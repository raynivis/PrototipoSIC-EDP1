package Relatorio;

import Individuo.Cidadao;
import java.util.ArrayList;
import java.util.List;
import Individuo.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
/**
 *
 * @author Gustavo
 */
public class NoArvoreBRelatorio {  
    private List<Cidadao> chaves;
    private List<NoArvoreBRelatorio> filhos;
    private int grau;
    
    public NoArvoreBRelatorio(int grau) {
        this.grau = grau;
        chaves = new ArrayList<>();
        filhos = new ArrayList<>();
    }   
    public void inserir(Cidadao cidadao) {
        if (chaves.size() < grau) {
            chaves.add(cidadao);
            chaves.sort((c1, c2) -> c1.getNome().compareTo(c2.getNome()));
        } else {
            int i = 0;
            while (i < chaves.size() && chaves.get(i).getNome().compareTo(cidadao.getNome()) < 0) {
                i++;
            }
            chaves.add(i, cidadao);
            if (filhos.size() <= i) {
                filhos.add(new NoArvoreBRelatorio(grau));
            }
            filhos.get(i).inserir(cidadao);
        }
    }

    public void imprimir(Document document) {
        for (Cidadao cidadao : chaves) {
            try {
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
        for (NoArvoreBRelatorio filho : filhos) {
            filho.imprimir(document);
        }
    }

    public List<Cidadao> getChaves() {
        return chaves;
    }

    public List<NoArvoreBRelatorio> getFilhos() {
        return filhos;
    }

    public int getGrau() {
        return grau;
    }
    
    
    
    

}
