# Sistema de Feedback de Franquia de Restaurantes


<img alt="Static Badge" src="https://img.shields.io/badge/Java-17-brown?style=for-the-badge&logo=openjdk&logoColor=ED8B00&color=ED8B00">
<img alt="Static Badge" src="https://img.shields.io/badge/Spring%20Boot-3.2.3-%236DB33F?style=for-the-badge&logo=springboot&logoColor=%236DB33F">
<img alt="Static Badge" src="https://img.shields.io/badge/MongoDB-7.0.7-%2347A248?style=for-the-badge&logo=mongodb&logoColor=%2347A248">
<img alt="Static Badge" src="https://img.shields.io/badge/Kafka-3.7.0-%23231F20?style=for-the-badge&logo=apachekafka&logoColor=%23231F20">
<img alt="Static Badge" src="https://img.shields.io/badge/junit5-5-%2325A162?style=for-the-badge&logo=junit5&logoColor=%2325A162">
<img alt="Static Badge" src="https://img.shields.io/badge/Grafana--%23F46800?style=for-the-badge&logo=grafana&logoColor=%23F46800">
<img alt="Static Badge" src="https://img.shields.io/badge/redis-7.2.4-%23DC382D?style=for-the-badge&logo=redis&logoColor=%23DC382D">
<img alt="Static Badge" src="https://img.shields.io/badge/Swagger-3-%2385EA2D?style=for-the-badge&logo=swagger&logoColor=%2385EA2D">



## 💻 Sobre o projeto
Este projeto de back-end Java é construído com Spring Boot e implementa uma arquitetura de microserviços para coletar e analisar feedbacks de clientes de uma franquia de restaurantes. O sistema é projetado para processar formulários de feedback e fornecer análises estatísticas para insights operacionais e estratégicos.

## Microsserviços
O sistema é composto por cinco microserviços principais:

1. **Gateway**: Atua como um ponto de entrada para as requisições, direcionando-as para os serviços apropriados.
2. **Eureka**: Serve como um servidor de descoberta de serviços, mapeando todos os microserviços disponíveis.
3. **FeedbacksCollector**: Uma API que recebe feedbacks dos clientes e os encaminha para o serviço FeedbacksStorage.
4. **FeedbacksStorage**: Responsável por armazenar os feedbacks recebidos no banco de dados MongoDB e realização de análises.
5. **FeedbacksAnalysis**: Uma API que processa solicitações de análise dos feedbacks. As análises podem ser baseadas em critérios como região, idade ou personalizadas, utilizando qualquer parâmetro do formulário.

## ⚙️ Funcionalidades

- Enviar feedback com base em um restaurante selecionado, avaliando os seguintes itens:
  - Nota de 1 a 10 da experiência 
  - Qualidade da refeição 
  - Se o pedido veio conforme esperado
  - Tempo de espera 
  - Atendimento
  - Ambiente
    - Além disso, é preciso incluir a idade e gênero para análises mais detalhadas

- Solicitar análises dos feedbacks tendo como referência:
  - Região
    - Os feedbacks são separados pelas regiões do Brasil (Norte, Nordeste, Centro-Oeste, Sudeste e Sul)
    - Pode receber um intervalo de datas para parametrizar os feedbacks selecionados
  - Idade
    - Os feedbacks são separados por grupos de idades (16-24, 25-35, 36-50, 51-70 e 71-110)
    - Pode receber um intervalo de datas para parametrizar os feedbacks selecionados
  - Personalizado
    - A análise pode receber os seguintes parâmetros, podendo ser combinados:
      - Intervalo de datas
      - Intervalo de idades
      - Intervalo de notas
      - Região
      - Gênero
      - Qualidade da refeição
      - Se o pedido veio conforme esperado
      - Tempo de espera
      - Atendimento
      - Ambiente 

## Como executar o projeto

### Pré-requisitos
- JDK
- Maven disponível no PATH ou IDE
- Docker
- Git

### Passos

```
# Clone deste repositório
$ git clone https://github.com/matheus1629/restaurant-franchise-feedback.git

# Inicializar container kafka
docker run -d --name my-kafka -p 9092:9092 apache/kafka:3.7.0

# Inicializar container mongoDB
docker run -d --name my-mongodb -p 27017:27017 mongo:7.0.7

# Inicializar container redis
docker run -d --name my-redis -p 6379:6379 redis:7.2.4

# Criar os arquivos .jar para os microsserviços
$ cd eurekaserver && mvn package
$ cd feedbackscollector && mvn package
$ cd feedbacksstorage && mvn package
$ cd feedbacksanalysis && mvn package
$ cd gatewayserver && mvn package

# Executar os microsserviços (Seguir a ordem de execução)
java -jar eurekaserver/target/eurekaserver-0.0.1-SNAPSHOT.jar
java -jar feedbackscollector/target/feedbackscollector-0.0.1-SNAPSHOT.jar
java -jar feedbacksstorage/target/feedbacksstorage-0.0.1-SNAPSHOT.jar
java -jar feedbacksanalysis/target/feedbacksanalysis-0.0.1-SNAPSHOT.jar
java -jar gatewayserver/target/gatewayserver-0.0.1-SNAPSHOT.jar
```

[Restaurant Feedback.postman_collection.json](..%2FRestaurant%20Feedback.postman_collection.json)

 
## Comunicação e Armazenamento
- A comunicação entre os serviços FeedbacksCollector, FeedbacksStorage e FeedbacksAnalysis é realizada de forma assíncrona através do Apache Kafka.
- O serviço FeedbacksStorage utiliza o Redis para armazenar resultados de análises. Isso permite que análises com os mesmos parâmetros sejam recuperadas rapidamente sem a necessidade de recalculá-las.

## Resiliência e Monitoramento
- O Gateway utiliza o padrão de Circuit Breaker para os serviços FeedbacksCollector e FeedbacksAnalysis, garantindo a estabilidade do sistema mesmo em caso de falhas parciais.
- Métricas e traces são visualizados graficamente através do Grafana, proporcionando uma visão clara do desempenho e saúde do sistema.

## Testes
- Testes foram realizados nos serviços na camada de Service utilizando JUnit 5 e Mockito.

## Docker
- Executar `mvn compile jib:dockerBuild` em cada microsserviço para criar sua imagem.
- No diretório **docker-compose/default/** executar `docker-compose up` para rodar o projeto em containers.


 