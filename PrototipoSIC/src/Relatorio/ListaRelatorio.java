package Relatorio;
import Individuo.Cidadao;
import Individuo.Rg;

/**
 *
 * @author nivis
 */
public class ListaRelatorio {
    private NoRlt cabeca; /*cabeca da lista*/

    public ListaRelatorio() {
        this.cabeca = null; /*iniciando a lista vazia*/
    }
    
    public void inserirNaLista(Cidadao novo) {
       NoRlt novoNo =  new NoRlt(novo);
       if( cabeca == null || novo.getNome().compareTo(cabeca.getCidadao().getNome()) < 0) {
           novoNo.prox = cabeca;
           cabeca = novoNo;
       } else {
           NoRlt i = cabeca;
           while(i.prox != null && novo.getNome().compareTo(i.prox.getCidadao().getNome()) > 0) {
            i = i.prox;
           }
           novoNo.prox = i.prox;
           i.prox = novoNo;
       }
    }
    
    public void imprimirLista() { 
        NoRlt i = cabeca;
        
        if(i != null){
            EnumSiglaEstado sigla = EnumSiglaEstado.valueOf(i.getCidadao().getOrigem().getEstado());
            System.out.println(sigla.getNomeEstado()+ ": ");
        }
        while (i != null) {
            System.out.println("   " + i.getCidadao().getNome() );
            System.out.println("   " + i.getCidadao().getCpf() );
            System.out.println("   " + i.getCidadao().getDatanasc());
            for(Rg r : i.getCidadao().getRgGerais()){
                System.out.print("   " + r.getRg() + " ");
                System.out.println(r.getEstadoRG());
            }
            System.out.print("   " + i.getCidadao().getOrigem().getCidade() + " ");
            System.out.println(i.getCidadao().getOrigem().getEstado());
            i = i.prox;
            System.out.println("");
        }
    }
    
    
    
}
