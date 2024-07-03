package Estrutura;

import Individuo.*;
import java.util.function.Consumer;

/**
 *
 * @author maymi
 */
public class EstruturaRBT {
    private NoRB raiz;
    private int contaCidadao;
    private int rtse;
    private int rtsd;
    private int rtde;
    private int rtdd;
    private int pintar;
    private static boolean status;
    
    public EstruturaRBT() {
        this.raiz = null;
        contaCidadao = 0;
        pintar = 0;
        rtdd = 0;
        rtse = 0;
        rtsd = 0;
        rtde = 0;
        status = false;
    }

    // Rotação à esquerda
    private void rotacaoEsquerda(NoRB no) {
        NoRB direito = no.getDireito();
        no.setDireito(direito.getEsquerdo());
        if (direito.getEsquerdo() != null) {
            direito.getEsquerdo().setPai(no);
        }
        direito.setPai(no.getPai());
        if (no.getPai() == null) {
            raiz = direito;
        } else if (no == no.getPai().getEsquerdo()) {
            no.getPai().setEsquerdo(direito);
        } else {
            no.getPai().setDireito(direito);
        }
        direito.setEsquerdo(no);
        no.setPai(direito);
    }

    // Rotação à direita
    private void rotacaoDireita(NoRB no) {
        NoRB esquerdo = no.getEsquerdo();
        no.setEsquerdo(esquerdo.getDireito());
        if (esquerdo.getDireito() != null) {
            esquerdo.getDireito().setPai(no);
        }
        esquerdo.setPai(no.getPai());
        if (no.getPai() == null) {
            raiz = esquerdo;
        } else if (no == no.getPai().getDireito()) {
            no.getPai().setDireito(esquerdo);
        } else {
            no.getPai().setEsquerdo(esquerdo);
        }
        esquerdo.setDireito(no);
        no.setPai(esquerdo);
    }

    // Inserir um novo nó na árvore Red-Black
    public void inserirRBT(Cidadao cidadao) {
        NoRB novoNo = new NoRB(cidadao);
        NoRB y = null;
        NoRB x = raiz;
        while (x != null) {
            y = x;
            if (cidadao.getCpf().compareTo(x.getCidadao().getCpf()) < 0) {
                x = x.getEsquerdo();
            } else if (cidadao.getCpf().compareTo(x.getCidadao().getCpf()) > 0) {
                x = x.getDireito();
            } else {
                Cidadao cidadaoExistente = buscarRBT(cidadao.getCpf());
                if (cidadaoExistente != null) {
                    boolean substituir = false;
                    for (Rg r : cidadaoExistente.getRgGerais()) {
                        if (cidadao.getRgGerais().get(0).getEstadoRG().equals(r.getEstadoRG())) {
                            r.setRg(cidadao.getRgGerais().get(0).getRg());
                            substituir = true;
                        }
                    }
                    if (!substituir) {
                        cidadaoExistente.getRgGerais().add(cidadao.getRgGerais().get(0));
                    }
                }
                return;
            }
        }
        contaCidadao++;
        novoNo.setPai(y);
        if (y == null) {
            raiz = novoNo;
        } else if (cidadao.getCpf().compareTo(y.getCidadao().getCpf()) < 0) {
            y.setEsquerdo(novoNo);
        } else {
            y.setDireito(novoNo);
        }
        fixarInsercao(novoNo);
    }
    
    // Inserir um novo nó na árvore Red-Black
    public void inserirRBTRapido(Cidadao cidadao) {
        NoRB novoNo = new NoRB(cidadao);
        NoRB y = null;
        NoRB x = raiz;
        while (x != null) {
            y = x;
            if (cidadao.getCpf().compareTo(x.getCidadao().getCpf()) < 0) {
                x = x.getEsquerdo();
            } else if (cidadao.getCpf().compareTo(x.getCidadao().getCpf()) > 0) {
                x = x.getDireito();
            }
        }
        contaCidadao++;
        novoNo.setPai(y);
        if (y == null) {
            raiz = novoNo;
        } else if (cidadao.getCpf().compareTo(y.getCidadao().getCpf()) < 0) {
            y.setEsquerdo(novoNo);
        } else {
            y.setDireito(novoNo);
        }
        fixarInsercaoRapida(novoNo);
    }

