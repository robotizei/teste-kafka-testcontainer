#language: pt

@CDP @UC07 @WEB @SPRINT04 @PERMISSAO @PERMISSAO_INFO
Funcionalidade:  UC07 - CDP Informações - Permissionamento Informações

  Contexto:
    Dado que eu navegue até o Controle de Perdas

  Esquema do Cenario:  <CT> - Valida permissão informações com perfil <PERFIL>
    Quando <QUANDO>
    Então <ENTAO>

    Exemplos:
      | CT   | QUANDO                                                | ENTAO                    | PERFIL       |
      | CT01 | efetue login com perfil com permissão de Admin        | acesso a TAB Informações | Admin        |
      | CT02 | efetue login com perfil com permissão de Criação      | acesso a TAB Informações | Criação      |
      | CT03 | efetue login com perfil com permissão de Edição       | acesso a TAB Informações | Edição       |
      | CT04 | efetue login com perfil com permissão de Visualização | acesso a TAB Informações | Visualização |