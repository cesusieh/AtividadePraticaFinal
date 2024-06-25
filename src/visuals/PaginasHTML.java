package visuals;

import controllers.Helper;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;

public class PaginasHTML {
    public static String renderizarHome(){
        String listaJson = "";
        try (BufferedReader br = new BufferedReader(new FileReader("db/tarefas.json"))){
            ArrayList<JSONObject> jsons = Helper.lerJson(br);
            StringBuilder jsonBuilder = new StringBuilder();
            for (JSONObject json : jsons) {
                String status;
                if (json.getBoolean("concluida") == false) {
                    status = "Não concluída";
                } else {
                    status = "Concluída";
                }
                jsonBuilder.append("<span class=\"tarefa\">" + json.getString("id") + " - " + json.getString("nome") + "<span class=\"task-status\">"+ status + "</span>" + "</span>" + "<br>");
            }
            listaJson = jsonBuilder.toString();
        } catch (FileNotFoundException e){
            return "Arquivos tarefas.json não encontrado";
        } catch (IOException e) {
            return "Problema ao fazer leitura das tarefas";
        }

        String pagina =
                "<!DOCTYPE html>\n" +
                "<html lang=\"pt-br\">\n" +
                "    <head>\n" +
                "        <meta charset=\"UTF-8\">\n" +
                "        <title>Tarefas</title>\n" +
                "        <style>\n" +
                "            body { font-family: Arial, sans-serif; line-height: 1.6; margin: 20px; padding: 0; background-color: #f0f0f0; }\n" +
                "            h1 { color: #333; text-align: center; }\n" +
                "            .tasks { background-color: #fff; border: 1px solid #ccc; padding: 20px; margin: 20px auto; max-width: 600px; border-radius: 5px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);}\n" +
                "            .task-item { margin-bottom: 10px; padding: 10px; border: 1px solid #eee; background-color: #f9f9f9; border-radius: 4px; }\n" +
                "            .task-item.concluida { text-decoration: line-through; color: #888; }\n" +
                "            .add-button { text-align: center; margin-top: 20px; }\n" +
                "            .add-button a { display: inline-block; padding: 10px 20px; text-decoration: none; color: #007bff; background-color: #fff; border: 1px solid #007bff; border-radius: 5px; transition: background-color 0.3s, color 0.3s; }\n" +
                "            .add-button a:hover { background-color: #007bff; color: #fff; }\n" +
                "            .add-button a:before { content: \"+ \"; }\n" +
                "            .form-encerrar { margin-top: 20px; text-align: center; }\n" +
                "            .form-encerrar input[type=\"text\"] { padding: 8px; width: 200px; font-size: 14px; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box; }\n" +
                "            .form-encerrar button { margin-left: 10px; padding: 8px 20px; font-size: 14px; background-color: #dc3545; color: #fff; border: none; border-radius: 4px; cursor: pointer; transition: background-color 0.3s; }\n" +
                "            .form-encerrar button:hover { background-color: #c82333; }\n" +
                "            .task-status { float: right; }\n" +
                "        </style>\n" +
                "    </head>\n" +
                "    <body>\n" +
                "        <h1>Tarefas</h1>\n" +
                "        <div class=\"tasks\">"  + listaJson + "\n" +
                "            <div class=\"add-button\">\n" +
                "                <a href=\"/CriarTarefa\">Adicionar tarefa</a>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "            <div class=\"form-encerrar\">\n" +
                "                <form id=\"formEncerrarTarefa\">\n" +
                "                    <label for=\"inputTarefaEncerrar\">Encerrar Tarefa:</label>\n" +
                "                    <input type=\"text\" id=\"inputTarefaEncerrar\" name=\"tarefaEncerrar\" placeholder=\"Digite o ID da tarefa\" required>\n" +
                "                    <button type=\"submit\">Encerrar Tarefa</button>\n" +
                "                </form>\n" +
                "            </div>\n" +
                "            <div class=\"add-button\">\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </body>\n" +
                "<script>\n" +
                "    document.getElementById('formEncerrarTarefa').addEventListener('submit', function(event) {\n" +
                "        event.preventDefault();\n" +
                "\n" +
                "        var idTarefa = document.getElementById('inputTarefaEncerrar').value.trim();\n" +
                "\n" +
                "        var jsonData = {\n" +
                "            \"ID\": idTarefa\n" +
                "        };\n" +
                "\n" +
                "        fetch('/ConcluirTarefa', {\n" +
                "            method: 'POST',\n" +
                "            headers: {\n" +
                "                'Content-Type': 'application/json'\n" +
                "            },\n" +
                "            body: JSON.stringify(jsonData)\n" +
                "        });\n" +
                "\n" +
                "        alert('Requisição enviada para encerrar tarefa.');\n" +
                "        window.location.reload();\n" +
                "    });\n" +
                "</script>"+
                "</html>";

        return pagina;
    }

