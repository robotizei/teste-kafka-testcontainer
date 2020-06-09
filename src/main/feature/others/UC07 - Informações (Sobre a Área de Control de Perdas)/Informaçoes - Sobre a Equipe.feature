#language: pt

@CDP @UC07 @WEB @SPRINT01 @SOBREEQUIPE
Funcionalidade:  UC07 - CDP Informações - Sobre a Equipe

  Cenário: CT01 - Adicionar novo membro de equipe
    Dado que eu navegue até o Administração
    E efetue login com perfil com permissão de Admin
    E acesso a TAB CDP
    E acesso a TAB SOBRE no Administração
    E seleciono opção para exibir dados da equipe
    E clico no botão Adicionar membro
    Entao preencho a modal de membro
    E clico no botão Salvar do dialog de adicionar membro
    Entao deve exibir a snackBar com a mensagem "Registro salvo com sucesso."
    E valido o membro de equipe adicionado na lista na linha 1
    E clico no botão Salvar Informações do Sobre a CDP
    Entao deve exibir a snackBar com a mensagem "Informações salvas com sucesso"

  Cenário: CT02 - Valido os dados preenchidos no CDP
    Dado que eu navegue até o Controle de Perdas
    E efetue login com perfil com permissão de Visualização
    E acesso a TAB Informações
    E acesso a TAB SOBRE no CDP
    E valida o preenchimento da Exibição do membro de Equipe na linha 1

  Cenário: CT03 - Edito os dados do membro de equipe
    Dado que eu navegue até o Administração
    E efetue login com perfil com permissão de Admin
    E acesso a TAB CDP
    E acesso a TAB SOBRE no Administração
    Dado clico no botão editar do Membro de equipe da linha 1
    E editos os dados da modal de membro
    E clico no botão Salvar do dialog de adicionar membro
    Entao deve exibir a snackBar com a mensagem "Registro salvo com sucesso."
    E valido o membro de equipe editado na lista na linha 1

  Cenário: CT04 - Excluo no membro de equipe
    Dado clico no botão remover do Membro de equipe da linha 1
    E confirmo a exclusão do membro de equipe
    Entao deve exibir a snackBar com a mensagem "Registro excluído com sucesso."
    E valido que o número de membro de equipe na lista é igual a 0

  Cenário: CT05 - Adicionar dois novos membros de equipe de uma vez
    Dado que eu navegue até o Administração
    E efetue login com perfil com permissão de Admin
    E acesso a TAB CDP
    E acesso a TAB SOBRE no Administração
    E clico no botão Adicionar membro
    Entao preencho a modal de membro
    E clico no botão Salvar e adicionar do dialog de adicionar membro
    Entao deve exibir a snackBar com a mensagem "Registro salvo com sucesso."
    Entao preencho a modal de membro com outro membro
    E clico no botão Salvar do dialog de adicionar membro
    Entao deve exibir a snackBar com a mensagem "Registro salvo com sucesso."
    E valido o membro de equipe adicionado na lista na linha 1
    Entao valido que o número de membro de equipe na lista é igual a 2
