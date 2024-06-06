
import Estrutura.*;
import Persistencia.GerenciadorDeDados;
import Telas.TelaInicial;

/**
 *
 * @author Gustavo
 */
public class Program {
    
    public static void main(String[] args) {
        ArvoreB arvoreB = new ArvoreB(5);
        
        GerenciadorDeDados.verificarExistenciaArquivo(arvoreB);
        new TelaInicial(arvoreB).setVisible(true);
        arvoreB.imprimir();    
    }

        
}
