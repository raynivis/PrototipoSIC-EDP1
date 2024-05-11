
import Estrutura.ListaEncadeada;
import Importacoes.JsonImporter;
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
       ListaEncadeada listaCadastros = new ListaEncadeada();
       GerenciadorDeDados gerenciadorDeDados = new GerenciadorDeDados(listaCadastros);        
               
        new TelaInicial().setVisible(true);    
        
        listaCadastros.imprimirLista();
    }
        
}
