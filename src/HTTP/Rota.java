package HTTP;

import com.sun.net.httpserver.HttpHandler;

public class Rota {
    private String path;
    private HttpHandler func;

    public static Rota[] iniciarRotas(){
        Rota[] rotas = {
                new Rota("/CriarTarefa", new controllers.CriarTarefa())
        };
        return rotas;
    }

    public String getPath() {
        return path;
    }

    public HttpHandler getFunc() {
        return func;
    }

    public Rota(String path, HttpHandler func) {
        this.path = path;
        this.func = func;
    }
}
