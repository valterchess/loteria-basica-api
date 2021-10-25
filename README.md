# loteria-basica-api

# Processo de Desenvolvimento de uma Spring Rest	API.
Números de loteria associados à um cliente.

Valter F. Silva

https://www.github.com/valterchess



Primeiramente deve se levar em consideração o fato da escolha do Spring como ferramenta de auxílio ao desenvolvimento de uma API Java no modelo arquitetural Rest.

Obviamente quando se busca implementar um projeto é importante ter agilidade nos processos e evitar perder tempo com processos que são ‘padronizados’ ou de implementação repetitiva e muitas vezes complexas.
 O eco sistema Spring com um grande número de projetos nos facilita o processo de desenvolvimento ao reduzir/remover a necessidade da implementação de códigos complexos de infraestrutura, melhorando a produtividade e agilizando processos, e reduzindo o número de prolemas que podem ocorrer durante a implementação destes processos. E assim os desenvolvedores podem focar na implementação das regras de negócio e em entregar uma mais bem trabalhada e mais amigável ao consumidor , de forma mais rápida. Seguindo o princípio de entrega contínua.


O projeto que costumo usar em projetos pessoais, para treinamento e prática é o Spring Boot.

Pois ele permite a implementação das dependências de diversos sub projetos do eco sistema Spring, que implementam automaticamente outros sub projetos que forem convenientes para seu bom funcionamento. Facilitando processos e evitando incompatibilidade de versões que poderiam causar erros.

Os Sub Projetos Spring que utilizei no Spring starter project são:

    • Spring Web – Integra o protocolo HTTP à aplicação.
    • Validation – A especificação do Jakarta Bean validation, implementada pelo Hibernate, é muito importante para validar os dados informados pelo consumidor, e evitar que dados com informações descordantes, nulos ou  em branco sejam enviados ao banco de dados. Considero importante também fazer o tratamento das mensagens de erro enviadas ao consumidor para facilitar o entendimento do erro, caso algum dado esteja em desacordo com a validação requerida.
    • Spring Data JPA – A utilização do Data JPA, que também é uma especificação Jakarta implementada pelo Hibernate, é importante pois permite o mapeamento do objeto relacional, ou seja, facilita a integração e o mapeamento das entidades com as suas respectivas tabelas no banco de dados.
    • MySQL Driver – Facilita o processo de conexão com o banco de dados, permitindo que a conexão ocorra sem a necessidade da implementação de códigos repetitivos.
    • Flyway Migration – A utilização do flyway ajuda no controle de versionamento do banco de dados, e facilita o acesso através da migração. E já está integrado ao Spring, pois é um projeto importante.
    • Spring Boot devTools – Com restart automático da aplicação, ajuda a ganhar tempo no desenvolvimento. Apesar de que em alguns momentos é adequado fazer o restart manual da aplicação, ao fazer modificações no pom.xml por exemplo.


As demais ferramentas e recursos utilizados foram:

    • MySQL Server – Selecionei o MySQL, pois é o banco de dados relacional que tenho mais familiaridade.
    • MySQLWorkbench – É uma ferramenta que auxilia muito na visualização rápida das atualizações feitas por migração. Pois é possível que ocorra algum erro de sintaxe por exemplo e a execução não ocorra no banco de dados.
    • Postman – O postman é uma excelente ferramenta para testar os end-points e auxilia muito para identificar os Status de resposta nas diferentes circunstâncias na qual a api é exposta, assim facilitando a correção e o tratamento de erros e exceptions, para deixar as respostas mais amigáveis e inteligíveis para o consumidor da api.
    • Spring Tool Suite 4 – Essa IDE facilita muito os processos e permite ter agilidade na implementação de dependências, starters etc. Além de ser baseada no Eclipse, minha ide favorita.
    • Swagger – Assim como o postman ajuda a testar a api, acredito que o Swagger auxilie, que tem interesse em consumir a Api, a entender o funcionamento das regras de negócio, etc. Além de documentar de forma organizada, facilitando o acesso e o entendimento.
    • Git – O git dispensa apresentações ou explicações, seu uso é praticamente obrigatorio. Fiz o uso dele neste caso, pois gostei de fazer esse pequeno projeto e pretendo dar continuidade.
    • Outros… - além dessas tecnologias, obviamente é necessário trabalhar com o protocolo HTTP, o json, e o maven. Que neste caso são praticamente obrigatórios.

