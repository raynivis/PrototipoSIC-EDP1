/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Importacoes;

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

    public static List<Cidadao> importarCidadaosDeJson(String arquivoJson) {
        List<Cidadao> cidadaos = new ArrayList<>();
        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader(arquivoJson));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray cidadaosJson = (JSONArray) jsonObject.get("cidadãos");

            for (Object c : cidadaosJson) {
                JSONObject cidadaoJson = (JSONObject) c;
                String nome = (String) cidadaoJson.get("nome");
                String cpf = (String) cidadaoJson.get("cpf");
                String datanasc = (String) cidadaoJson.get("data_nasc");
                JSONObject naturalidadeJson = (JSONObject) cidadaoJson.get("naturalidade");
                Naturalidade naturalidade = new Naturalidade(
                    (String) naturalidadeJson.get("cidade"),
                    (String) naturalidadeJson.get("estado"));
                
                /*mudei aqui*/
                List<Rg> rgs =  new ArrayList<>();
                Rg rg = new Rg("rg", "a");
                rgs.add(rg) ;
                /*mudei ate aqui, tava dando erro pq era so uma array, ai tive q fazer esse tampa buraco
                para conseguir compilar e testar*/

                Cidadao cidadao = new Cidadao(nome, datanasc, cpf, rgs, naturalidade);
                cidadaos.add(cidadao);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return cidadaos;
    }
}
