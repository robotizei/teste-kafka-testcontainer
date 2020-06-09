#language: pt

@CDP @UC07 @WEB @SPRINT01 @VERSAO
Funcionalidade: UC07 - CDP Informações - ATUALIZAÇÃO DO SISTEMA CDP no Administração

  Cenário: CT01 - Adicionar atualização do sistema no Administração
    Dado que eu navegue até o Administração
    E efetue login com perfil com permissão de Admin
    E acesso a TAB CDP
    E acesso a TAB Documentação no Administração
    E clico para adicionar uma nova versão no Administração
    Então adiciono uma nova versão no Administração
    E clico no botão Salvar no modal de Adicionar Versão
    Entao deve exibir a snackBar com a mensagem "Versão salva com sucesso"
    E valido que as informações da atualização do sistema persistiram no Administração

  Cenário: CT02 - Valida os dados sobre a equipe CDP no CDP
    Dado que eu navegue até o Controle de Perdas
    E efetue login com perfil com permissão de Visualização
    E acesso a TAB Informações
    E acesso a TAB Atualização no CDP
    Então valido as informações da atualização do sistema

  Cenário: CT03 - Editar atualização do sistema no Administração
    Dado que eu navegue até o Administração
    E efetue login com perfil com permissão de Admin
    E acesso a TAB CDP
    E acesso a TAB Documentação no Administração
    E clico para editar a versão da linha 1 no Administração
    Então edito a nova versão no Administração
    E clico no botão Salvar no modal de Adicionar Versão
    Entao deve exibir a snackBar com a mensagem "Versão salva com sucesso"
    E valido que as informações editadas da atualização do sistema persistiram no Administração

  Cenário: CT04 - Excluir atualização do sistema no Administração
    Dado que eu navegue até o Administração
    E efetue login com perfil com permissão de Admin
    E acesso a TAB CDP
    E acesso a TAB Documentação no Administração
    Então clico no botão para excluir a versão da linha 1 no Administração
    E clico no botão Confirmar no modal de Adicionar Versão
    Entao deve exibir a snackBar com a mensagem "Versão excluída com sucesso"