# Processos de desenvolvimento.
# Starter: 

Inicio a aplicação, com praticamente todos os Starters do Spring boot que irei usar. E Eles são:
    • Spring Boot DevTools
    • Validation
    • Spring Data JPA
    • Flyway Migration
    • MySql Driver
    • Spring Web


Procuro Sempre iniciar a Aplicação com todas as dependências que irei utilizar, mas acredito que é sempre possível que haja a necessidade, ao longo do desenvolvimento de incluir outros Starters, ou dependências, como no caso do Swagger, que incluí a dependências somente no final.

# Entity Cliente: 
  Apos baixar todas as dependências do Starter, iniciei a criação da entidade Cliente, e resolvi colocar apenas atributos básicos.

    • Cliente
    • Long Id;
    • String nome;
    • String e-mail;

Inclui as Validações; gerei os Getters e Setters; e o equals e o hashCode para o Id. O resultado final foi esse.
Geralmente inicio pelo Controller, mas desta vez resolvi iniciar pelo Entity, pois estava um pouco em duvida de como funcionariam os processos.

#ClienteRepository:
Apenas adicionei um método:
|Cliente findByEmail(String email);|

# Configurando Integração com MySQL:

Após criar o ClienteRepository, iniciei aconfiguração do aplication.properties, coisas simples criar banco de dados se não existir, nome do usuário e senha. 
---------------------------------------------------------------------------------------------------------
|spring.datasource.url=jdbc:mysql://localhost/orange_db?createDatabaseIfNotExist=true&serverTimezone=UTC|
|spring.datasource.data-username="nome"                                                                 |
|spring.datasource.data-password="5enH4"                                                                |
|(Obs.: Acabei cometendo um pequeno deslise aqui, mas vou explicar em seguida.)                         |
---------------------------------------------------------------------------------------------------------
Seguidamente (Ainda sem iniciar a Aplicação nenhuma vez), através do /DB/Migration criei o arquivo de migração da tabela cliente.
Então iniciei a Aplicação, e Obviamente, como havia comentado houve um erro. Pois Na criação do banco o comando correto, no meu caso, para usuário e senha não deveria conter o data o correto deveria ser assim:
---------------------------------------------------------------------------------------------------------
|spring.datasource.url=jdbc:mysql://localhost/orange_db?createDatabaseIfNotExist=true&serverTimezone=UTC|
|spring.datasource.username=”nome”                                                                      |
|spring.datasource.password=”5enH4”                                                                     |
---------------------------------------------------------------------------------------------------------
Após essa correção a execução ocorreu normalmente.

# Service: crud de Cliente;

Tudo muito básico, um método para criar novos clientes que verifica se o e-mail está cadastrado, e um método de exclusão.
A verificação do e-mail auxilia também na atualização dos dados.

# ClienteController:
Na classe ClienteController, fiz os métodos básicos de cadastro, atualização e de Consulta.
listarClientes(): que retorna uma lista de clientes com a facilidade do findAll() do JpaRepository.

buscar(): Que retorna um cliente especifico, e recebe como parâmetro o Id. E retorna um 404, se o cliente informado não existir.

adicionar(): Adiciona um novo cliente seguindo as regras do método da classe CadastroClienteService. Retorna o Status HTTP 201.

atualizar(): Atualiza um cliente recebendo como parâmetro o id, e verifica se o cliente existe no banco, caso o cliente não exista retorna um erro 404.
  
delete(): Exclui um cliente seguindo o método do CadastroClienteService. Se o cliente existir, faz a exclusão e retorna como Status o 204, e se por acaso o cliente não exista retorna um 404.


# Tratamento de Exceptions:

