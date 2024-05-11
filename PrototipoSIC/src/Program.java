
import Estrutura.ListaEncadeada;
import Importacoes.JsonImporter;
import Individuo.Cidadao;
import Individuo.Naturalidade;
import Individuo.Rg;
import Persistencia.GerenciadorDeDados;
import static Persistencia.GerenciadorDeDados.verificarExistenciaArquivo;
import Relatorio.Relatorio;
import Telas.TelaInicial;
import Timer.TempoDeExecucao;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ra
 */
public class Program {
    
    public static void main(String[] args) {     
       ListaEncadeada listaCadastros = new ListaEncadeada();
       GerenciadorDeDados gerenciadorDeDados = new GerenciadorDeDados(listaCadastros);        
       gerenciadorDeDados.verificarExistenciaArquivo(listaCadastros);
        new TelaInicial(listaCadastros).setVisible(true);    
        
        listaCadastros.imprimirLista();
    }
        
}
