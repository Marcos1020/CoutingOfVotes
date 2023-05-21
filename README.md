# COUNTING-OF-VOTES
Projeto criado, para fazer a simulação de uma contagem de votos.
Onde é criado uma pauta especifica a ser votada. Onde é necessario ser um associado, 
para poder votar na pauta e decidir o rumo da mesma, se deve ser aprovada ou não.


# EXECUÇÃO DO PROJETO.

- Ter o Java instalado na maquina, utilizei o Java11 para o desenvolvimento da minha aplicação 

- Para execução do projeto é necessario ter o docker-compose instalado na maquina,
uma interface para simular o banco de dados MySql(eu utilizei o DBeaver).

- Abrir a pasta docker que se encontra dentro do projeto, dentro da pasta digitar o seguinte comando 
docker-compose up -d para gerar a imagem e criar a conexão com o banco de dados.

- feito isso, e com o projeto rodando em sua maquina vamos abrir o Swagger para realização dos teste 
 url para abrir o swagger "http://localhost:9292/swagger-ui/index.html#/".

- Primeiro passo criar os associados
- segundo passo criar a pauta 
- terceiro passo pegar o Idt da pauta que foi criada e abriar a votação passando o tempo que a mesma deve ficar aberta para votação.
- Quarto passo pagar o identificador do associado e votar.

