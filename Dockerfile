# Usando uma imagem base do Java
FROM openjdk:21-jdk

# Definindo o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copiando o arquivo jar da sua aplicação para o contêiner
COPY coletas.jar app.jar

EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
