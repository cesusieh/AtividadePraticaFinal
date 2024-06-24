package controllers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONException;
import org.json.JSONObject;
import models.Resposta;
import repositorio.Tarefa;

import java.io.IOException;

public class ConcluirTarefa implements HttpHandler {
    @Override
    public void handle(HttpExchange request) throws IOException {
        JSONObject json = new JSONObject();
        try{
            json = Helper.lerRequest(request);
        } catch (JSONException e){
            Resposta resposta = new Resposta(422, "Não foi possivel processar os dados recebidos");
            resposta.gerarResponse(request);
        }
        int id = json.getInt("ID");
        Resposta resposta = Tarefa.concluirTarefa(id);
        resposta.gerarResponse(request);
    }
}
