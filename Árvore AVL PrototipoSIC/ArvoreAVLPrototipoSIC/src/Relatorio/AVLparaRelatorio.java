/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
    EstruturaAVL metodosAvl = new EstruturaAVL();
    private NoAVL raiz;
    
    public AVLparaRelatorio() {
        this.raiz = null;
    }
    
    public void inserir(Cidadao novo) {
        raiz = inserirRelatorioAVL(raiz, novo);
    }

    private NoAVL inserirRelatorioAVL(NoAVL no, Cidadao novo) {
        if (no == null) {
            return new NoAVL(novo);
        }
        if (novo.getNome().compareTo(no.getCidadao().getNome()) < 0) {
            no.setEsquerdo(inserirRelatorioAVL(no.getEsquerdo(), novo));
        } else if (novo.getNome().compareTo(no.getCidadao().getNome()) > 0) {
            no.setDireito(inserirRelatorioAVL(no.getDireito(), novo));
        }
        
        no.setAltura(Math.max(metodosAvl.alturaNo(no.getEsquerdo()), metodosAvl.alturaNo(no.getDireito())) + 1);
        no = metodosAvl.balanceia(no);

        return no;
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
