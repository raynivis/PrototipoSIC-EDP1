
import Estrutura.EstruturaRBT;
import Persistencia.GerenciadorDeDados;
import Telas.TelaInicial;

/**
 *
 * @author ra
 */
public class Program {
    
    public static void main(String[] args) { 
        EstruturaRBT arvoreRB = new EstruturaRBT();
        
        GerenciadorDeDados.verificarExistenciaArquivo(arvoreRB);
      
        new TelaInicial(arvoreRB).setVisible(true);
        arvoreRB.imprimir();
    }
        
}
