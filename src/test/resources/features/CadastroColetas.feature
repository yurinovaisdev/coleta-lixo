#language: pt

@regressivo
Funcionalidade: Cadastro de coleta

  Cenario: Cadastros de coletas com sucesso
    Dado que eu tenha os seguintes dados de coleta:
      | campo          | valor        |
      | nomeMotorista  | Yuri Novais  |
      | veiculoColeta  | CAMINHAO_001 |
      | statusColeta   | EM_ROTA      |
      | dataColeta     | 2024-03-07   |
    Quando eu enviar a requisicao para o endpoint "/coletas" para cadastro de coletas
    Então o status code deve retornar 201

  Cenario: Cadastros de coletas sem sucesso
    Dado que eu tenha os seguintes dados de coleta:
      | campo          | valor        |
      | nomeMotorista  | Yuri Novais  |
      | veiculoColeta  | CAMINHAO_001 |
      | statusColeta   | EM_SEPARACAO |
      | dataColeta     | 2024-03-07   |
    Quando eu enviar a requisicao para o endpoint "/coletas" para cadastro de coletas
    Então o status code deve retornar 400
    E o corpo de resposta de erro de api deve retornar a mensagem "Dados fornecidos estão em formato inválido."

