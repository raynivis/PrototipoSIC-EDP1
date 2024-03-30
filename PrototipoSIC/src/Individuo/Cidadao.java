/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Individuo;

/**
 *
 * @author ra
 */
public class Cidadao {
    private String nome;
    private String datanasc;
    private String cpf;
    private Rg[] rsgerais;
    private int qntrg;
    private Naturalidade origem;

    public Cidadao(String nome, String datanasc, String cpf, Rg[] rsgerais, int qntrg, Naturalidade origem) {
        this.nome = nome;
        this.datanasc = datanasc;
        this.cpf = cpf;
        this.rsgerais = rsgerais;
        this.qntrg = qntrg;
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

    public Rg[] getRsgerais() {
        return rsgerais;
    }

    public void setRsgerais(Rg[] rsgerais) {
        this.rsgerais = rsgerais;
    }

    public int getQntrg() {
        return qntrg;
    }

    public void setQntrg(int qntrg) {
        this.qntrg = qntrg;
    }

    public Naturalidade getOrigem() {
        return origem;
    }

    public void setOrigem(Naturalidade origem) {
        this.origem = origem;
    }
    
    
}