Para tratamento das exceptions, fiz uma classe Anotada com @ControllerAdvice, e que herda de ResponseEntityExceptionHandler. E reescrevi o método handleMethodArgumentNotValid(). Mas, para isso, criei uma classe com os atributos relevantes  para o consumidor da api. Como por exemplo: 
Status; que traz o Status da mensagem.
Titulo; que informa o que pode ter causado o erro
DataHora; que informa o horário exato.

Fiz o teste e vi que não retornava o campo especifico na qual havia ocorrido o erro.
Fiz então uma classe estática (Campo) dentro da classe de Apoio (Problema). E dentro da classe problema adicionei um atributo do tipo list<Campo>. E dentro do método reescrito da classe de tratamento, atribui um ArrayList do tipo Problema.Campo a uma variável, que foi usada como parâmetro para uma variável do tipo Problema. Pode ter ficado um pouco confuso mas a imagem vai ajudar na compreensão.

Em breve falarei a respeito do método handleNegocio().
  
Obs.: A anotação @JsonInclude serve para que quando os campos tiverem valor null ele não apareça na resposta.

# Loteria:
Na classe loteria, inclui os atributos id, assim como em cliente; Cliente cliente, que recebe a anotação @ManyToOne; numeros e dataCompra. Então segui os mesmos processos:
Criando o repository, e a tabela no banco através da migração.

Na criação da tabela fiz um foreign de (cliente_id) da entidade/tabela loteria tendo como referência [cliente (id)] da entidade/tabela cliente.

  create table loteria (
	  id bigint not null auto_increment,
	  cliente_id bigint not null,
	  quantidade int not null,
	  numeros int not null,
	  data_compra datetime not null,
	
	  primary key (id)
	);
	
	alter table loteria add constraint fk_loteria_cliente
	foreign key (cliente_id) references cliente (id);

Obs.: As anotações @JsonProperty(access =Access.READ_ONLY)
servem para que esses atributos recebam apenas parametros informados nas regras de negócio.

# LoteriaService:

Na classe loteria service importei, LoteriaRepository e ClienteRepository. Pois, para retornar todos os dados do cliente ao associar a ele um número iria precisar desta interface.
Agreguei a ela um único método Gerar(). Que recebe como parâmetro um objeto do tipo Loteria.
Obs.: Ao passar um cliente que não existia na tabela era retornado um Status 500. Sendo Assim seria necessário fazer um tratamento.
Passei então uma verificação, onde dado a ausência do cliente deveria retornar uma mensagem simples, mas bem tratada. Por isso não quis passar uma Exception genérica e fiz a classe DomainException, que herda de RuntimeException.

  Após isso, apliquei ela na classe ApiExceptionHandler através do método handleNegocio, organizando a resposta com um objeto do tipo problema e retornando uma handleExceptionInternal().

	@ExceptionHandler(DomainException.class)
	public ResponseEntity<Object> handleNegocio(DomainException ex,            WebRequest request){
		var status = HttpStatus.BAD_REQUEST;
		
		var problema = new Problema();
		
		problema.setStatus(status.value());
		problema.setTitulo(ex.getMessage());
		problema.setDataHora(LocalDateTime.now());
	
		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
 		
# Voltando à LoteriaService. 
O metodo gerar recebe como parâmetro do tipo Loteria. Onde deve ser informado o id do cliente, todos os demais atributos são gerados de forma automática. O número aleatório que o cliente recebe como sendo seu número de loteria é do tipo Random() do pacote Java.util.random.
E então a classe retorna um loteriaRepository.save(loteria);
como mostra a classe LoteriaService.

# LoteriaController:
Tem apenas um método que recebe e valida o parâmetro necessário (“cliente”: {“id”: 1} | por exemplo), para o método gerar da classe loteriaService, executar as regras de negócio e agregar um número aleatório ao cliente.

# Swagger: 
Adicionei a dependência do Swagger no pom.xlm por ultimo para que não perdesse muito tempo, já que estava fazendo os testes com o Postman. A versão do Swagger é a 2.9.2.
Seguidamente criei a classe de configuração do Swagger, tendo como pacote de base o pacote raiz e os paths, tudo que viesse depois de /api (“/api/**”). A classe ficou assim:



A documentação do Swagger no Localhost:


E após isso fiz o git init, git add -A, git commit -m “...”, etc…
  
