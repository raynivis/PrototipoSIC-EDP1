package Relatorio;

import Estrutura.EstruturaAVL;
import Estrutura.NoAVL;
import Timer.TempoDeExecucao;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import java.io.FileNotFoundException;
import javax.swing.JOptionPane;
/**
 *
 * @author nivis
 */
public class Relatorio {
    private AVLparaRelatorio[] estado;

    public Relatorio(EstruturaAVL estruturaDeDados, AVLparaRelatorio[] estados, int faixaetaria1, int faixaetaria2) {
        this.estado = estados;

        percorrerAVLInOrder(estruturaDeDados.getRaiz(), faixaetaria1, faixaetaria2);
    }

    private void percorrerAVLInOrder(NoAVL no, int faixaetaria1, int faixaetaria2) {
        if (no == null) {
            return;
        }

        percorrerAVLInOrder(no.getEsquerdo(), faixaetaria1, faixaetaria2);

        String data = no.getCidadao().getDatanasc();
        String anoTexto = data.substring(data.length() - 4, data.length());
        int ano = Integer.parseInt(anoTexto);
        if ((2024 - ano) >= faixaetaria1 && (2024 - ano) <= faixaetaria2) {
            String naturalidadeEstado = no.getCidadao().getOrigem().getEstado();
            int j = EspalhamentoEstado.retornaIndiceEstado(naturalidadeEstado);
            estado[j].inserir(no.getCidadao());
        }

        percorrerAVLInOrder(no.getDireito(), faixaetaria1, faixaetaria2);
    }
    
    public void imprimirRelatorio(int faixaetaria1, int faixaetaria2) {
        try {
            TempoDeExecucao tempo = new TempoDeExecucao();
            tempo.iniciar();
            String nomeArquivo = "RelatorioPorFaixaEtaria_" + faixaetaria1 + "_a_" + faixaetaria2 + "_anos.pdf";
            PdfWriter writer = new PdfWriter(nomeArquivo);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            for (int i = 0; i < 27; i++) {
                if (estado[i].getRaiz()!= null) {
                    EnumSiglaEstado sigla = EnumSiglaEstado.valueOf(estado[i].getRaiz().getCidadao().getOrigem().getEstado());
                    String estadoNome = sigla.getNomeEstado();
                    Color color = new DeviceRgb(0, 0, 0); // Cor preta
                    Text boldText = new Text(estadoNome + ": ").setBold().setFontColor(color);
                    document.add(new Paragraph(boldText));
                    estado[i].imprimir(document);
                }
            }

            document.close();
            JOptionPane.showMessageDialog(null, "PDF gerado com sucesso.", "Sucesso!", 1);
            tempo.finalizar();
            long tempoDeExecucao = tempo.obterTempoEmMilissegundos();
            JOptionPane.showMessageDialog(null, "Tempo de execução: " + tempoDeExecucao + " Milissegundos", "Informação", JOptionPane.INFORMATION_MESSAGE);
        } catch (FileNotFoundException e) {
            System.err.println("Erro ao criar o arquivo PDF: " + e.getMessage());
        }
    }
}
