
import Estrutura.EstruturaAVL;
import Persistencia.GerenciadorDeDados;
import Telas.TelaInicial;
import Timer.TempoDeExecucao;
import javax.swing.JOptionPane;
/**
 *
 * @author ra
 */
public class Program {
    
    public static void main(String[] args) { 
       EstruturaAVL arvoreAvl = new EstruturaAVL();
        GerenciadorDeDados gerenciadorDeDados = new GerenciadorDeDados(arvoreAvl);
        TempoDeExecucao tempo = new TempoDeExecucao();
        // Começa a calcular o tempo
        tempo.iniciar();
        gerenciadorDeDados.verificarExistenciaArquivo(arvoreAvl);
        // Termina de calcular o tempo
        tempo.finalizar();
        long tempoDeExecucao = tempo.obterTempoEmMilissegundos();
        JOptionPane.showMessageDialog(null, "Tempo de execução: " + tempoDeExecucao + " Milissegundos", "Informação", JOptionPane.INFORMATION_MESSAGE);
        new TelaInicial(arvoreAvl).setVisible(true);
        arvoreAvl.imprimir();
    }
        
}
