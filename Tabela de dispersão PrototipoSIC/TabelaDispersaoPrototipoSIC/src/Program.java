

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
       GerenciadorDeDados gerenciadorDeDados = new GerenciadorDeDados();        
       gerenciadorDeDados.verificarExistenciaArquivo(tabelaHash);
       new TelaInicial(tabelaHash).setVisible(true);    
               
    }
        
}
