#language: pt

@TESTE
Funcionalidade: UC07 - CDP Informações - ATUALIZAÇÃO DO SISTEMA CDP no Administração

  Cenário: CT01 - Adicionar atualização do sistema no Administração
    Dado que eu acesso a url "http://localhost:4200"
    Quando faço o drag and drop da tabela 1 para a tabela 2
    Então o elemento da tabela 1 deve ir para a tabela 2