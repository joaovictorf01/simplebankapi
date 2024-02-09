# Usando a imagem do Java 17
FROM openjdk:17-oracle

# Criando um volume para armazenar temporariamente os arquivos
VOLUME /tmp

# Permite a passagem de opções de JVM via argumento na construção do Docker
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS

# Copia o jar da aplicação para a imagem Docker
COPY target/simplebankapi-0.0.1-SNAPSHOT.jar simplebankapi.jar

# Expõe a porta 8080 para acessar a aplicação
EXPOSE 8080

# Define o comando para iniciar a aplicação
ENTRYPOINT exec java $JAVA_OPTS -jar simplebankapi.jar
# Para projetos Spring Boot, use o entrypoint abaixo para reduzir o tempo de inicialização do Tomcat
# ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar simplebankapi.jar
