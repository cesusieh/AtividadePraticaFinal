package models;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public class Resposta {
    private String response;
    private int statusCode;

    public Resposta(int statusCode,  String response) {
        this.response = response;
        this.statusCode = statusCode;
    }

    public void gerarResponse(HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(statusCode, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