    public static String renderizarFormulario(){
        String pagina =
                "<!DOCTYPE html>\n" +
                "<html lang=\"pt-br\">\n" +
                "    <head>\n" +
                "        <meta charset=\"UTF-8\">\n" +
                "        <title>Tarefas - Adicionar Tarefa</title>\n" +
                "        <style>\n" +
                "            body { font-family: Arial, sans-serif; line-height: 1.6; margin: 20px; padding: 0; background-color: #f0f0f0; }\n" +
                "            h1 { color: #333; text-align: center; }\n" +
                "            form { max-width: 400px; margin: 0 auto; background-color: #fff; padding: 20px; border: 1px solid #ccc; border-radius: 5px; }\n" +
                "            label { display: block; margin-bottom: 10px; }\n" +
                "            input[type=\"text\"] { width: 100%; padding: 8px; font-size: 16px; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box; }\n" +
                "            button[type=\"submit\"] { background-color: #007bff; color: #fff; border: none; padding: 10px 20px; font-size: 16px; cursor: pointer; border-radius: 4px; display: block; margin-top: 20px; width: 100%; }\n" +
                "            button[type=\"submit\"]:hover { background-color: #0056b3; }\n" +
                "            a { display: block; text-align: center; margin-top: 20px; text-decoration: none; color: #007bff; }\n" +
                "            a:hover { text-decoration: underline; }\n" +
                "        </style>\n" +
                "    </head>\n" +
                "    <body>\n" +
                "        <h1>Adicionar tarefa</h1>\n" +
                "        <form id=\"formTarefa\">\n" +
                "            <label for=\"inputTarefa\">Tarefa:</label>\n" +
                "            <input type=\"text\" id=\"inputTarefa\" name=\"nome\" required>\n" +
                "            <br><br>\n" +
                "            <button type=\"submit\">Criar Tarefa</button>\n" +
                "        </form>\n" +
                "        <a href=\"/Home\">Voltar à home</a>\n" +
                "        <script>\n" +
                "            document.getElementById('formTarefa').addEventListener('submit', function(event) {\n" +
                "                event.preventDefault(); // Impede o envio do formulário padrão\n" +
                "\n" +
                "                const formData = new FormData(this);\n" +
                "                const jsonData = Object.fromEntries(formData);\n" +
                "\n" +
                "                fetch('/CriarTarefa', {\n" +
                "                    method: 'POST',\n" +
                "                    headers: {\n" +
                "                        'Content-Type': 'application/json'\n" +
                "                    },\n" +
                "                    body: JSON.stringify(jsonData)\n" +
                "                });\n" +
                "\n" +
                "                alert('Requisição enviada para criar tarefa.');\n" +
                "                window.location.href = '/Home'; // Redireciona para /Home\n" +
                "            });\n" +
                "        </script>\n" +
                "    </body>\n" +
                "</html>";

        return pagina;
    }
}
