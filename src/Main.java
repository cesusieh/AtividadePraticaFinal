import HTTP.Rota;
import HTTP.Server;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Rota[] rotas = Rota.iniciarRotas();
        Server server = new Server(8080, rotas);
        server.start();
    }
}