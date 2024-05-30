package Individuo;

import java.util.List;

/**
 *
 * @author ra
 */
public class Cidadao {
    private String nome;
    private String datanasc;
    private String cpf;
    private List<Rg> rgGerais; /*mudei aqui*/
    private Naturalidade origem;

    public Cidadao(String nome, String datanasc, String cpf, List<Rg> rgGerais, Naturalidade origem) {
        this.nome = nome;
        this.datanasc = datanasc;
        this.cpf = cpf;
        this.rgGerais = rgGerais;
        this.origem = origem;
    }

    
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDatanasc() {
        return datanasc;
    }

    public void setDatanasc(String datanasc) {
        this.datanasc = datanasc;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Rg> getRgGerais() {
        return rgGerais;
    }

    public void setRgGerais(List<Rg> rgGerais) {
        this.rgGerais = rgGerais;
    }

    public Naturalidade getOrigem() {
        return origem;
    }

    public void setOrigem(Naturalidade origem) {
        this.origem = origem;
    }
    
    
    
}
