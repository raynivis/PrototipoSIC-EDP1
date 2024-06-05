package Persistencia;

import Estrutura.EstruturaAVL;
import Importacoes.JsonImporter;
import Individuo.Cidadao;
import Individuo.Naturalidade;
import Individuo.Rg;
import java.io.File;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class GerenciadorDeDados {
    private static final String CAMINHO_DO_ARQUIVO = "cidadaos.json";
    private EstruturaAVL arvoreAvl;

    public GerenciadorDeDados(EstruturaAVL arvoreAvl) {
        this.arvoreAvl = new EstruturaAVL();
    }

    @SuppressWarnings("unchecked")
    

    public static void verificarExistenciaArquivo(EstruturaAVL arvoreAvl) {
        File arquivo = new File(CAMINHO_DO_ARQUIVO);   
        
        if (arquivo.exists()) {
            JsonImporter dados = new JsonImporter();
            dados.importarCidadaosDeJsonRapido(CAMINHO_DO_ARQUIVO, arvoreAvl);
        } else {
            JOptionPane.showMessageDialog(null, "O arquivo " + CAMINHO_DO_ARQUIVO + " não existe.", "", 1);
        }
    }
        
    public void carregarCidadaos() {
    JSONParser parser = new JSONParser();
    try (FileReader reader = new FileReader(CAMINHO_DO_ARQUIVO)) {
        Object obj = parser.parse(reader);
        JSONArray listaCidadaosJson = (JSONArray) obj;
        listaCidadaosJson.forEach(item -> {
            Cidadao cidadao = parsearObjetoCidadao((JSONObject) item);
            arvoreAvl.inserirAVL(cidadao);
        });
    } catch (IOException e) {
        // Se não existir o arquivo, inicie uma nova lista vazia.
        // Isso é esperado na primeira execução, então não é necessário imprimir o stack trace.
        arvoreAvl = new EstruturaAVL();
    } catch (ParseException e) {
        // Em caso de erro de parse, você pode querer informar o usuário ou logar o erro.
        e.printStackTrace();
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

    public void salvarCidadaos(EstruturaAVL arvoreAvl) {
        JSONArray listaCidadaos = new JSONArray();
        
        arvoreAvl.preOrdem((noAVL) -> {
            Cidadao cidadao = noAVL.getCidadao();
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
            file.write(listaCidadaos.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}