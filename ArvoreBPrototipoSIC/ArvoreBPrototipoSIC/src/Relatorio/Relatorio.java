package Relatorio;

import Estrutura.ArvoreB;
import Estrutura.NoB;
import Individuo.Cidadao;
import Timer.TempoDeExecucao;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import java.io.FileNotFoundException;
import javax.swing.JOptionPane;

public class Relatorio {
    private ArvoreBRelatorio[] estado;
    private ArvoreB arvoreB;

    public Relatorio(ArvoreB arvoreB, ArvoreBRelatorio[] estados) {
        this.arvoreB = arvoreB;
        this.estado = estados;
    }

    public void percorrerArvoreBInOrder(NoB no, int faixaetaria1, int faixaetaria2) {
        if (no == null) {
            return;
        }

        for (Cidadao cidadao : no.getChaves()) {
            String data = cidadao.getDatanasc();
            String anoTexto = data.substring(data.length() - 4);
            int ano = Integer.parseInt(anoTexto);
            if ((2024 - ano) >= faixaetaria1 && (2024 - ano) <= faixaetaria2) {
                String naturalidadeEstado = cidadao.getOrigem().getEstado();
                int j = EspalhamentoEstado.retornaIndiceEstado(naturalidadeEstado);
                estado[j].inserir(cidadao);
            }
        }

        for (NoB filho : no.getFilhos()) {
            percorrerArvoreBInOrder(filho, faixaetaria1, faixaetaria2);
        }
    }

    public void imprimirRelatorio(int faixaetaria1, int faixaetaria2) {
        percorrerArvoreBInOrder(arvoreB.getRaiz(), faixaetaria1, faixaetaria2);
        try {
            TempoDeExecucao tempo = new TempoDeExecucao();
            tempo.iniciar();
            String nomeArquivo = "RelatorioPorFaixaEtaria_" + faixaetaria1 + "_a_" + faixaetaria2 + "_anos.pdf";
            PdfWriter writer = new PdfWriter(nomeArquivo);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            for (int i = 0; i < 27; i++) {
                if (estado[i].getRaiz() != null) {
                    Cidadao primeiroCidadao = estado[i].getRaiz().getChaves().get(0);
                    EnumSiglaEstado sigla = EnumSiglaEstado.valueOf(primeiroCidadao.getOrigem().getEstado());
                    String estadoNome = sigla.getNomeEstado();
                    Color color = new DeviceRgb(0, 0, 0); // Cor preta
                    Text boldText = new Text(estadoNome + ": ").setBold().setFontColor(color);
                    document.add(new Paragraph(boldText));
                    estado[i].imprimir(document);
                }
            }

            document.close();
            tempo.finalizar();
            JOptionPane.showMessageDialog(null, "PDF gerado com sucesso.", "Sucesso!", 1);

            long tempoDeExecucao = tempo.obterTempoEmMilissegundos();
            JOptionPane.showMessageDialog(null, "Tempo de execução: " + tempoDeExecucao + " Milissegundos", "Relatorio", JOptionPane.INFORMATION_MESSAGE);
        } catch (FileNotFoundException e) {
            System.err.println("Erro ao criar o arquivo PDF: " + e.getMessage());
        }
    }
}
