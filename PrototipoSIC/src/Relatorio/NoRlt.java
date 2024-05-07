/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Relatorio;

import Individuo.Cidadao;

/**
 *
 * @author nivis
 */
public class NoRlt {  
    private Cidadao chave;
    public NoRlt prox;

    public NoRlt(Cidadao chave) {
        this.chave = chave;
        this.prox = null;
    }

    public void setChave(Cidadao chave) {
        this.chave = chave;
    }
     
    public Cidadao getCidadao() {
        return chave;
    }

}
