

import Estrutura.TabelaHash;
import Persistencia.GerenciadorDeDados;
import Telas.TelaInicial;

/**
 *
 * @author ra
 */
public class Program {
    
    public static void main(String[] args) {     
       TabelaHash tabelaHash = new TabelaHash();      
       GerenciadorDeDados.verificarExistenciaArquivo(tabelaHash);
       new TelaInicial(tabelaHash).setVisible(true);    
               
    }
        
}
