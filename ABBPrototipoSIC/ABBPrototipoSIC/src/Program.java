
import Estrutura.*;
import Importacoes.JsonImporter;
import Individuo.Cidadao;
import Individuo.Naturalidade;
import Individuo.Rg;
import Persistencia.GerenciadorDeDados;
import Relatorio.Relatorio;
import Telas.TelaInicial;
import Timer.TempoDeExecucao;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Gustavo
 */
public class Program {
    
    public static void main(String[] args) {
        ABB abb = new ABB();
        GerenciadorDeDados gerenciadorDeDados = new GerenciadorDeDados(abb);
        gerenciadorDeDados.verificarExistenciaArquivo(abb);
        abb.imprimir();
        new TelaInicial(abb).setVisible(true);
    }

        
}
