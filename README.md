<h1 align="center"> Transaction Analyzer </h1>

![Badge Em Desenvolvimento](https://img.shields.io/static/v1?label=Status&message=Em+Desenvolvimento&color=yellow&style=for-the-badge)
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
- `Importação de arquivo de transações`: Arquivos do tipo .csv contendo transações financeiras podem ser enviados, o arquivo enviado passa por uma série de validações, sendo salvo apenas as transações com informações válidas.
- `Histórico de importações`: Um histórico de importações realizadas é mostrado na tela principal da aplicação.
- `Detalhes de cada importação`: Apartir do histórico de importações pode-se acessar os detalhes de cada arquivo importado, como: data de importação, quem importou, data das transações, e uma lista de todas as transações de tal arquivo.
- `Editar usuário`: Usuários autenticados podem editar informações de outros usuários.
- `Deletar usuário`: Usuários autenticados podem desabilitar o acesso de outros usuário, usuário NÃO podem se auto desabilitar.
- `Lista de usuários`: Uma tela para mostrar todos os usuários cadastrados e suas informações, assim como os botões de *EDITAR* e *DELETAR*.
- `Análise de transações`: Uma tela para o usuário informar o mês e ano que deseja a análise, a aplicação mostra as transações suspeitas (transações igual ou superior a R$100.000,00), contas suspeitas (Se o somatório de movimentações no mês for superior a R$1.000.000,00) e agências suspeitas (Se o somatório de movimentações no mês for superior a R$1.000.000.000,00)

## :toolbox: Tecnologias
- `VSCode`
- `Java 17`
- `Maven`
- `Spring Boot, Spring MVC, Spring Data JPA, Spring Security`
- `H2 database`
- `Thymeleaf`
- `Lombok`
- `HTML5`
- `Bootstrap`
- `MySQL`