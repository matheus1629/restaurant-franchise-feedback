# Sistema de Feedback de Franquia de Restaurantes


<img alt="Static Badge" src="https://img.shields.io/badge/Java-17-brown?style=for-the-badge&logo=openjdk&logoColor=ED8B00&color=ED8B00">
<img alt="Static Badge" src="https://img.shields.io/badge/Spring%20Boot-3.2.3-%236DB33F?style=for-the-badge&logo=springboot&logoColor=%236DB33F">
<img alt="Static Badge" src="https://img.shields.io/badge/MongoDB-7.0.7-%2347A248?style=for-the-badge&logo=mongodb&logoColor=%2347A248">
<img alt="Static Badge" src="https://img.shields.io/badge/Kafka-3.7.0-%23231F20?style=for-the-badge&logo=apachekafka&logoColor=%23231F20">
<img alt="Static Badge" src="https://img.shields.io/badge/junit5-5-%2325A162?style=for-the-badge&logo=junit5&logoColor=%2325A162">
<img alt="Static Badge" src="https://img.shields.io/badge/Grafana--%23F46800?style=for-the-badge&logo=grafana&logoColor=%23F46800">
<img alt="Static Badge" src="https://img.shields.io/badge/redis-7.2.4-%23DC382D?style=for-the-badge&logo=redis&logoColor=%23DC382D">


## Descrição
Este projeto de back-end Java é construído com Spring Boot e implementa uma arquitetura de microserviços para coletar e analisar feedbacks de clientes de uma franquia de restaurantes. O sistema é projetado para processar formulários de feedback e fornecer análises estatísticas para insights operacionais e estratégicos.

## Microserviços
O sistema é composto por cinco microserviços principais:

1. **Gateway**: Atua como um ponto de entrada para as requisições, direcionando-as para os serviços apropriados.
2. **Eureka**: Serve como um servidor de descoberta de serviços, mapeando todos os microserviços disponíveis.
3. **FeedbacksCollector**: Uma API que recebe feedbacks dos clientes e os encaminha para o serviço FeedbacksStorage.
4. **FeedbacksStorage**: Responsável por armazenar os feedbacks recebidos no banco de dados MongoDB e realização de análises.
5. **FeedbacksAnalysis**: Uma API que processa solicitações de análise dos feedbacks. As análises podem ser baseadas em critérios como região, idade ou personalizadas, utilizando qualquer parâmetro do formulário.

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


 