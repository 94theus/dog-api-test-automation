## Tecnologias utilizadas no projeto

- Java JDK 8 - [Java JDK 8](https://www.oracle.com/br/java/technologies/javase/javase8-archive-downloads.html)
- IDE de desenvolvimento (Sugestão) - [JetBrains IntelliJ IDEA Community](https://www.jetbrains.com/pt-br/idea/download/#section=windows)
- Framework de testes automatizados de API - [RestAssured](https://rest-assured.io/)
- Relatório de teste - [Extent Reports](https://www.extentreports.com/)
- Orquestrador de testes - [TestNG](https://testng.org)
- Manipulação de dados e JSON - [Gson](http://google.github.io/gson/UserGuide.html)

## Abrindo o projeto (Baseado no uso do IntelliJ)

1. Baixar o projeto
2. Abrir IntelliJ 
3. Clicar em "File"
4. Clicar em "Open"
5. Selecinar a pasta raiz do projeto
6. Clicar em "OK"
7. O projeto será carregado e as dependências serão baixadas automaticamente. Aguardar até o fim.

## Arquitetura:

O projeto foi estruturado utilizando uma arquitetura em camadas, com foco em organização, reutilização e desacoplamento.

A arquitetura padrão composta por:

- `bases`: Pacote que contém as classes pai, que definem comportamentos ou contém implementações que serão extendidas pelas classes filhas. As já existentes são:
  - **RequestRestBase:** Deve ser herdada por toda classe "request" que representará uma requisição de uma API REST.
  - **TestBase:** Deve ser herdada por toda classe "test", gerencia o clico da vida dos testes e integração com o Extent Reports.
- `requests`: Contém as classes que representam os endpoints da API, cada classe define o serviço (endpoint) e o método HTTP.
- `tests`: Contém os cenários de teste seguindo o padrão AAA (Arrange, Act, Assert).
- `utils`: Pacote que contém classes com utilitários das tecnologias utilizadas no template, as já existentes são:
  - **RestAssuredUtils:** Responsável pela execução das requisições HTTP.
  - **ExtentReportsUtils:** Responsável pela geração do relatório de testes.
  - **GeneralUtils:** Utilitários auxiliares, como formatação de JSON.
  - **GlobalParameters:** Responsável por carregar e disponibilizar as configurações globais do projeto.
- `resources`: Contém arquivos de configuração:
  - **globalParameters.properties:** Define variáveis como base URL, ambiente e caminho do relatório.
  - **simplelogger.properties:** Configuração do SLF4J para controle de nível de log durante a execução dos testes, reduzindo logs desnecessários (como logs internos do TestNG) e mantendo a saída mais limpa e legível.
- `pom.xml`: Arquivo de configuração do Maven, responsável por gerenciar dependências e execução dos testes.

Essa arquitetura permite separação de responsabilidades, falicitando manutenção e escalabilidade dos testes automatizados.

O projeto foi desenvolvido visando execução em linha de comando e integração com pipelines de CI/CD.

## Configuração das variáveis globais/de ambiente dos testes

O arquivo `globalParameters.properties` contém as variáveis globais utilizadas pelos testes, como a base URL da API, ambiente e configurações de relatório.

Ele segue o padrão de chave e valor (key=value), permitindo alterar configurações sem necessidade de modificar o código.

Essas propriedades são carregadas pela classe `GlobalParameters`, que centraliza o acesso às configurações ao longo da execução dos testes.


## Cenários de Teste

Foram automatizados os seguintes cenários utilizando a Dog API:

---

### Get Breeds List
- Endpoint: `/breeds/list/all`
- Método: `GET`

#### Cenários automatizados
- Deve retornar a lista de raças com sucesso

#### Validações realizadas
- Status code `200`
- Campo `status` igual a `success`
- Campo `message` não nulo
- Presença de raças conhecidas no retorno, como `hound`, `bulldog` e `retriever`

---

### Get Breed Images
- Endpoint: `/breed/{breed}/images`
- Método: `GET`

#### Cenários automatizados
- Deve retornar imagens para uma raça válida
- Deve retornar comportamento de erro para uma raça inválida

#### Validações realizadas
- Status code `200` para raça válida
- Campo `status` igual a `success`
- Campo `message` não nulo
- Lista de imagens não vazia
- Primeiro item da lista contendo uma URL de imagem
- Para raça inválida, validação de campos de retorno de erro

---

### Get Random Image
- Endpoint: `/breeds/image/random`
- Método: `GET`

#### Cenários automatizados
- Deve retornar uma imagem aleatória com sucesso

#### Validações realizadas
- Status code `200`
- Campo `status` igual a `success`
- Campo `message` não nulo
- Campo `message` contendo uma URL válida

> Observação: Por se tratar de um endpoint público e com resposta dinâmica, pode ocorrer instabilidade ocasional (ex: status 503).


## Estrutura dos testes

Os testes foram organizados seguindo o padrão AAA (Arrange, Act, Assert), com o objetivo de manter clareza e separação de responsabilidades em cada cenário.

- **Arrange:** preparação dos dados necessários para o teste, como parâmetros, valores esperados e instanciação da classe de requisição.
- **Act:** execução da requisição à API.
- **Assert:** validação do retorno obtido, como status code, estrutura da resposta e conteúdo esperado.

## Relatório de testes

A execução dos testes gera automaticamente um relatório em HTML utilizando o Extent Reports.

O relatório é salvo no diretório definido pela propriedade `report.path` no arquivo `globalParameters.properties` (por padrão: `target/reports`).

Para visualizar o resultado da execução, basta acessar a pasta gerada e abrir o arquivo `.html` em um navegador.

O relatório contém informações detalhadas sobre os testes executados, incluindo status, requisições realizadas e respostas da API.


## Padrão de escrita de código

O projeto segue o padrão CamelCase, amplamente utilizado em aplicações Java, com o objetivo de manter consistência e legibilidade do código.

- Classes e arquivos de classe iniciam com letra maiúscula  
  Ex: `GetRandomImageTests`, `RequestRestBase`

- Métodos e variáveis iniciam com letra minúscula  
  Ex: `executeRequest()`, `baseUrl`

- Pacotes são escritos em letras minúsculas  
  Ex: `com.testeautomacaoapi.tests`

- Constantes são escritas em letras maiúsculas com separação por `_`  
  Ex: `BASE_URL`, `REPORT_PATH`

Esse padrão foi aplicado em toda a estrutura do projeto, incluindo classes de testes, requests e utilitários.




