package Estrutura;
import Individuo.*;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
/**
 *
 * @author Gustavo
 */
public class ArvoreB {
    private NoArvoreB raiz;

    public ArvoreB(int grau) {
        raiz = new NoArvoreB(grau);
    }

    public void inserir(Cidadao cidadao) {
        raiz.inserir(cidadao);
    }

    public Cidadao buscar(String cpf) {
        return raiz.buscar(cpf);
    }
    
    public void imprimir() {
        raiz.imprimirRecursivamente(raiz);
    }
    
    public void ordenarChaves(List<Cidadao> chaves) {
        for (int i = 0; i < chaves.size(); i++) {
            for (int j = i + 1; j < chaves.size(); j++) {
                if (chaves.get(i).getCpf().compareTo(chaves.get(j).getCpf()) > 0) {
                    Cidadao temp = chaves.get(i);
                    chaves.set(i, chaves.get(j));
                    chaves.set(j, temp);
                }
            }
        }
    }
    

    public NoArvoreB getRaiz() {
        return raiz;
    }
}
