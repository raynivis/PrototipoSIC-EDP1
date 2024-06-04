package Estrutura;
import Individuo.*;
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
    
    public void emOrdem(Consumer<NoABB> consumer) {
        emOrdem(raiz, consumer);
    }

    private void emOrdem(NoABB no, Consumer<NoABB> consumer) {
        if (no != null) {
            emOrdem(no.esquerda, consumer);
            consumer.accept(no);
            emOrdem(no.direita, consumer);
        }
    }

    public NoABB getRaiz() {
        return raiz;
    }
  
}

