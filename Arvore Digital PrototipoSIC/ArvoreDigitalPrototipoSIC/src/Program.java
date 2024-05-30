
import Estrutura.ArvoreMAria;
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
       ArvoreMAria listaCadastros = new ArvoreMAria();
       GerenciadorDeDados gerenciadorDeDados = new GerenciadorDeDados(listaCadastros);        
       gerenciadorDeDados.verificarExistenciaArquivo();
       new TelaInicial(listaCadastros).setVisible(true);
       
       TempoDeExecucao tempo = new TempoDeExecucao();
            // Começa a calcular o tempo
            tempo.iniciar();
        listaCadastros.imprimirArvore();
        tempo.finalizar();
        long tempoDeExecucao = tempo.obterTempoEmMilissegundos();
        JOptionPane.showMessageDialog(null, "Tempo de execução: " + tempoDeExecucao + " Milissegundos", "Informação", JOptionPane.INFORMATION_MESSAGE);
        
    }
        
}
