/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Timer;

/**
 *
 * @author Eduardo
 */
public class TempoDeExecucao {
    private long startTime; // Tempo de início da execução
    private long endTime; // Tempo de término da execução

    // Método para iniciar a medição do tempo de execução
    public void iniciar() {
        startTime = System.nanoTime();
    }

    // Método para finalizar a medição do tempo de execução
    public void finalizar() {
        endTime = System.nanoTime();
    }

    // Método para calcular e obter o tempo de execução em milissegundos
    public long obterTempoEmMilissegundos() {
        return (endTime - startTime)/ 1000000;// Convertido para milissegundos
    }
}