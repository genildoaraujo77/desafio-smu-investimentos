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

No postman os endpoints estão separados por:
  - Auth(Authorization Server)
  - Usuários(Investidores ou ADMs)
  - Conta com as Operações(Deposito, Saque, Transferência e Extrato)
  
A aplicação já cria um usuário adm padrão para testes com os meus dados de login:
- user: admin@email.com
- senha: 123456
  
###

##

SMU Investimento Contas + Auth
Collection para gerenciamento e cadastro de investidores e suas contas na SMU Investimentos.

Auth
Add folder description…
GET
Requisição Authorization Code pelo (Navegador)
http://localhost:8082/oauth2/authorize?response_type=code&client_id=smuaccount&state=abc&redirect_uri=https://oidcdebugger.com/debug&scope=users:read users:write accounts:write accounts:read
Add request description…
Query Params
response_type
code

client_id
smuaccount

state
abc

redirect_uri
https://oidcdebugger.com/debug

scope
users:read users:write accounts:write accounts:read

POST
Autenticação com Authorization Code - SMUCONTA
http://localhost:8082/oauth2/token
Add request description…
Authorization
Basic Auth
Username
<username>

Password
<password>

Body
urlencoded
grant_type
authorization_code

code
Sg8fuihqKPqJT1bFDpzYqouPo1yvXdZB4SDNX5LxcSuPD-SOyWLfA4LPYkPPxYLUjYiParnQ-5Lu4M9Fq3MlV2V8yqnREx1mVR2fEn0ik3KIUi4XIIsGnQR3AXLG6nx0

redirect_uri
https://oidcdebugger.com/debug

POST
Autenticação com Refresh Token - SMUCONTA
http://localhost:8082/oauth2/token
Add request description…
Authorization
Basic Auth
Username
<username>

Password
<password>

Body
urlencoded
grant_type
refresh_token

refresh_token
nFgER6UrCnNr1WtWHO5klLfgbs8oAIEA4lYuMQnNTiMJePJXlLRd0o2npYhnS8QGyYEfks0Q1dcRuYV9hljnlCNcw9mOBYShSUZOKP-XeMRt1atthXut9cZ5tXIiw_CF

POST
Revogar permissões da aplicação client
http://localhost:8082/oauth2/revoke
Add request description…
Authorization
Basic Auth
Username
<username>

Password
<password>

Body
urlencoded
token
JtE21smC2ZL7t7G4bFxS8rK53iQOlAx4Do7Rc-fAQ55EcVBce4QfNPZGFEZZMciOyzsJfQ_E9_BNEsNIvAkW1_x_76F6IVfAY25TJ8V7zgFY2D5Wjme0eiwJJot9R9uK

Usuarios
Add folder description…
POST
Criar Usuario e retorna com a conta criada
localhost:8080/users
Add request description…
Body
raw (json)
json
{
    "email": "gege.araujo77@outlook.com",
    "name": "Gege Cardoso",
    "cpfCnpj": "12345678901",
    "password": "123456",
    "type": "ADMIN"
}
GET
Listar Usuarios
localhost:8080/users
Add request description…
Authorization
Bearer Token
Token
<token>

GET
Buscar Usuario por Id
localhost:8080/users/4
Add request description…
Authorization
Bearer Token
Token
<token>

Conta
Add folder description…
POST
Fazer deposito
http://localhost:8080/accounts/40.00/000002
Add request description…
Authorization
Bearer Token
Token
<token>

POST
Fazer saque
http://localhost:8080/accounts/draw/1.00/000002
Add request description…
Authorization
Bearer Token
Token
<token>

POST
Fazer transferencia para outra conta
http://localhost:8080/accounts/000002/transfer/10.00/000001
Add request description…
Authorization
Bearer Token
Token
<token>

GET
Exibir extrato da conta
http://localhost:8080/accounts/extract/000002
Add request description…
Authorization
Bearer Token
Token
<token>

POST
Fazer bloqueio da conta por user adm
http://localhost:8080/accounts/block-account/000002
Add request description…
Authorization
Bearer Token
Token
<token>
