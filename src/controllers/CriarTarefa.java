package controllers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import models.Tarefa;
import org.json.JSONException;
import org.json.JSONObject;
import models.Resposta;
import visuals.PaginasHTML;

import java.io.IOException;
import java.io.OutputStream;

public class CriarTarefa implements HttpHandler {

    @Override
    public void handle(HttpExchange request) throws IOException {
        if ("POST".equals(request.getRequestMethod())){
            JSONObject json = new JSONObject();
            try{
                json = Helper.lerRequest(request);
            } catch (JSONException e){
                Resposta resposta = new Resposta(422, "Não foi possivel processar os dados recebidos");
                resposta.gerarResponse(request);
            }
            Tarefa t = new Tarefa(json.getString("nome"));
            Resposta response = repositorio.Tarefa.criarTarefa(t);
            response.gerarResponse(request);
        } else if ("GET".equals(request.getRequestMethod())){
                String pagina = PaginasHTML.renderizarFormulario();
                byte[] responseBytes = pagina.getBytes();
                request.sendResponseHeaders(200, responseBytes.length);
                OutputStream os = request.getResponseBody();
                os.write(responseBytes);
                os.close();
        } else {
            Resposta response = new Resposta(405, "Método HTTP inválido");
            response.gerarResponse(request);
        }
    }
}
