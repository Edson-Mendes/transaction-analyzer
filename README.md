<h1 align="center"> Transaction Analyzer </h1>

![Badge Concluído](https://img.shields.io/static/v1?label=Status&message=Concluído&color=success&style=for-the-badge)
![Badge Java](https://img.shields.io/static/v1?label=Java&message=17&color=orange&style=for-the-badge&logo=java)
![Badge Spring](https://img.shields.io/static/v1?label=Spring&message=v2.6.6&color=brightgreen&style=for-the-badge&logo=spring)
![Badge Maven](https://img.shields.io/static/v1?label=Maven&message=v3.8.4&color=critical&style=for-the-badge&logo=apache+maven)

## :book: Resumo do projeto
Transaction Analyzer é uma aplicação para análise de transações financeiras e identificação de possíveis transações suspeitas. 

O projeto foi proposto pela Alura no Challenge Backend 3ª edição.

## :hammer: Funcionalidades
- `Autenticação de usuário`
  - `Login`: Login de usuário informando email e senha.
  - `Cadastrar usuário`: Usuários autenticados podem cadastrar outros usuários, cadastrando nome e email, a senha é gerada automaticamente e enviada para o email cadastrado.
  <hr/>
- `Importação de arquivo de transações`: Arquivos do tipo .csv contendo transações financeiras podem ser enviados, o arquivo enviado passa por uma série de validações, sendo salvo apenas as transações com informações válidas.
  
  - `Formato do Arquivo CSV:` ```BANCO_ORIGEM;AGÊNCIA_ORIGEM;CONTA_ORIGEM;BANCO_DESTINO;AGÊNCIA_DESTINO;CONTA_DESTINO;VALOR;HORA_E_DATA``` 
  - `Exemplo:` ```BANCO BRADESCO;0055;12345-6;NUBANK;0001;00001-1;80000;2022-09-22T11:32:00```
  <hr/>
- `Histórico de importações`: Um histórico de importações realizadas é mostrado na tela principal da aplicação.
  <hr/>
- `Detalhes de cada importação`: Apartir do histórico de importações pode-se acessar os detalhes de cada arquivo importado, como: data de importação, quem importou, data das transações, e uma lista de todas as transações de tal arquivo.
  <hr/>
- `Editar usuário`: Usuários autenticados podem editar informações de outros usuários.
  <hr/>
- `Deletar usuário`: Usuários autenticados podem desabilitar o acesso de outros usuário, usuário NÃO podem se auto desabilitar.
  <hr/>
- `Lista de usuários`: Uma tela para mostrar todos os usuários cadastrados e suas informações, assim como os botões de *EDITAR* e *DELETAR*.
  <hr/>
- `Análise de transações`: Uma tela para o usuário informar o mês e ano que deseja a análise, a aplicação mostra as transações suspeitas (transações igual ou superior a R$100.000,00), contas suspeitas (Se o somatório de movimentações no mês for superior a R$1.000.000,00) e agências suspeitas (Se o somatório de movimentações no mês for superior a R$1.000.000.000,00).

## :toolbox: Tecnologias
- `VSCode`
- `Java 17`
- `Maven`
- `Spring Boot, Spring MVC, Spring Data JPA, Spring Security`
- `H2 database`
- `Thymeleaf`
- `Lombok`
- `Bean Validation`
- `HTML5`
- `Bootstrap`
- `MySQL`
- `JUnit 5`
- `Mockito`

## :hammer_and_wrench: Deploy
O deploy da aplicação foi realizado no **Heroku**, você pode testar/brincar/usar [aqui](https://transactions-analyzer-app.herokuapp.com), fique a vontade para usar o login **admin@email.com.br** e senha **123999**

OBS: As aplicações que usam conta gratuita do heroku *adormecem* se ficarem inativas, então pode ser que a primeira requisição demore um pouco, apenas seja paciente :wink:.

## :gear: Atualizações futuras
- [ ] Suporte a upload de arquivos em formato XML
- [x] Testes automatizados
- [ ] Responsividade das páginas
- [ ] Possibilidade de alterar senha
- [ ] Aba para exemplificar o formato dos dados
- [ ] Aba para mostrar bancos salvos no sistema
