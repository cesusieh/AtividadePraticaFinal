package HTTP;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {
    private int port;
    private Rota[] rotas;

    public Server(int port, Rota[] rotas) {
        this.port = port;
        this.rotas = rotas;
    }

    public void start() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(port),0);
        for (Rota rota : rotas){
            server.createContext(rota.getPath(), rota.getFunc());
        }
        server.start();
        System.out.println("Aplicação rodando na porta: "+port);
    }
}