    private void fixarInsercaoRapida(NoRB no) {
        while (no != null && no.getPai() != null && no.getPai().isRed()) {
            NoRB pai = no.getPai();
            NoRB avo = pai.getPai();

            // Verifica se avo não é nulo
            if (avo == null) {
                break; // Sai do loop se não há avô
            }

            if (pai == avo.getEsquerdo()) {
                NoRB tio = avo.getDireito();
                if (tio != null && tio.isRed()) {
                    pai.setRed(false);
                    tio.setRed(false);
                    avo.setRed(true);
                    pintar++;
                    no = avo;
                } else {
                    if (no == pai.getDireito()) {
                        no = pai;
                        rotacaoEsquerda(no);
                        rtdd++;
                    } else {
                        rtse++;
                    }
                    pai.setRed(false);
                    avo.setRed(true);
                    rotacaoDireita(avo);
                }
            } else {
                NoRB tio = avo.getEsquerdo();
                if (tio != null && tio.isRed()) {
                    pai.setRed(false);
                    tio.setRed(false);
                    avo.setRed(true);
                    pintar++;
                    no = avo;
                } else {
                    if (no == pai.getEsquerdo()) {
                        no = pai;
                        rotacaoDireita(no);
                        rtde++;
                    } else {
                        rtsd++;
                    }
                    pai.setRed(false);
                    avo.setRed(true);
                    rotacaoEsquerda(avo);
                }
            }
        }
        if (raiz != null) {
            raiz.setRed(false);
        }
    }


    // Ajustar a árvore após inserção
    private void fixarInsercao(NoRB no) {
        while (no != raiz && no.getPai().isRed()) {
            if (no.getPai() == no.getPai().getPai().getEsquerdo()) {
                NoRB tio = no.getPai().getPai().getDireito();
                if (tio != null && tio.isRed()) {
                    no.getPai().setRed(false);
                    tio.setRed(false);
                    no.getPai().getPai().setRed(true);
                    pintar++;
                    no = no.getPai().getPai();
                } else {
                    if (no == no.getPai().getDireito()) {
                        no = no.getPai();
                        rotacaoEsquerda(no);
                        rtdd++;
                    }
                    else{
                        rtsd++;
                    }
                    no.getPai().setRed(false);
                    no.getPai().getPai().setRed(true);
                    rotacaoDireita(no.getPai().getPai());
                }
            } else {
                NoRB tio = no.getPai().getPai().getEsquerdo();
                if (tio != null && tio.isRed()) {
                    no.getPai().setRed(false);
                    tio.setRed(false);
                    no.getPai().getPai().setRed(true);
                    pintar++;
                    no = no.getPai().getPai();
                } else {
                    if (no == no.getPai().getEsquerdo()) {
                        no = no.getPai();
                        rotacaoDireita(no);
                        rtde++;
                    }
                    else{
                        rtse++;
                    }
                    no.getPai().setRed(false);
                    no.getPai().getPai().setRed(true);
                    rotacaoEsquerda(no.getPai().getPai());
                }
            }
        }
        raiz.setRed(false);
    }

    // Busca na árvore Red-Black
    public Cidadao buscarRBT(String cpf) {
        return buscarRBT(raiz, cpf);
    }

    private Cidadao buscarRBT(NoRB no, String cpf) {
        while (no != null) {
            if (cpf.equals(no.getCidadao().getCpf())) {
                return no.getCidadao();
            } else if (cpf.compareTo(no.getCidadao().getCpf()) < 0) {
                no = no.getEsquerdo();
            } else {
                no = no.getDireito();
            }
        }
        return null;
    }

    // Métodos de impressão
    public void preOrdem(Consumer<NoRB> consumer) {
        preOrdem(raiz, consumer);
    }

    private void preOrdem(NoRB raiz, Consumer<NoRB> consumer) {
        if (raiz != null) {
            consumer.accept(raiz);
            preOrdem(raiz.getEsquerdo(), consumer);
            preOrdem(raiz.getDireito(), consumer);
        }
    }

    public void imprimir() {
        imprimirRBT(raiz);
        qtdCidadao();
    }

    private void imprimirRBT(NoRB raiz) {
        if (raiz != null) {
            Cidadao cidadao = raiz.getCidadao();
            imprimirRBT(raiz.getEsquerdo());
            System.out.println("  CPF: " + cidadao.getCpf() + ", Nome: " + cidadao.getNome() + ", Data Nasc: " + cidadao.getDatanasc());
            System.out.println("  RGs:");
            for (Rg rg : cidadao.getRgGerais()) {
                System.out.println("    RG: " + rg.getRg() + ", Estado: " + rg.getEstadoRG());
            }
            System.out.println("  Naturalidade: " + cidadao.getOrigem().getCidade() + " - " + cidadao.getOrigem().getEstado());
            imprimirRBT(raiz.getDireito());
        }
    }

    public NoRB getRaiz() {
        return raiz;
    }

    public void qtdCidadao(){
        System.out.println("\nQuantidade de pessoas: " + contaCidadao);
        System.out.println("\nPintar: " + pintar);
        System.out.println("\nRotação simples direita: " + rtsd);
        System.out.println("\nRotação simples esquerda: " + rtse);
        System.out.println("\nRotação dupla direita: " + rtdd);
        System.out.println("\nRotação dupla esquerda: " + rtde);
    }
}
