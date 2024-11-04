
# COLETAS-LIXO

Este projeto contém um serviço Java para gerenciamento de coletas, configurado para execução em contêineres Docker.

## Como Executar

1. Clone o repositório:
   ```bash
   git clone https://github.com/yurinovaisdev/coleta-lixo.git
   ```

2. Acesse o diretório do projeto:
   ```bash
   cd coleta-lixo
   ```

3. Construa a imagem Docker:
   ```bash
   docker build -t api-coletas-image .
   ```

4. Execute o contêiner a partir da imagem criada, mapeando a porta 8080 para acessar a aplicação:
   ```bash
   docker run -d --name my-app-coletas -p 8080:8080 api-coletas-image
   ```

5. Para validar o serviço, acesse a URL de verificação de saúde:
    - [http://localhost:8080/coletas/health](http://localhost:8080/coletas/health)

## Dockerfile e Configuração de Porta

Este projeto inclui um Dockerfile que utiliza uma imagem base do Java. O Dockerfile foi configurado para usar a variável de ambiente `PORT`, definida como `8080` para compatibilidade com o ambiente Railway. A aplicação deve estar configurada para ler essa variável, ajustando a porta de execução conforme necessário.

