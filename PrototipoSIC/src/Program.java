
import Estrutura.ListaEncadeada;
import Individuo.Cidadao;
import Individuo.Naturalidade;
import Individuo.Rg;
import Persistencia.GerenciadorDeDados;
import Relatorio.Relatorio;
import Telas.TelaInicial;
import Timer.TempoDeExecucao;
import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ra
 */
public class Program {
    
    public static void main(String[] args) {
       GerenciadorDeDados gerenciadorDeDados = new GerenciadorDeDados();
       
       /*declarando a lista*/
        /*cirando a lista de rgs*/
       /* List<Rg> lista1 = new ArrayList<>();
        Rg rg1 = new Rg("12", "sp");
        lista1.add(rg1);
        List<Rg> lista2 = new ArrayList<>();
        Rg rg2 = new Rg("20", "sp");
        lista2.add(rg2);
        List<Rg> lista3 = new ArrayList<>();
        Rg rg3 = new Rg("78", "sp");
        lista3.add(rg3);
        List<Rg> lista4 = new ArrayList<>();
        Rg rg4 = new Rg("89", "sp");
        lista4.add(rg4); */
        
        /*cirando uma naturalidade so*/
       // Naturalidade origem = new Naturalidade("Casticty", "SP");
        
        /*criando os Cidadaos*/
       /* Cidadao c1 = new Cidadao("Ray", "31/07/2004", "1", lista1, origem);
        
        Cidadao c2 = new Cidadao("Mayra Marques", "31/07/1998", "2", lista2, origem);
        Cidadao c3 = new Cidadao("Ligeiro", "31/07/1945", "3", lista3, origem);
        Cidadao c4 = new Cidadao("Cotrez", "31/07/2003", "4", lista4, origem); */
        
        /*adicionando os cidadaos na lista e criando os nohs em ordem de chegada*/
        /*lista.adicionarLista(c1);
        lista.adicionarLista(c2);
        lista.adicionarLista(c3);
        lista.adicionarLista(c4);
        tempo.finalizar();
        
        long tempoDeExecucao = tempo.obterTempoEmMilissegundos();
        System.out.println("Tempo de execucao: " + tempoDeExecucao + " Milissegundos");
        
        /*testando a insercao*/
       // lista.imprimirLista();
        
        
        /*testando colocar o msm cpf*/
       /* List<Rg> lista5 = new ArrayList<>();
        Rg rg5 = new Rg("36", "ms");
        lista5.add(rg5);
        Cidadao c5 = new Cidadao("Ray", "31/07/2004", "1", lista5, origem);
        
        /*adicionando na lista*/
        //lista.adicionarLista(c5); 

        /*testando para ver se inseriu no cpf "1"*/
        //lista.imprimirLista();
        
        
        
        
        new TelaInicial().setVisible(true);
         
    }
    
}
