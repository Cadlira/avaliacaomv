### Considerações

A arquitetura consiste de um total de seis projetos, sendo eles:
- **config-server:** 
	- Servidor responsável por prover os arquivos de configuração para os demais projetos. utilizando o ***spring-cloud-config-server***.
- **discovery-server:**
	- Servidor responsável pelo registro dos serviços da arquitetura utilizando o *** spring-cloud-starter-netflix-eureka-server***.
- **gateway-server:**
	- Servidor de endereçamentos dos serviços da arquitetura, também responsável (junto com o ribbon) pelo load balance na chamada dos serviços registrados no servidor de discovery. foi utilizado ***spring-cloud-starter-netflix-zuul***
- **uaa-server:**
	- Servidor de autenticação SSO (Single sign on). Responsável pela autenticação das requisições realizadas em nossa API utilizando Spring Oauth2. Também possibilita que a WEB UI realize o login em um servidor SSO sem a necessidade de uma tela de login para a aplicação web. Esse serviço possui um banco de dados implementado em  [H2 Database](http://www.h2database.com) com uma tabela (user:{username, password}) gerenciada pelo [Liquibase](https://www.liquibase.org/). Também existe nesse sistemas 3 telas (login, register e authorize), que foram implementadas utilizando jquery e bootstrap. Foi utilizado ***spring-cloud-starter-oauth2***
- **user-rs:**
	- Servidor responsável por prover os recursos referente ao cadastro de usuários. Este serviço também possui um base de dados em [H2 Database](http://www.h2database.com) com uma tabela (user: {id, username, name, email}) gerenciada pelo [Liquibase](https://www.liquibase.org/). Este serviço não armazena a senha do usuário. Para esse serviço foi disponibilizado uma API REST para manipulação dos usuários, bem como adicionado o framework [Swagger](https://swagger.io/) para realização de testes na API (embora esteja sendo exibido no contexto do swagger não está funcionado as chamadas devido problemas de autenticação).
- **web-ui:**
	- Por fim foi disponibilizado um projeto web, desenvolvido em Angular 4 para realizar as chamadas na API.

###Solicitações não atendidas
Das tecnologias/frameworks solicitados na avaliação existem algumas das quais eu nunca havia trabalhado, que foram: Spring Oauth2, JPA Specification e Swagger.

E por problemas de tempo para implementação (mesmo sendo 7 dias para entrega essa semana foi um pouco complicada).

Dexando as desculpas de lado, vamos para o que não foi atendido:

- **JPA Especification:**: Embora não aparente ser complicado o seu desenvolvimento decidi por implementar os meus repositorios utilizando JpaRepository mesmo, pois como tinha que escolher o que aprender devido ao tempo decidi focar em Spring Oauth2, visto que era o cerne da avaliação.
- **Swagger:** Apesar de está implementado no serviço user-rs, o mesmo não está 100% funcional, pois, embora esteja listando os serviços disponíveis, a execução do serviço pela interface está apresentando erro de autenticação.
- **Internacionalização:** Mesmo já trabalhando com internacionalização i18n em todos os projetos, acabei não adicionado internacionalização nas interfaces WEB (uaa-server e web-ui). No uaa-server utilizaria a internacionalização através de JQuery i18n e no web-ui utilizaria a internacionalização do proprio angular [Angular i18n](https://angular.io/guide/i18n).

###Execução dos serviços
Todos os serviços foram diponibilizados como imagens Docker publicadas no [Docker Hub](https://hub.docker.com/r/leolira/).

Nesse repositório foi disponibilizado um arquivo "docker-compose.yml" confome abaixo:
```
version: '3'
services:
    config-server:
        image: leolira/config-server
        environment:
            - 'CONFIG_GIT_URL=https://github.com/Cadlira/config.git'
        ports:
            - '8888:8888'
        networks:
            - mv_net
    discovery-server:
        image: leolira/discovery-server
        depends_on:
            - config-server
        ports:
            - '8761:8761'
        networks:
            - mv_net
    uaa-server:
        image: leolira/uaa-server
        depends_on:
            - config-server        
            - discovery-server
        ports:
            - '9000:9000'
        networks:
            - mv_net            
    user-rs:
        image: leolira/user-rs
        depends_on:
            - config-server        
            - discovery-server
        ports:
            - '8081:8081'
        networks:
            - mv_net                        
    gateway-server:
        image: leolira/gateway-server
        depends_on:
            - config-server
            - discovery-server
        ports:
            - '3001:3001'
        networks:
            - mv_net            
    web-ui:
        image: leolira/web-ui
        environment:
            - 'API_URL=http://localhost:3001/'            
        ports:
            - '80:80'
        networks:
            - mv_net
networks:
    mv_net: 
```

Para executar é necessário informar o endereço do servidor de gateway para a aplicação web:
```
 - 'API_URL=http://localhost:3001/'  <-- Deve ser alterado para a url caso diferente.
```
Por fim, navegar até a pasta que encontra-se o arquivo e executar o seguinte comando:
`$ docker-compose up`
ou
`$ docker-compose up -d`
Caso queiramos executar em background


