#language: pt

@regressivo
Funcionalidade: Deletar coleta

  Contexto: Cadastros de coletas com sucesso
    Dado que eu tenha os seguintes dados de coleta:
      | campo          | valor        |
      | nomeMotorista  | Yuri Novais  |
      | veiculoColeta  | CAMINHAO_001 |
      | statusColeta   | EM_ROTA      |
      | dataColeta     | 2024-03-07   |
    Quando eu enviar a requisicao para o endpoint "/coletas" para cadastro de coletas
    Então o status code deve retornar 201

  Cenario: Deve ser possivel deletar uma coleta
    Dado que eu recupere o ID da coleta criado no contexto
    Quando eu enviar a requisicao para com o ID para o endpoint "/coletas" de delecao de coleta
    Então o status code deve retornar 204