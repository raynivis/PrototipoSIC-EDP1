
import Estrutura.ArvoreMAria;
import Persistencia.GerenciadorDeDados;
import Telas.TelaInicial;

/**
 *
 * @author ra
 */
public class Program {
    
    public static void main(String[] args) {     
       ArvoreMAria ad = new ArvoreMAria();
       GerenciadorDeDados gerenciadorDeDados = new GerenciadorDeDados(ad);        
       gerenciadorDeDados.verificarExistenciaArquivo();
       new TelaInicial(ad).setVisible(true);
           
       ad.imprimirArvore();
        
    }
        
}
