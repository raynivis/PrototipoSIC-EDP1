package Importacoes;

import Estrutura.EstruturaAVL;
import Individuo.Cidadao;
import Individuo.Naturalidade;
import Individuo.Rg;
import Persistencia.GerenciadorDeDados;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class JsonImporter {

        public EstruturaAVL importarCidadaosDeJson(String arquivoJson, EstruturaAVL arvoreAvl) {
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
                    String numeroRg = (String) cidadaoJson.get("rg");
                    String datanasc = (String) cidadaoJson.get("data_nasc");
                    
                    JSONObject naturalidadeJson = (JSONObject) cidadaoJson.get("naturalidade");
                    Naturalidade naturalidade = new Naturalidade((String) naturalidadeJson.get("cidade"), (String) naturalidadeJson.get("estado"));
                    Rg rg = new Rg(numeroRg, estadoRG);

                    List<Rg> rgs = new ArrayList<>();
                    rgs.add(rg);
                    Cidadao novoCidadao = new Cidadao(nome, datanasc, cpf, rgs, naturalidade);
                    /*inserção com a busca */
                    arvoreAvl.inserirAVL(novoCidadao);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return arvoreAvl;
        }
        
        public void importarCidadaosDeJsonRapido(String arquivoJson, EstruturaAVL arvoreAvl) {
            JSONParser parser = new JSONParser();
            try {
                Object obj = parser.parse(new FileReader(arquivoJson));
                JSONArray cidadaosJson = (JSONArray) obj;

                for (Object c : cidadaosJson) {
                    JSONObject cidadaoJson = (JSONObject) c;
                    Cidadao novoCidadao = GerenciadorDeDados.parsearObjetoCidadao(cidadaoJson);
                    if (novoCidadao != null) {
                        /* inserção dos dados do BD, sem necessidade de verificação */
                        arvoreAvl.inserirRapidoAVL(novoCidadao);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } 
}

