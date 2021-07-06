# movie-list-api
Projeto Spring Boot que disponibiliza um recurso para consultar os ganhadores da categoria de <b>Pior Filme</b> do <i>Golden Raspberry Awards</i>.

##### Executando o projeto

* Faça o clone do projeto em um diretório de sua escolha;
* Execute o comando:
	<code>
		mvn clean install
	</code>
* Importe o projeto para sua IDE de preferênica;
* Acesse a API através da url: <b>http://localhost:8080</b>.

No diretório <code>src/main/resources/data</code> há um arquivo .csv que contém os dados que são expostos pela API. 
Ao iniciar a aplicação, o Spring faz a leitura desse arquivo e persiste os dados no banco de dados H2
que pode ser acessado via: <b>http://localhost:8080/h2-console</b>

Em caso de necessidade para efetuar a troca do arquivo .csv, é preciso subistituí-lo dentro do diretório e reiniciar a aplicação.

O projeto com os testes de integração está disponível no repositório <a>https://github.com/renanramos/movie-list-integration</a>.
