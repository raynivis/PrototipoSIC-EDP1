package Persistencia;

import Estrutura.ABB;
import Importacoes.JsonImporter;
import Individuo.Cidadao;
import Individuo.Naturalidade;
import Individuo.Rg;
import Timer.TempoDeExecucao;
import java.io.File;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
/**
 *
 * @author Eduardo, Gustavo
 */
public class GerenciadorDeDados {
    private static final String CAMINHO_DO_ARQUIVO = "cidadaos.json";

    @SuppressWarnings("unchecked")
    public static void verificarExistenciaArquivo(ABB abb) {
        File arquivo = new File(CAMINHO_DO_ARQUIVO);
        if (arquivo.exists()) {
            JsonImporter dados = new JsonImporter();
            TempoDeExecucao tempo = new TempoDeExecucao();
            // Começa a calcular o tempo
            tempo.iniciar();
            dados.importarCidadaosDeJsonRapido(CAMINHO_DO_ARQUIVO, abb);
            tempo.finalizar();
            long tempoDeExecucao = tempo.obterTempoEmMilissegundos();
            JOptionPane.showMessageDialog(null, "Tempo de execução: " + tempoDeExecucao + " Milissegundos", "Persistencia de Dados", JOptionPane.INFORMATION_MESSAGE);      
        } else {
            JOptionPane.showMessageDialog(null, "O arquivo " + CAMINHO_DO_ARQUIVO + " não existe.", "", 1);
        }
    }

    public static Cidadao parsearObjetoCidadao(JSONObject cidadaoJson) {
        String nome = (String) cidadaoJson.get("nome");
        String cpf = (String) cidadaoJson.get("cpf");
        String datanasc = (String) cidadaoJson.get("data_nasc");
        JSONArray rgsJson = (JSONArray) cidadaoJson.get("rgs");
        List<Rg> rgs = new ArrayList<>();
        for (Object r : rgsJson) {
            JSONObject rgJson = (JSONObject) r;
            String numero = (String) rgJson.get("numero");
            String estadoRG = (String) rgJson.get("Estado");
            rgs.add(new Rg(numero, estadoRG));
        }
        JSONObject naturalidadeJson = (JSONObject) cidadaoJson.get("naturalidade");
        Naturalidade naturalidade = new Naturalidade(
                (String) naturalidadeJson.get("cidade"),
                (String) naturalidadeJson.get("estado"));
        return new Cidadao(nome, datanasc, cpf, rgs, naturalidade);
    }

    public void salvarCidadaos(ABB abb) {
        JSONArray listaCidadaos = new JSONArray();
        abb.preOrdem((noABB) -> {
            Cidadao cidadao = noABB.getCidadao();
            JSONObject detalhesCidadao = new JSONObject();
            detalhesCidadao.put("nome", cidadao.getNome());
            detalhesCidadao.put("cpf", cidadao.getCpf());
            detalhesCidadao.put("data_nasc", cidadao.getDatanasc());
            JSONArray arrayRgs = new JSONArray();
            for (Rg rg : cidadao.getRgGerais()) {
                JSONObject detalhesRg = new JSONObject();
                detalhesRg.put("numero", rg.getRg());
                detalhesRg.put("Estado", rg.getEstadoRG());
                arrayRgs.add(detalhesRg);
            }
            detalhesCidadao.put("rgs", arrayRgs);
            JSONObject detalhesNaturalidade = new JSONObject();
            detalhesNaturalidade.put("cidade", cidadao.getOrigem().getCidade());
            detalhesNaturalidade.put("estado", cidadao.getOrigem().getEstado());
            detalhesCidadao.put("naturalidade", detalhesNaturalidade);
            listaCidadaos.add(detalhesCidadao);
        });
        try (FileWriter file = new FileWriter("cidadaos.json")) {
            TempoDeExecucao tempo = new TempoDeExecucao();
            // Começa a calcular o tempo
            tempo.iniciar();
            file.write(listaCidadaos.toJSONString());
            file.flush();
            tempo.finalizar();
            long tempoDeExecucao = tempo.obterTempoEmMilissegundos();
            JOptionPane.showMessageDialog(null, "Tempo de execução: " + tempoDeExecucao + " Milissegundos", "Persistencia de Dados: Salvar", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}