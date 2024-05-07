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
        System.out.println("\nAcre: ");
        ac.imprimirLista();
        System.out.println("\nAlagoa: ");
        al.imprimirLista();
        System.out.println("\nAmapa: ");
        ap.imprimirLista();
        System.out.println("\nAmazona: ");
        am.imprimirLista();
        System.out.println("\nBahia: ");
        ba.imprimirLista();
        System.out.println("\nCeara: ");
        ce.imprimirLista();
        System.out.println("\nDistrito Federal: ");
        df.imprimirLista();
        System.out.println("\nEspirto Santo: ");
        es.imprimirLista();
        System.out.println("\nGoias: ");
        go.imprimirLista();
        System.out.println("\nMaranhao: ");
        ma.imprimirLista();
        System.out.println("\nMato Grosso: ");
        mt.imprimirLista();
        System.out.println("\nMato Grosso do Sul: ");
        ms.imprimirLista();
        System.out.println("\nMinas Gerais: ");
        mg.imprimirLista();
        System.out.println("\nPara: ");
        pa.imprimirLista();
        System.out.println("\nParaiba: ");
        pb.imprimirLista();
        System.out.println("\nParana: ");
        pr.imprimirLista();
        System.out.println("\nPernambuco: ");
        pe.imprimirLista();
        System.out.println("\nPiaui: ");
        pi.imprimirLista();
        System.out.println("\nRio de Janeiro: ");
        rj.imprimirLista();
        System.out.println("\nRio Grande de Norte: ");
        rn.imprimirLista();
        System.out.println("\nRio Grande do Sul: ");
        rs.imprimirLista();
        System.out.println("\nRondonia: ");
        ro.imprimirLista();
        System.out.println("\nRoraima: ");
        rr.imprimirLista();
        System.out.println("\nSanta Catarina: ");
        sc.imprimirLista();
        System.out.println("\nSao Paulo");
        sp.imprimirLista();
        System.out.println("\nSergipe");
        se.imprimirLista();
        System.out.println("\nTocantins: ");
        to.imprimirLista();
    }
    
    
    
}
