package Estrutura;
import Individuo.Cidadao;
import Individuo.Rg;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

/**
 *
 * @author Gustavo Cortez
 */
public class NoArvoreB {
    private List<Cidadao> chaves;
    private List<NoArvoreB> filhos;
    private int grau; // grau da árvore

    public NoArvoreB(int grau) {
        this.grau = grau;
        this.chaves = new ArrayList<>();
        this.filhos = new ArrayList<>();
    }

    public void inserir(Cidadao cidadao) {
        if (chaves.size() < grau - 1) {
            // Nó não está cheio, adiciona a chave
            chaves.add(cidadao);
            ordenarChaves(chaves);
        } else {
            // Nó está cheio, divide o nó em dois nós menores
            NoArvoreB novoNo = new NoArvoreB(grau);
            int meio = chaves.size() / 2;
            List<Cidadao> chavesDireita = chaves.subList(meio, chaves.size());
            chaves = chaves.subList(0, meio);
            novoNo.chaves.addAll(chavesDireita);
            filhos.add(novoNo);
            if (comparar(cidadao, chaves.get(0)) < 0) {
                inserirRecursivamente(this, cidadao);
            } else {
                inserirRecursivamente(novoNo, cidadao);
            }
        }
    }

    private void inserirRecursivamente(NoArvoreB no, Cidadao cidadao) {
        // Verifica se a chave já existe no nó
        if (no.chaves.contains(cidadao)) {
            // Chave já existe, não faz nada
            return;
        }
        // Verifica se o nó tem filhos
        if (no.filhos.isEmpty()) {
            // Nó não tem filhos, adiciona a chave
            no.chaves.add(cidadao);
            ordenarChaves(no.chaves);
        } else {
            if(buscar(cidadao.getCpf()) == null){
                // Nó tem filhos, segue para o filho apropriado
                int i = 0;
                while (i < no.chaves.size() && comparar(cidadao, no.chaves.get(i)) > 0) {
                    i++;
                }
                inserirRecursivamente(no.filhos.get(i), cidadao);
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
            }
        }
    }
    
    public void emOrdem(Consumer<Cidadao> consumer) {
        if (filhos.isEmpty()) {
            ordenarChaves(chaves);
            for (Cidadao cidadao : chaves) {
                consumer.accept(cidadao);
            }
        } else {
            for (int i = 0; i < filhos.size(); i++) {
                filhos.get(i).emOrdem(consumer);
            }
            ordenarChaves(chaves);
            for (Cidadao cidadao : chaves) {
                consumer.accept(cidadao);
            }
        }
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
    private int comparar(Cidadao cidadao1, Cidadao cidadao2) {
        return cidadao1.getCpf().compareTo(cidadao2.getCpf());
    }

    public Cidadao buscar(String cpf) {
        int i = 0;
        while (i < chaves.size() && cpf.compareTo(chaves.get(i).getCpf()) > 0) {
            i++;
        }
        if (i < chaves.size() && cpf.equals(chaves.get(i).getCpf())) {
            return chaves.get(i);
        } else {
            if (filhos.isEmpty()) {
                return null;
            } else {
                Cidadao cidadao = filhos.get(i).buscar(cpf);
                if (cidadao != null) {
                    return cidadao;
                } else {
                    for (int j = i + 1; j < filhos.size(); j++) {
                        cidadao = filhos.get(j).buscar(cpf);
                        if (cidadao != null) {
                            return cidadao;
                        }
                    }
                    return null;
                }
            }
        }
    }

    public List<Cidadao> getChaves() {
        return chaves;
    }
    
    public List<NoArvoreB> getFilhos() {
        return filhos;
    }

    public int getGrau() {
        return grau;
    }
    public void imprimirRecursivamente(NoArvoreB no) {
        if (no == null) {
            return;
        }

        // Imprime as chaves do nó
        System.out.print("Chaves: ");
        for (Cidadao cidadao : no.chaves) {
            System.out.print(cidadao.getNome()+ " ");
            System.out.print(cidadao.getCpf() + " ");
            System.out.println(" ");
        }
        System.out.println();

        // Imprime os filhos do nó
        for (NoArvoreB filho : no.filhos) {
            imprimirRecursivamente(filho);
        }
    }
    
}
