# desafio-smu-investimentos
Projeto voltado para cadastros de novos investidores

# investor

  Sistema de controle de contas para investidores simples. Nele criei duas APIs Rest(auth-server, account) em Java 17 que 
  permite fazer o cadastro de usuários investidores, suas contas e operações como deposito, saque, transferência e extrato 
  utilizando Spring (Boot, Data, Webflux ...).

  Este projeto é desafio técnico para vaga de desenvolvedor Java.

## Pré-requisitos

1. [Java] versão 17 ou superior - Obs: para executar os arquivos .jar contidos no diretório raiz de cada API.
2. [Docker] versão latest
3. [Postman] versão latest - Obs: na API account tem um diretório chamado postman onde compartilhei a collection para testes.


### Iniciando o banco de Dados

```
docker run --name investor-mysql -p 3306:3306 -p 33060:33060 -e MYSQL_ROOT_HOST='%' -e MYSQL_ROOT_PASSWORD='senha123' -d mysql/mysql-server:latest
```

## Iniciando a aplicação

Usando Java

- `java -jar your_path/auth-server.jar`
- `java -jar your_path/account.jar`

## Após iniciar tudo

# No postman os endpoints estão separados por:
  - Auth(Authorization Server)
  - Usuários(Investidores ou ADMs)
  - Conta com as Operações(Deposito, Saque, Transferência e Extrato)
  
# A aplicação já cria um usuário adm padrão para testes com os meus dados de login:
- user: admin@email.com
- senha: 123456
  
# Para os demais cadastros e operações basta importar a collection contida no caminho especificado abaixo:
## Obs: na API account tem um diretório chamado postman onde compartilhei a collection para testes.
