
import Estrutura.*;
import Persistencia.GerenciadorDeDados;
import Telas.TelaInicial;
import Timer.TempoDeExecucao;
import javax.swing.JOptionPane;
/**
 *
 * @author Gustavo
 */
public class Program {
    
    public static void main(String[] args) {
        ABB abb = new ABB();
        GerenciadorDeDados gerenciadorDeDados = new GerenciadorDeDados(abb);
        TempoDeExecucao tempo = new TempoDeExecucao();
        // Começa a calcular o tempo
        tempo.iniciar();
        gerenciadorDeDados.verificarExistenciaArquivo(abb);
        // Termina de calcular o tempo
        tempo.finalizar();
        long tempoDeExecucao = tempo.obterTempoEmMilissegundos();
        JOptionPane.showMessageDialog(null, "Tempo de execução: " + tempoDeExecucao + " Milissegundos", "Informação", JOptionPane.INFORMATION_MESSAGE);
        new TelaInicial(abb).setVisible(true);
        abb.imprimir();    
    }

        
}
