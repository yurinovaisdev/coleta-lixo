#language: pt

@regressivo
Funcionalidade: Validar contrato no cadastro de coletas

  Cenario: Validar contrato cadastro de coletas bem sucedido
    Dado que eu tenha os seguintes dados de coleta:
      | campo          | valor        |
      | nomeMotorista  | Yuri Novais  |
      | veiculoColeta  | CAMINHAO_001 |
      | statusColeta   | EM_ROTA      |
      | dataColeta     | 2024-03-07   |
    Quando eu enviar a requisicao para o endpoint "/coletas" para cadastro de coletas
    Então o status code deve retornar 201
    E que o arquivo de contrato esperado é o "Cadastro bem-sucedido da coleta"
    Então a resposta da requisição deve estar em conformidade com o contrato selecionado