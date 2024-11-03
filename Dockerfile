#Usando uma imagem base do Java com Maven
FROM maven AS build

#Definindo o diretório de trabalho
WORKDIR /app

#Copiando o arquivo pom.xml e a pasta com o código-fonte
COPY pom.xml .
COPY src ./src

#Executando o comando de build para criar o .jar
RUN mvn clean package -DskipTests

#Usando uma imagem base do Java para rodar a aplicação
FROM openjdk:21-jdk

#Definindo o diretório de trabalho dentro do contêiner
WORKDIR /app

#Copiando o arquivo jar da aplicação do estágio de build
COPY --from=build /app/target/coletas.jar app.jar

#Externalizando a porta usada pela aplicação (8080, por exemplo)
EXPOSE 8080

#Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
