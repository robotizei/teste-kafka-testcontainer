#language: pt

@CDP @UC07 @WEB @SPRINT01 @SOBRESETOR
Funcionalidade: UC07 - CDP Informações - Sobre o setor CDP no Administração

  Cenário: CT01 - Preencher os dados sobre o setor CDP no Administração
    Dado que eu navegue até o Administração
    E efetue login com perfil com permissão de Admin
    E acesso a TAB CDP
    E acesso a TAB SOBRE no Administração
    Então preencho os dados sobre o setor no Administração
    E clico no botão Salvar Informações do Sobre a CDP
    Entao deve exibir a snackBar com a mensagem "Informações salvas com sucesso"
    E valido que as informações de sobre o setor no Administração persistiram

  Cenário: CT02 - Valida os dados sobre o setor CDP no CDP
    Dado que eu navegue até o Controle de Perdas
    E efetue login com perfil com permissão de Visualização
    E acesso a TAB Informações
    E acesso a TAB SOBRE no CDP
    Então valido as informações sobre o setor no CDP
