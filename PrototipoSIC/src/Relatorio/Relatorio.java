/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Relatorio;

import Estrutura.ListaEncadeada;
import Estrutura.No;

/**
 *
 * @author nivis
 */
public class Relatorio {
    
    
    /*estrutura:*/
    ListaEncadeada estruraDeDados;
    /*lista dos estados*/
    ListaRelatorio[] estado;
    

    public Relatorio(ListaEncadeada estruraDeDados, ListaRelatorio[] estados, int faixaetaria1, int faixaetaria2) {
        this.estruraDeDados = estruraDeDados;
        this.estado = estados;      
    
        No i = this.estruraDeDados.getCabeca();
        while(i != null) {
            String data = i.getCidadao().getDatanasc();
            String anoTexto = data.substring(data.length()-4, data.length());
            int ano = Integer.parseInt(anoTexto);
            if((2024-ano) >= faixaetaria1 && (2024-ano) <= faixaetaria2) {           
                String naturalidadeEstado = i.getCidadao().getOrigem().getEstado();
                int j = EspalhamentoEstado.retornaIndiceEstado(naturalidadeEstado);
                estado[j].inserirNaLista(i.getCidadao());              
            }
            i=i.prox;
        }      
    }
    
    public void imprimirRelatorio(){ 
        for(int i = 0; i< 27; i++) {
            estado[i].imprimirLista();
        }
    }
    
    
    
}
