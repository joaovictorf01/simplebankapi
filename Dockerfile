# Etapa de build
# Use a imagem do JDK 17 para compilar o projeto
FROM maven:3.8.4-openjdk-17 as build

# Copia os arquivos do projeto para o diretório de trabalho no contêiner
COPY src /home/app/src
COPY pom.xml /home/app

# Define o diretório de trabalho para o diretório onde os arquivos foram copiados
WORKDIR /home/app

# Executa o Maven para construir o aplicativo (empacota o JAR)
RUN mvn -f /home/app/pom.xml clean package

# Etapa de execução
# Use a imagem do JRE 17 para rodar o aplicativo
FROM openjdk:17-oracle

# Cria um volume para armazenar temporariamente os arquivos
VOLUME /tmp

# Passagem de opções de JVM via argumento na construção do Docker
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS

# Copia o JAR construído na etapa de build para a imagem de execução
COPY --from=build /home/app/target/simplebankapi-0.0.1-SNAPSHOT.jar /usr/local/lib/simplebankapi.jar

# Expõe a porta 8080
EXPOSE 8080

# Define o comando para iniciar a aplicação
ENTRYPOINT exec java $JAVA_OPTS -jar /usr/local/lib/simplebankapi.jar
