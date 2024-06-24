package repositorio;

import controllers.Helper;
import org.json.JSONObject;
import models.Resposta;

import java.io.*;
import java.util.ArrayList;

public class Tarefa {
    public static Resposta criarTarefa(models.Tarefa tarefa){
        try (BufferedReader br = new BufferedReader(new FileReader("db/tarefas.json"))){
            ArrayList<JSONObject> jsons = Helper.lerJson(br);
            jsons.add(new JSONObject(tarefa.toString()));
            try (FileWriter file = new FileWriter("db/tarefas.json")){
                System.out.println(jsons);
                for (JSONObject json : jsons){
                    file.write(json.toString()+"\n");
                }
                file.flush();
            } catch (IOException e){
                return new Resposta(405, "Erro ao salvar tarefa");
            }
        } catch (FileNotFoundException e) {
            return new Resposta(500, "Arquivo de tarefas não encontrado");
        } catch (IOException e) {
            return new Resposta(405, "Erro ao salvar tarefa");
        }
        return new Resposta(201, "Tarefa salva com sucesso");
    }

    public static void escreverJson(ArrayList<JSONObject> jsons) throws IOException {
        try (FileWriter fw = new FileWriter("db/tarefas.json")){
            for (JSONObject json : jsons){
                fw.write(json.toString()+"\n");
            }
            fw.flush();
        } catch (IOException e){
            throw new IOException();
        }
    }

    public static Resposta concluirTarefa(int id) {
        try (BufferedReader br = new BufferedReader(new FileReader("db/tarefas.json"))) {
            ArrayList<JSONObject> jsons = Helper.lerJson(br);

            for (JSONObject json : jsons) {
                if (json.getInt("id") == id) {
                    json.put("concluida", true);
                }
            }
            escreverJson(jsons);
        } catch (FileNotFoundException e) {
            return new Resposta(500, "Arquivo de tarefas não encontrado");
        } catch (IOException e) {
            return new Resposta(405, "Erro ao salvar tarefa");
        }
        return new Resposta(200, "Tarefa concluida com sucesso");
    }
}
