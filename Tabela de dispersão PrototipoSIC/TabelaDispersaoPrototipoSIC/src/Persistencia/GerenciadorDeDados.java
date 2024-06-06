package Persistencia;

import Estrutura.TabelaHash;
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

public class GerenciadorDeDados {
    private static final String CAMINHO_DO_ARQUIVO = "cidadaos.json";
    

    @SuppressWarnings("unchecked")
    

    public static void verificarExistenciaArquivo(TabelaHash tabela) {
        // Garante que a lista seja inicializada, independentemente de sua existência prévia
        
        File arquivo = new File(CAMINHO_DO_ARQUIVO);   
        
        if (arquivo.exists()) {
            JsonImporter dados = new JsonImporter();
            // Como garantimos que a lista não é nula, passamos diretamente
            TempoDeExecucao tempo = new TempoDeExecucao();
            tempo.iniciar();
            dados.importarCidadaosDeJsonRapido(CAMINHO_DO_ARQUIVO, tabela);
            tempo.finalizar();
            long tempoDeExecucao = tempo.obterTempoEmMilissegundos();
            JOptionPane.showMessageDialog(null, "Tempo de execução de: " + tempoDeExecucao + " Milissegundos", "Pesistencia de Dados", JOptionPane.INFORMATION_MESSAGE);
            tabela.imprimirColisoes();         
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

    public void salvarCidadaos(TabelaHash tabela) {
        JSONArray listaCidadaos = new JSONArray();
        
        for (Cidadao cidadao : tabela.getCidadaos()) {
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
        }

        try (FileWriter file = new FileWriter("cidadaos.json")) {
            file.write(listaCidadaos.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

}