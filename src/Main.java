import HTTP.Rota;
import HTTP.Server;
import java.io.IOException;

public class Main {
    public static void main(String[] args){
        Rota[] rotas = Rota.iniciarRotas();
        Server server = new Server(8080, rotas);
        try {
            server.start();
        } catch (IOException e){
            System.out.println("Não foi possivel iniciar o servidor\n"+e.getMessage());
        }

    }
}