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
                switch(naturalidadeEstado) {
                    case "AC":
                        estado[0].inserirNaLista(i.getCidadao());
                    break;
                    case "AL":
                        estado[1].inserirNaLista(i.getCidadao());
                    break;
                    case "AP":
                        estado[2].inserirNaLista(i.getCidadao());
                    break;
                    case "AM":
                        estado[3].inserirNaLista(i.getCidadao());
                    break;
                    case "BA":
                        estado[4].inserirNaLista(i.getCidadao());
                    break;
                    case "CE":
                        estado[5].inserirNaLista(i.getCidadao());
                    break;
                    case "DF":
                        estado[6].inserirNaLista(i.getCidadao());
                    break;
                    case "ES":
                        estado[7].inserirNaLista(i.getCidadao());
                    break;
                    case "GO":
                        estado[8].inserirNaLista(i.getCidadao());
                    break;
                    case "MA":
                        estado[9].inserirNaLista(i.getCidadao());
                    break;
                    case "MT":
                        estado[10].inserirNaLista(i.getCidadao());
                    break;
                    case "MS":
                        estado[11].inserirNaLista(i.getCidadao());
                    break;
                    case "MG":
                        estado[12].inserirNaLista(i.getCidadao());
                    break;
                    case "PA":
                        estado[13].inserirNaLista(i.getCidadao());
                    break;
                    case "PB":
                        estado[14].inserirNaLista(i.getCidadao());
                    break;
                    case "PR":
                        estado[15].inserirNaLista(i.getCidadao());
                    break;
                    case "PE":
                        estado[16].inserirNaLista(i.getCidadao());
                    break;
                    case "PI":
                        estado[17].inserirNaLista(i.getCidadao());
                    break;
                    case "RJ":
                        estado[18].inserirNaLista(i.getCidadao());
                    break;
                    case "RN":
                        estado[19].inserirNaLista(i.getCidadao());
                    break;
                    case "RS":
                        estado[20].inserirNaLista(i.getCidadao());
                    break;
                    case "RO":
                        estado[21].inserirNaLista(i.getCidadao());
                    break;
                    case "RR":
                        estado[22].inserirNaLista(i.getCidadao());
                    break;
                    case "SC":
                        estado[23].inserirNaLista(i.getCidadao());
                    break;
                    case "SP":
                        estado[24].inserirNaLista(i.getCidadao());
                    break;
                    case "SE":
                        estado[25].inserirNaLista(i.getCidadao());
                    break;
                    case "TO":
                        estado[26].inserirNaLista(i.getCidadao());
                    break;               
                    default:
                    break;
                }
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
