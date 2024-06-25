package controllers;

import com.sun.net.httpserver.HttpExchange;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.*;
import java.util.ArrayList;

public class Helper {
    public static JSONObject lerRequest(HttpExchange request) throws IOException {
        InputStreamReader isr = new InputStreamReader(request.getRequestBody(), "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        StringBuilder requestBody = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            requestBody.append(line);
        }
        br.close();
        isr.close();
        return new JSONObject(requestBody.toString());
    }

    public static ArrayList<JSONObject> lerJson(BufferedReader br){
        ArrayList <JSONObject> jsons = new ArrayList<JSONObject>();
        String line;
        try {
            while ((line = br.readLine()) != null) {
                JSONObject j = new JSONObject(line);
                jsons.add(j);
            }
            return jsons;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (JSONException e) {
            System.out.println("Erro na leitura do arquivo JSON");
            return null;
        }
    }

    public static int buscarUltimoID(){
        try (BufferedReader br = new BufferedReader(new FileReader("db/tarefas.json"))){
            ArrayList<JSONObject> jsons = lerJson(br);
            return jsons.size();
        } catch (FileNotFoundException e) {
            System.out.println("Não foi possivel encontrar o arquivo");
            return -1;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return -1;
        } catch (NullPointerException e) {
            System.out.println("Não foi possivel encontrar o ultimo id");
            return -1;
        }
    }
}
