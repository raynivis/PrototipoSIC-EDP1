package Estrutura;
import Individuo.*;
import java.util.List;
import java.util.function.Consumer;
/**
 *
 * @author Gustavo
 */
public class ABB {
    private NoABB raiz;

    public ABB() {
        this.raiz = null;
    }

    public void inserir(Cidadao cidadao) {
        raiz = inserirRecursivamente(raiz, cidadao);
    }

    private NoABB inserirRecursivamente(NoABB no, Cidadao cidadao) {
        if (no == null) {
            return new NoABB(cidadao);
        }
        if(buscar(cidadao.getCpf()) == null){
            if (cidadao.getCpf().compareTo(no.getCidadao().getCpf()) <= 0) {
                no.esquerda = inserirRecursivamente(no.esquerda, cidadao);
            } else {
                no.direita = inserirRecursivamente(no.direita, cidadao);
            }
            return no;
        }
        else{
            // Verifica se o RG já existe na lista de RGs gerais
            Cidadao cidadaoExistente = buscar(cidadao.getCpf());
            List<Rg> rgGerais = cidadaoExistente.getRgGerais();
            boolean rgJaExiste = false;
            for (Rg rg : rgGerais) {
                if (rg.getRg().equals(cidadao.getRgGerais().get(0).getRg()) && rg.getEstadoRG().equals(cidadao.getRgGerais().get(0).getEstadoRG())) {
                    rgJaExiste = true;
                    break;
                }
            }
            if (!rgJaExiste) {
                // Adiciona o novo RG à lista de RGs gerais
                cidadaoExistente.getRgGerais().addAll(cidadao.getRgGerais());
            }
            return no;
        }
    }
    
    public void inserirRapido(Cidadao cidadao) {
        raiz = inserirRecursivamenteRapido(raiz, cidadao);
    }

    private NoABB inserirRecursivamenteRapido(NoABB no, Cidadao cidadao) {
        if (no == null) {
            return new NoABB(cidadao);
        }
        if (cidadao.getCpf().compareTo(no.getCidadao().getCpf()) <= 0) {
            no.esquerda = inserirRecursivamenteRapido(no.esquerda, cidadao);
        } else {
            no.direita = inserirRecursivamenteRapido(no.direita, cidadao);
        }
        return no;
    }

    public Cidadao buscar(String cpf) {
        return buscar(raiz, cpf);
    }

    private Cidadao buscar(NoABB no, String cpf) {
        if (no == null || no.getCidadao().getCpf().equals(cpf)) {
            return (no != null) ? no.getCidadao() : null;
        }
        if (cpf.compareTo(no.getCidadao().getCpf()) < 0) {
            return buscar(no.esquerda, cpf);
        } else {
            return buscar(no.direita, cpf);
        }
    }
    
    public void imprimir() {
        imprimirABB(raiz);
    }
    
    public void imprimirABB(NoABB no) {
        if (no != null) {
            imprimirABB(no.getEsquerda());
            System.out.println(no.getCidadao().getNome());
            imprimirABB(no.getDireita());
        }
    
    }
    
    public void preOrdem(Consumer<NoABB> consumer) {
        preOrdem(raiz, consumer);
    }

    private void preOrdem(NoABB no, Consumer<NoABB> consumer) {
        if (no!= null) {
            consumer.accept(no); // <--- Move this line up
            preOrdem(no.esquerda, consumer);
            preOrdem(no.direita, consumer);
        }
    }

    public NoABB getRaiz() {
        return raiz;
    }
  
}

