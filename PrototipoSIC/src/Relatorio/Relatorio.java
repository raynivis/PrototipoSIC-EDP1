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
    ListaRelatorio ac = new ListaRelatorio();
    ListaRelatorio al = new ListaRelatorio();
    ListaRelatorio ap = new ListaRelatorio();
    ListaRelatorio am = new ListaRelatorio();
    ListaRelatorio ba = new ListaRelatorio();
    ListaRelatorio ce = new ListaRelatorio();
    ListaRelatorio df = new ListaRelatorio();
    ListaRelatorio es = new ListaRelatorio();
    ListaRelatorio go = new ListaRelatorio();
    ListaRelatorio ma = new ListaRelatorio();
    ListaRelatorio mt = new ListaRelatorio();
    ListaRelatorio ms = new ListaRelatorio();
    ListaRelatorio mg = new ListaRelatorio();
    ListaRelatorio pa = new ListaRelatorio();
    ListaRelatorio pb = new ListaRelatorio();
    ListaRelatorio pr = new ListaRelatorio();
    ListaRelatorio pe = new ListaRelatorio();
    ListaRelatorio pi = new ListaRelatorio();
    ListaRelatorio rj = new ListaRelatorio();
    ListaRelatorio rn = new ListaRelatorio();
    ListaRelatorio rs = new ListaRelatorio();
    ListaRelatorio ro = new ListaRelatorio();
    ListaRelatorio rr = new ListaRelatorio();
    ListaRelatorio sc = new ListaRelatorio();
    ListaRelatorio sp = new ListaRelatorio();
    ListaRelatorio se = new ListaRelatorio();
    ListaRelatorio to = new ListaRelatorio();

    public Relatorio(ListaEncadeada estruraDeDados, int faixaetaria1, int faixaetaria2) {
        this.estruraDeDados = estruraDeDados;
        No i = this.estruraDeDados.getCabeca();
        while(i != null) {
            String data = i.getCidadao().getDatanasc();
            String anoTexto = data.substring(data.length()-4, data.length());
            int ano = Integer.parseInt(anoTexto);
            System.out.println(2024-ano);
            if((2024-ano) >= faixaetaria1 && (2024-ano) <= faixaetaria2) {           
                String naturalidadeEstado = i.getCidadao().getOrigem().getEstado();
                switch(naturalidadeEstado) {
                    case "AC":
                        ac.inserirNaLista(i.getCidadao());
                    break;
                    case "AL":
                        al.inserirNaLista(i.getCidadao());
                    break;
                    case "AP":
                        ap.inserirNaLista(i.getCidadao());
                    break;
                    case "AM":
                        am.inserirNaLista(i.getCidadao());
                    break;
                    case "BA":
                        ba.inserirNaLista(i.getCidadao());
                    break;
                    case "CE":
                        ce.inserirNaLista(i.getCidadao());
                    break;
                    case "DF":
                        df.inserirNaLista(i.getCidadao());
                    break;
                    case "ES":
                        es.inserirNaLista(i.getCidadao());
                    break;
                    case "GO":
                        go.inserirNaLista(i.getCidadao());
                    break;
                    case "MA":
                        ma.inserirNaLista(i.getCidadao());
                    break;
                    case "MT":
                        mt.inserirNaLista(i.getCidadao());
                    break;
                    case "MS":
                        ms.inserirNaLista(i.getCidadao());
                    break;
                    case "MG":
                        mg.inserirNaLista(i.getCidadao());
                    break;
                    case "PA":
                        pa.inserirNaLista(i.getCidadao());
                    break;
                    case "PB":
                        pb.inserirNaLista(i.getCidadao());
                    break;
                    case "PR":
                        pr.inserirNaLista(i.getCidadao());
                    break;
                    case "PE":
                        pe.inserirNaLista(i.getCidadao());
                    break;
                    case "PI":
                        pi.inserirNaLista(i.getCidadao());
                    break;
                    case "RJ":
                        rj.inserirNaLista(i.getCidadao());
                    break;
                    case "RN":
                        rn.inserirNaLista(i.getCidadao());
                    break;
                    case "RS":
                        rs.inserirNaLista(i.getCidadao());
                    break;
                    case "RO":
                        ro.inserirNaLista(i.getCidadao());
                    break;
                    case "RR":
                        rr.inserirNaLista(i.getCidadao());
                    break;
                    case "SC":
                        sc.inserirNaLista(i.getCidadao());
                    break;
                    case "SP":
                        sp.inserirNaLista(i.getCidadao());
                    break;
                    case "SE":
                        se.inserirNaLista(i.getCidadao());
                    break;
                    case "TO":
                        to.inserirNaLista(i.getCidadao());
                    break;               
                    default:
                    break;
                }
            }
            i=i.prox;
        }      
    }
    
    public void imprimirRelatorio(){
        
        ac.imprimirLista();
        al.imprimirLista();
        ap.imprimirLista();
        am.imprimirLista();
        ba.imprimirLista();
        ce.imprimirLista();
        df.imprimirLista();
        es.imprimirLista();
        go.imprimirLista();
        ma.imprimirLista();
        mt.imprimirLista();
        ms.imprimirLista();
        mg.imprimirLista();
        pa.imprimirLista();
        pb.imprimirLista();
        pr.imprimirLista();
        pe.imprimirLista();
        pi.imprimirLista();
        rj.imprimirLista();
        rn.imprimirLista();
        rs.imprimirLista();
        ro.imprimirLista();
        rr.imprimirLista();
        sc.imprimirLista();
        sp.imprimirLista();
        se.imprimirLista();
        to.imprimirLista();
    }
    
    
    
}
