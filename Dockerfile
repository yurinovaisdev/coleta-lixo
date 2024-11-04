# Usando uma imagem base do Java
FROM openjdk:21-jdk

# Definindo o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copiando o arquivo jar da sua aplicação para o contêiner
COPY coletas.jar app.jar

# Expondo a porta que a aplicação irá utilizar (ajuste para o padrão Railway)
EXPOSE 10000

# Definindo a variável de ambiente para a porta
ENV PORT=10000

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
