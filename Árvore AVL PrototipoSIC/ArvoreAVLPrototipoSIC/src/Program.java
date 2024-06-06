
import Estrutura.EstruturaAVL;
import Persistencia.GerenciadorDeDados;
import Telas.TelaInicial;

/**
 *
 * @author ra
 */
public class Program {
    
    public static void main(String[] args) { 
       EstruturaAVL arvoreAvl = new EstruturaAVL();
        
        GerenciadorDeDados.verificarExistenciaArquivo(arvoreAvl);
      
        new TelaInicial(arvoreAvl).setVisible(true);
        arvoreAvl.imprimir();
    }
        
}
