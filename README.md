# AtividadePraticaFinal

O projeto consiste em uma simplês lista da tarefas. Ela utiliza páginas HTML para fazer requisições HTTP em suas rotas, além disso salva e modifica os dados contidos no arquivo "tarefas.json" dentro da pasta db.

# Classes principais:

Main
Responsavel por iniciar as rotas e em seguida o servidor

Server
Classe simples que iniciar um servidor da biblioteca HttpServer

Rota
Classe responsavel por gerenciar o Path e a Função de cada rota.

Pacote controller
Mantém as classes responsaveis pelas funções executadas nas rotas, essas classes implementam obrigatoriamente a interface HttpHandler.

Pacote repositorio
Mantém as classe que interagem diretamente com o arquivo .json

PaginasHtml
Classe responsavel por gerar as paginas web.

# Como usar

Para iniciar o projeto certifique-se de que a pasta Dependencias e a pasta db estão devidamente criadas e preenchidas. Em seguida deve-se compilar e executar o projeto, acessar seu localhost na porta 8080 e acessar a pagina contida no "/Home".

# Pesquisa
# GPT e Referências

O Chat GPT foi usado principalmente para tirar duvidas pontuais e para geração das paginas HTML.

Outras fontes de pesquis incluem o site StackOverFlow para tirar duvidas / resolver alguns problemas e a documentação 