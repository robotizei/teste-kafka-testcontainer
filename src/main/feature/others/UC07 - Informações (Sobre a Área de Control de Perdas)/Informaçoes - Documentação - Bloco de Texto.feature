#language: pt

@CDP @UC07 @WEB @SPRINT01 @DOCUMENTACAO_BLOCOTEXTO
Funcionalidade: UC07 - CDP Informações - Documentação - Bloco de Texto

  Cenário: CT01 - Preencher os dados Documentação - Bloco de Texto CDP no Administração
    Dado que eu navegue até o Administração
    E efetue login com perfil com permissão de Admin
    E acesso a TAB CDP
    E acesso a TAB Documentação no Administração
    Então preencho a Documentação oficial do projeto
    E clico em Adicionar bloco de texto na Documentação no Administração
    Entao preencho os dados do bloco de texto Documentação no Administração
    E clico no botão Salvar Informações do Sobre a CDP
    Entao deve exibir a snackBar com a mensagem "Informações salvas com sucesso"
    E valido que as informações de Documentação oficial do projeto no Administração persistiram
    E valido que o bloco de texto Documentação do projeto no Administração persistiram

  Cenário: CT02 - Valida os dados Documentação - Bloco de Texto CDP
    Dado que eu navegue até o Controle de Perdas
    E efetue login com perfil com permissão de Visualização
    E acesso a TAB Informações
    E acesso a TAB DOCUMENTAÇÃO no CDP
    Então valido que o bloco de texto Documentação do projeto no CDP

  Cenário: CT03 - Excluir os dados Documentação - Bloco de Texto CDP no Administração
    Dado que eu navegue até o Administração
    E efetue login com perfil com permissão de Admin
    E acesso a TAB CDP
    E acesso a TAB Documentação no Administração
    Entao clico em Excluir bloco de texto na Documentação no Administração
    E confirmo a exclusão do bloco de texto no modal
    E clico no botão Salvar Informações do Sobre a CDP
    Entao deve exibir a snackBar com a mensagem "Informações salvas com sucesso"


