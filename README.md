# API Cartão de crédito FIAP

API de um sistema de cartão de crédito para alunos da FIAP que conta com recursos como:
- Carga de arquivo de importação;
- Cadastro de alunos;
- Realização de transações;
- Download de extrato no formato txt.

### Setup

- Preenchar suas configurações da api e de banco de dados no arquivo `application.yaml`.
```text
// src/main/resources/application.yaml

```
### Run
Execute na raiz do projeto a task `bootRun` do gradle.

- Windows
  `gradlew bootRun`
- Linux / Mac
  `./gradlew bootRun`


### Documentação da API
Executando localmente a API na porta 8080, a documentação estará disponivel em [Swagger](http://localhost:8080/api/swagger-ui.html).
