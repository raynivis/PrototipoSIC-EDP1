package Importacoes;

import Estrutura.ArvoreMAria;
import Individuo.Cidadao;
import Individuo.Naturalidade;
import Individuo.Rg;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class JsonImporter {

    public ArvoreMAria importarCidadaosDeJson(String arquivoJson, ArvoreMAria arvoreCidadaos) {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(arquivoJson));
            JSONObject jsonObject = (JSONObject) obj;

            String estadoRG = (String) jsonObject.get("uf");
            JSONArray cidadaosJson = (JSONArray) jsonObject.get("cidadãos");

            for (Object c : cidadaosJson) {
                JSONObject cidadaoJson = (JSONObject) c;
                String nome = (String) cidadaoJson.get("nome");
                String cpf = (String) cidadaoJson.get("cpf");
                String rgNumero = (String) cidadaoJson.get("rg");
                String datanasc = (String) cidadaoJson.get("data_nasc");

                List<Rg> rgs = new ArrayList<>();
                if (rgNumero != null) {
                    rgs.add(new Rg(rgNumero, estadoRG));
                }

                JSONObject naturalidadeJson = (JSONObject) cidadaoJson.get("naturalidade");
                Naturalidade naturalidade = null;
                if (naturalidadeJson != null) {
                    naturalidade = new Naturalidade((String) naturalidadeJson.get("cidade"), (String) naturalidadeJson.get("estado"));
                }

                Cidadao novoCidadao = new Cidadao(nome, datanasc, cpf, rgs, naturalidade);
                arvoreCidadaos.adicionarCidadao(novoCidadao);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arvoreCidadaos;
    }
}
