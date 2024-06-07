
import Estrutura.ListaEncadeada;
import Persistencia.GerenciadorDeDados;
import Telas.TelaInicial;

/**
 *
 * @author nivis
 */
public class Program {
    
    public static void main(String[] args) {     
       ListaEncadeada listaCadastros = new ListaEncadeada();
       GerenciadorDeDados.verificarExistenciaArquivo(listaCadastros);
       new TelaInicial(listaCadastros).setVisible(true);    
        
        
    }
        
}
