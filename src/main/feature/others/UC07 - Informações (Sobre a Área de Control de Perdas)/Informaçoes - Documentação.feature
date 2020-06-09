#language: pt

@CDP @UC07 @WEB @SPRINT01 @DOCUMENTACAO
Funcionalidade: UC07 - CDP Informações - Documentação

  Cenário: CT01 - Preencher os dados sobre o setor CDP no Administração
    Dado que eu navegue até o Administração
    E efetue login com perfil com permissão de Admin
    E acesso a TAB CDP
    E acesso a TAB Documentação no Administração
    Quando preencho a Documentação oficial do projeto
    E clico no botão Salvar Informações do Sobre a CDP
    Entao deve exibir a snackBar com a mensagem "Informações salvas com sucesso"
    E valido que as informações de Documentação oficial do projeto no Administração persistiram

  Cenário: CT02 - Valida os dados sobre o setor CDP no CDP
    Dado que eu navegue até o Controle de Perdas
    E efetue login com perfil com permissão de Visualização
    Quando acesso a TAB Informações
    E acesso a TAB DOCUMENTAÇÃO no CDP
    Então valido as informações de Documentação oficial do projeto  no CDP
