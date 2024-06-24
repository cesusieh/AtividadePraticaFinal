package controllers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import visuals.PaginasHTML;

import java.io.*;

public class HomePage implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String pagina = PaginasHTML.renderizarHome();
        byte[] responseBytes = pagina.getBytes();
        exchange.sendResponseHeaders(200, responseBytes.length);
        OutputStream os = exchange.getResponseBody();
        os.write(responseBytes);
        os.close();
    }
}
