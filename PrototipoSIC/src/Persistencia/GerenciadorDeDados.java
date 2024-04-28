/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Individuo.Cidadao;
import Individuo.Naturalidade;
import Individuo.Rg;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorDeDados {
    private static final String CAMINHO_DO_ARQUIVO = "cidadaos.json";
    private List<Cidadao> cidadaos;

    public GerenciadorDeDados() {
        this.cidadaos = new ArrayList<>();
        carregarCidadaos();
    }

    @SuppressWarnings("unchecked")
    

    public void carregarCidadaos() {
    JSONParser parser = new JSONParser();
    try (FileReader reader = new FileReader(CAMINHO_DO_ARQUIVO)) {
        Object obj = parser.parse(reader);
        JSONArray listaCidadaosJson = (JSONArray) obj;
        listaCidadaosJson.forEach(item -> {
            Cidadao cidadao = parsearObjetoCidadao((JSONObject) item);
            cidadaos.add(cidadao);
        });
    } catch (IOException e) {
        // Se não existir o arquivo, inicie uma nova lista vazia.
        // Isso é esperado na primeira execução, então não é necessário imprimir o stack trace.
        cidadaos = new ArrayList<>();
    } catch (ParseException e) {
        // Em caso de erro de parse, você pode querer informar o usuário ou logar o erro.
        e.printStackTrace();
    }
}

    
    private Cidadao parsearObjetoCidadao(JSONObject cidadaoJson) {
        String nome = (String) cidadaoJson.get("nome");
        String cpf = (String) cidadaoJson.get("cpf");
        String datanasc = (String) cidadaoJson.get("data_nasc");
    
        JSONArray rgsJson = (JSONArray) cidadaoJson.get("rgs");
        List<Rg> rgs = new ArrayList<>();
        for (Object r : rgsJson) {
            JSONObject rgJson = (JSONObject) r;
            String numero = (String) rgJson.get("rg");
            String estadoRG = (String) rgJson.get("estadoRG");
            rgs.add(new Rg(numero, estadoRG));
    }

    JSONObject naturalidadeJson = (JSONObject) cidadaoJson.get("naturalidade");
    Naturalidade naturalidade = new Naturalidade(
        (String) naturalidadeJson.get("cidade"),
        (String) naturalidadeJson.get("estado"));

        return new Cidadao(nome, datanasc, cpf, rgs, naturalidade);
}

    public void salvarCidadaos(List<Cidadao> cidadaos) {
        JSONArray listaCidadaos = new JSONArray();
        for (Cidadao cidadao : cidadaos) {
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

        try (FileWriter file = new FileWriter(CAMINHO_DO_ARQUIVO)) {
            file.write(listaCidadaos.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Cidadao> getCidadaos() {
        return cidadaos;
    }
    

}