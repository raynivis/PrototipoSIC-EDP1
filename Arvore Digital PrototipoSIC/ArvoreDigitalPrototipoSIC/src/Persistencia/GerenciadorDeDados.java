package Persistencia;

import Estrutura.ArvoreMAria;
import Estrutura.NoArvore;
import Individuo.Cidadao;
import Individuo.Naturalidade;
import Individuo.Rg;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import Importacoes.JsonImporter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class GerenciadorDeDados {
    private static final String CAMINHO_DO_ARQUIVO = "cidadaos.json";
    private ArvoreMAria listaCadastros;

    public GerenciadorDeDados(ArvoreMAria listaCadastros) {
        this.listaCadastros = listaCadastros;
        
    }

    public void verificarExistenciaArquivo() {
        File arquivo = new File(CAMINHO_DO_ARQUIVO);

        if (arquivo.exists()) {
            carregarCidadaos();
        } else {
            JOptionPane.showMessageDialog(null, "O arquivo " + CAMINHO_DO_ARQUIVO + " não existe.", "", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void carregarCidadaos() {
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(CAMINHO_DO_ARQUIVO)) {
            JSONArray listaCidadaosJson = (JSONArray) parser.parse(reader);
            for (Object item : listaCidadaosJson) {
                JSONObject cidadaoJson = (JSONObject) item;
                Cidadao cidadao = parsearObjetoCidadao(cidadaoJson);
                listaCadastros.adicionarCidadao(cidadao);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public static Cidadao parsearObjetoCidadao(JSONObject cidadaoJson) {
        String nome = (String) cidadaoJson.get("nome");
        String cpf = (String) cidadaoJson.get("cpf");
        String datanasc = (String) cidadaoJson.get("data_nasc");

        JSONArray rgsJson = (JSONArray) cidadaoJson.get("rgs");
        List<Rg> rgs = new ArrayList<>();
        if (rgsJson != null) {
            for (Object r : rgsJson) {
                JSONObject rgJson = (JSONObject) r;
                String numero = (String) rgJson.get("numero");
                String estadoRG = (String) rgJson.get("Estado");
                rgs.add(new Rg(numero, estadoRG));
            }
        }

        JSONObject naturalidadeJson = (JSONObject) cidadaoJson.get("naturalidade");
        Naturalidade naturalidade = null;
        if (naturalidadeJson != null) {
            naturalidade = new Naturalidade((String) naturalidadeJson.get("cidade"), (String) naturalidadeJson.get("estado"));
        }

        return new Cidadao(nome, datanasc, cpf, rgs, naturalidade);
    }

    public void salvarCidadaos() {
        JSONArray listaCidadaos = new JSONArray();
        salvarNoArvore(listaCadastros.getRaiz(), listaCidadaos);

        try (FileWriter file = new FileWriter(CAMINHO_DO_ARQUIVO)) {
            file.write(listaCidadaos.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void salvarNoArvore(NoArvore no, JSONArray listaCidadaos) {
        if (no == null) {
            return;
        }

        if (no.getCidadao() != null) {
            Cidadao cidadao = no.getCidadao();
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

        for (NoArvore filho : no.getFilhos()) {
            salvarNoArvore(filho, listaCidadaos);
        }
    }

    public void importarCidadaosManual(String caminhoArquivoJson) {
        JsonImporter importer = new JsonImporter();
        importer.importarCidadaosDeJson(caminhoArquivoJson, listaCadastros);
    }
}
