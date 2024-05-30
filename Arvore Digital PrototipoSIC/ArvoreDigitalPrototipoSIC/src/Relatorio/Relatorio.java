package Relatorio;

import Estrutura.ArvoreMAria;
import Estrutura.NoArvore;
import Timer.TempoDeExecucao;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import javax.swing.JOptionPane;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author nivis
 */
public class Relatorio {
    
    /*estrutura:*/
    private ArvoreMAria estruturaDeDados;
    /*lista dos estados*/
    private ListaRelatorio[] estado;
    

    public Relatorio(ArvoreMAria estruturaDeDados, ListaRelatorio[] estados, int faixaetaria1, int faixaetaria2) {
        this.estruturaDeDados = estruturaDeDados;
        this.estado = estados;      
        processarArvore(this.estruturaDeDados.getRaiz(), faixaetaria1, faixaetaria2);
    }
    
    private void processarArvore(NoArvore no, int faixaetaria1, int faixaetaria2) {
        if (no == null) {
            return;
        }
        
        if (no.getCidadao() != null) {
            String data = no.getCidadao().getDatanasc();
            String anoTexto = data.substring(data.length() - 4);
            int ano = Integer.parseInt(anoTexto);
            int idade = LocalDate.now().getYear() - ano;
            if (idade >= faixaetaria1 && idade <= faixaetaria2) {
                String naturalidadeEstado = no.getCidadao().getOrigem().getEstado();
                int j = EspalhamentoEstado.retornaIndiceEstado(naturalidadeEstado);
                estado[j].inserirNaLista(no.getCidadao());
            }
        }

        for (NoArvore filho : no.getFilhos()) {
            processarArvore(filho, faixaetaria1, faixaetaria2);
        }
    }

    public void imprimirRelatorio(int faixaetaria1, int faixaetaria2) {
        try {
            TempoDeExecucao tempo = new TempoDeExecucao();
            // Começa a calcular o tempo
            tempo.iniciar();
            String nomeArquivo = "RelatorioPorFaixaEtaria_" + faixaetaria1 + "_a_" + faixaetaria2 + "_anos.pdf";
            PdfWriter writer = new PdfWriter(nomeArquivo);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            for (int i = 0; i < 27; i++) {
                if (estado[i].getCabeca() != null) {
                    EnumSiglaEstado sigla = EnumSiglaEstado.valueOf(estado[i].getCabeca().getCidadao().getOrigem().getEstado());
                    String estadoNome = sigla.getNomeEstado();
                    Color color = new DeviceRgb(0, 0, 0); // Cor preta
                    // Cria um texto em negrito
                    Text boldText = new Text(estadoNome + ": ").setBold().setFontColor(color);
                    // Adiciona o texto em negrito ao documento
                    document.add(new Paragraph(boldText));
                    estado[i].imprimirLista(document);
                }
            }

            document.close();
            tempo.finalizar();
            JOptionPane.showMessageDialog(null, "PDF gerado com sucesso.", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
            
            long tempoDeExecucao = tempo.obterTempoEmMilissegundos();
            JOptionPane.showMessageDialog(null, "Tempo de execução: " + tempoDeExecucao + " Milissegundos", "Informação", JOptionPane.INFORMATION_MESSAGE);
        } catch (FileNotFoundException e) {
            System.err.println("Erro ao criar o arquivo PDF: " + e.getMessage());
        }
    }
}